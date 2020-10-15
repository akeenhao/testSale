package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.WsBuyer;
import com.mycompany.myapp.service.*;
import com.mycompany.myapp.service.dto.WsBuyerDTO;
import com.mycompany.myapp.service.dto.WsOrderDetailsDTO;
import com.mycompany.myapp.service.dto.WsStoreDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.WsOrderDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.WsOrder}.
 */
@RestController
@RequestMapping("/api")
public class WsOrderResource {

    private final Logger log = LoggerFactory.getLogger(WsOrderResource.class);

    private static final String ENTITY_NAME = "testWsOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WsOrderService wsOrderService;

    @Autowired
    WsOrderDetailsService wsOrderDetailsService;
    @Autowired
    WsBuyerService wsBuyerService;
    @Autowired
    WsStoreService wsStoreService;
    @Autowired
    WsProductService wsProductService;


    public WsOrderResource(WsOrderService wsOrderService) {
        this.wsOrderService = wsOrderService;
    }

    /**
     * {@code POST  /ws-orders} : Create a new wsOrder.
     *
     * @param wsOrderDTO the wsOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wsOrderDTO, or with status {@code 400 (Bad Request)} if the wsOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ws-orders")
    public ResponseEntity<String> createWsOrder(@RequestBody WsOrderDTO wsOrderDTO) throws URISyntaxException {

        log.debug("REST request to save WsOrder : {}", wsOrderDTO);
        if (wsOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new wsOrder cannot have an ID", ENTITY_NAME, "idexists");
        }
        // 校验余额是否足够
        WsBuyerDTO wsBuyerDTO = wsBuyerService.findOne(wsOrderDTO.getBuyerId()).get();
        Float priceTotal = 0F;
        for (WsOrderDetailsDTO detail : wsOrderDTO.getDetails()) {
            priceTotal += detail.getPrice() * detail.getNum();
        }
        if (wsBuyerDTO.getBalance() < priceTotal) {
            ResponseEntity res = new ResponseEntity("余额不足，可适当减少订单中的商品", HttpStatus.INTERNAL_SERVER_ERROR);
            return res;
        }
        wsOrderDTO.setTotalPrice(priceTotal);
        WsOrderDTO result = wsOrderService.save(wsOrderDTO);

        for (WsOrderDetailsDTO detail : wsOrderDTO.getDetails()) {
            detail.setOrderId(result.getId());
            wsOrderDetailsService.save(detail);
        }
        // 减去对应余额
        wsBuyerDTO.setBalance(wsBuyerDTO.getBalance() - priceTotal);
        wsBuyerService.save(wsBuyerDTO);

        ResponseEntity res = new ResponseEntity("订单已生成", HttpStatus.INTERNAL_SERVER_ERROR);
        return res;
    }

    @PostMapping("/updateWsOrderState")
    public ResponseEntity<String> updateWsOrderState(@RequestBody WsOrderDTO wsOrderDTO) throws URISyntaxException {
        log.debug("REST request to save WsOrder : {}", wsOrderDTO);
        if (wsOrderDTO.getId() == null) {
            throw new BadRequestAlertException("A new wsOrder must have an ID", ENTITY_NAME, "id");
        }

        Optional<WsOrderDTO> queryRes = wsOrderService.findOne(wsOrderDTO.getId());
        WsOrderDTO queryDTO = queryRes.get();
        //todo 状态改变  引起一些业务变动
        /*
        动作：
        买家创建
        卖家接单/拒单
        卖家备货完毕
        买家签收

        状态：
        等待商家接单
        备货中/取消
        送货中
        已签收
        */
        if ("备货中".equals(wsOrderDTO.getStatus())) {
            if (!"等待商家接单".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）原状态不是“等待商家接单”，无法去备货", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            if (!"备货中".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）已经正在备货，请刷新", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            if (!wsOrderDTO.getStoreId().equals(queryDTO.getStoreId())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）不是你家的订单，无法去备货", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
        }
        if ("取消".equals(wsOrderDTO.getStatus())) {
            if (!"等待商家接单".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）商家已接单，在处理中，无法取消", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            Float priceTotal = 0F;
            List<WsOrderDetailsDTO> wsOrderDetailsDTOS = wsOrderDetailsService.findAllByOrderId(wsOrderDTO.getId());
            for (WsOrderDetailsDTO detail : wsOrderDetailsDTOS) {
                priceTotal += detail.getPrice() * detail.getNum();
            }

            WsBuyerDTO wsBuyerDTO = wsBuyerService.findOne(wsOrderDTO.getBuyerId()).get();
            // 加上对应余额
            wsBuyerDTO.setBalance(wsBuyerDTO.getBalance() + priceTotal);
            wsBuyerService.save(wsBuyerDTO);
        }
        if ("送货中".equals(wsOrderDTO.getStatus())) {
            if (!"备货中".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）原状态不是“备货中”，无法去送货", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            if (!wsOrderDTO.getStoreId().equals(queryDTO.getStoreId())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）不是你家的订单，无法去送货", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
        }
        if ("已签收".equals(wsOrderDTO.getStatus())) {
            if (!"送货中".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）未送货，无法签收", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            if (!wsOrderDTO.getStoreId().equals(queryDTO.getStoreId())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）不是你家的订单，无法去送货", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }

            Float priceTotal = 0F;
            List<WsOrderDetailsDTO> wsOrderDetailsDTOS = wsOrderDetailsService.findAllByOrderId(wsOrderDTO.getId());
            for (WsOrderDetailsDTO detail : wsOrderDetailsDTOS) {
                priceTotal += detail.getPrice() * detail.getNum();
            }
            wsStoreService.addBalance(queryDTO.getStoreId(), BigDecimal.valueOf(priceTotal));
        }

        queryDTO.setStatus(wsOrderDTO.getStatus());

        wsOrderService.save(queryDTO);
        ResponseEntity res = new ResponseEntity("成功", HttpStatus.OK);
        return res;
    }


    /**
     * {@code PUT  /ws-orders} : Updates an existing wsOrder.
     *
     * @param wsOrderDTO the wsOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wsOrderDTO,
     * or with status {@code 400 (Bad Request)} if the wsOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wsOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ws-orders")
    public ResponseEntity<WsOrderDTO> updateWsOrder(@RequestBody WsOrderDTO wsOrderDTO) throws URISyntaxException {
        log.debug("REST request to update WsOrder : {}", wsOrderDTO);
        if (wsOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WsOrderDTO result = wsOrderService.save(wsOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wsOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ws-orders} : get all the wsOrders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wsOrders in body.
     */
    @GetMapping("/ws-orders")
    public ResponseEntity<Page<WsOrderDTO>> getAllWsOrders(Pageable pageable) {
        log.debug("REST request to get a page of WsOrders");
        Page<WsOrderDTO> page = wsOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/ws-orders-byStoreAndStatus")
    public ResponseEntity<Page<WsOrderDTO>> getAllWsOrdersByParam(Long storeId, String orderType, Pageable pageable) {
        log.debug("REST request to get a page of WsOrders");

        Page<WsOrderDTO> page;
        if (StringUtils.isNotBlank(orderType)) {
            page = wsOrderService.findAllByStoreIdAndStatus(storeId, orderType, pageable);

        } else {
            page = wsOrderService.findAllByStoreId(storeId, pageable);

        }

        for (WsOrderDTO wsOrderDTO : page.getContent()) {
            wsOrderDTO.setStoreName(wsStoreService.findOne(storeId).get().getName());
            WsBuyerDTO wsBuyer = wsBuyerService.findOne(wsOrderDTO.getBuyerId()).get();
            wsOrderDTO.setBuyerName(wsBuyer.getName());
            wsOrderDTO.setBuyerAddr(wsBuyer.getAddress());
            wsOrderDTO.setBuyerTel(wsBuyer.getPhone());
            List<WsOrderDetailsDTO> detailsDTOS = wsOrderDetailsService.findAllByOrderId(wsOrderDTO.getId());
            for (WsOrderDetailsDTO wsOrderDetailsDTO : detailsDTOS) {
                wsOrderDetailsDTO.setProductName(wsProductService.findOne(wsOrderDetailsDTO.getProductId()).get().getName());
            }
            wsOrderDTO.setDetails(detailsDTOS);
        }


        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);

    }

    /**
     * {@code GET  /ws-orders/:id} : get the "id" wsOrder.
     *
     * @param id the id of the wsOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wsOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ws-orders/{id}")
    public ResponseEntity<WsOrderDTO> getWsOrder(@PathVariable Long id) {
        log.debug("REST request to get WsOrder : {}", id);
        Optional<WsOrderDTO> wsOrderDTO = wsOrderService.findOne(id);
        wsOrderDTO.get().setStoreName(wsStoreService.findOne(wsOrderDTO.get().getStoreId()).get().getName());
        WsBuyerDTO wsBuyer = wsBuyerService.findOne(wsOrderDTO.get().getBuyerId()).get();
        wsOrderDTO.get().setBuyerName(wsBuyer.getName());
        wsOrderDTO.get().setBuyerAddr(wsBuyer.getAddress());
        wsOrderDTO.get().setBuyerTel(wsBuyer.getPhone());
        List<WsOrderDetailsDTO> detailsDTOS = wsOrderDetailsService.findAllByOrderId(id);
        if (wsOrderDTO.isPresent()) {
            for (WsOrderDetailsDTO wsOrderDetailsDTO : detailsDTOS) {
                wsOrderDetailsDTO.setProductName(wsProductService.findOne(wsOrderDetailsDTO.getProductId()).get().getName());
            }
            wsOrderDTO.get().setDetails(detailsDTOS);
        }

        return ResponseUtil.wrapOrNotFound(wsOrderDTO);
    }

    /**
     * {@code DELETE  /ws-orders/:id} : delete the "id" wsOrder.
     *
     * @param id the id of the wsOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ws-orders/{id}")
    public ResponseEntity<Void> deleteWsOrder(@PathVariable Long id) {
        log.debug("REST request to delete WsOrder : {}", id);
        wsOrderService.delete(id);
        wsOrderDetailsService.deleteByOrderId(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
