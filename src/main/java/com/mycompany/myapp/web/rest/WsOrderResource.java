package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.WsBuyer;
import com.mycompany.myapp.domain.WsProduct;
import com.mycompany.myapp.service.*;
import com.mycompany.myapp.service.dto.*;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import java.util.Date;
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
    @ApiOperation(value = "下单或更新老订单后下单", notes = "下单或更新老订单后下单")
    public ResponseEntity<String> createOrUpdateWsOrder(@ApiParam(value = "wsOrderDTO", name = "订单详情") @RequestBody WsOrderDTO wsOrderDTO) {

        wsOrderDTO.setCreateTime(new Date());
        wsOrderDTO.setUpdateTime(new Date());
        log.debug("REST request to save WsOrder : {}", wsOrderDTO);
        if (wsOrderDTO.getId() != null) {
            Optional<WsOrderDTO> queryRes = wsOrderService.findOne(wsOrderDTO.getId());
            WsOrderDTO queryDTO = queryRes.get();
            if (!"未付款".equals(queryDTO.getStatus()) && !"等待商家接单".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("商家已开始处理此订单，不能修改订单了，如果需要添加商品，请下新的订单", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }

            wsOrderService.delete(wsOrderDTO.getId());
            wsOrderDetailsService.deleteByOrderId(wsOrderDTO.getId());
        }
        // 校验余额是否足够
        WsBuyerDTO wsBuyerDTO = wsBuyerService.findOne(wsOrderDTO.getBuyerId()).get();
        Float priceTotal = 0F;
        for (WsOrderDetailsDTO detail : wsOrderDTO.getDetails()) {
            WsProductDTO wsProductDTO = wsProductService.findOne(detail.getProductId()).get();
            if (null == wsProductDTO) {
                ResponseEntity res = new ResponseEntity("商品不存在：" + detail.getProductId() + "-" + detail.getProductName(), HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            priceTotal += detail.getPrice() * detail.getNum();
        }
        wsOrderDTO.setTotalPrice(priceTotal);
        wsOrderDTO.setStatus("未付款");
        WsOrderDTO result = wsOrderService.save(wsOrderDTO);
        for (WsOrderDetailsDTO detail : wsOrderDTO.getDetails()) {
            detail.setOrderId(result.getId());
            wsOrderDetailsService.save(detail);
        }

        if (wsBuyerDTO.getBalance() < priceTotal) {
            ResponseEntity res = new ResponseEntity("余额不足，总价" + priceTotal + "元", HttpStatus.INTERNAL_SERVER_ERROR);
            return res;
        }

        // 减去对应余额
        wsBuyerDTO.setBalance(wsBuyerDTO.getBalance() - priceTotal);
        wsBuyerService.save(wsBuyerDTO);

        result.setStatus("等待商家接单");
        wsOrderService.save(result);

        ResponseEntity res = new ResponseEntity("订单已生成,等待商家接单", HttpStatus.OK);
        return res;
    }

    @ApiOperation(value = "处理订单（更新状态）", notes = "处理订单（更新状态）")
    @PostMapping("/updateWsOrderState")
    public ResponseEntity<String> updateWsOrderState(@ApiParam(value = "wsOrderDTO", name = "订单详情（id、状态、卖家id必填）") @RequestBody WsOrderDTO wsOrderDTO) {
        log.debug("REST request to save WsOrder : {}", wsOrderDTO);
        if (wsOrderDTO.getId() == null) {
            throw new BadRequestAlertException("A new wsOrder must have an ID", ENTITY_NAME, "id");
        }

        Optional<WsOrderDTO> queryRes = wsOrderService.findOne(wsOrderDTO.getId());
        WsOrderDTO queryDTO = queryRes.get();

        if ("等待商家接单".equals(wsOrderDTO.getStatus())) {
            if (!"未付款".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）当前状态是" + queryDTO.getStatus() + "，只有未付款的订单才能去付款", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }


            // 减去对应余额
            WsBuyerDTO wsBuyerDTO = wsBuyerService.findOne(queryDTO.getBuyerId()).get();
            Float priceTotal = 0F;
            List<WsOrderDetailsDTO> wsOrderDetailsDTOS = wsOrderDetailsService.findAllByOrderId(wsOrderDTO.getId());
            for (WsOrderDetailsDTO detail : wsOrderDetailsDTOS) {
                WsProductDTO wsProductDTO = wsProductService.findOne(detail.getProductId()).get();
                if (null == wsProductDTO) {
                    ResponseEntity res = new ResponseEntity("商品错误：" + detail.getProductId() + "-" + detail.getProductName(), HttpStatus.INTERNAL_SERVER_ERROR);
                    return res;
                }
                priceTotal += wsProductDTO.getPrice() * detail.getNum();
            }


            if (wsBuyerDTO.getBalance() < priceTotal) {
                ResponseEntity res = new ResponseEntity("余额不足，总价" + priceTotal + "元", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }

            wsBuyerDTO.setBalance(wsBuyerDTO.getBalance() - priceTotal);
            wsBuyerService.save(wsBuyerDTO);

            wsOrderDTO.setStatus("等待商家接单");
            wsOrderService.save(wsOrderDTO);

        }
        if ("备货中".equals(wsOrderDTO.getStatus())) {
            if (!"等待商家接单".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）当前状态是" + queryDTO.getStatus() + "，只有接单的订单才能去备货", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            if ("备货中".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）已经正在备货，请刷新", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            if (!wsOrderDTO.getStoreId().equals(queryDTO.getStoreId())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）不是你家的订单，无法去备货", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
        }
        if ("取消".equals(wsOrderDTO.getStatus())) {
            if (!"未付款".equals(queryDTO.getStatus()) && !"等待商家接单".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）商家已接单，在处理中，无法取消", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            Float priceTotal = 0F;
            List<WsOrderDetailsDTO> wsOrderDetailsDTOS = wsOrderDetailsService.findAllByOrderId(wsOrderDTO.getId());
            for (WsOrderDetailsDTO detail : wsOrderDetailsDTOS) {
                WsProductDTO wsProductDTO = wsProductService.findOne(detail.getProductId()).get();
                if (null == wsProductDTO) {
                    ResponseEntity res = new ResponseEntity("商品错误：" + detail.getProductId() + "-" + detail.getProductName(), HttpStatus.INTERNAL_SERVER_ERROR);
                    return res;
                }
                priceTotal += wsProductDTO.getPrice() * detail.getNum();
            }

            WsBuyerDTO wsBuyerDTO = wsBuyerService.findOne(queryDTO.getBuyerId()).get();
            // 加上对应余额
            wsBuyerDTO.setBalance(wsBuyerDTO.getBalance() + priceTotal);
            wsBuyerService.save(wsBuyerDTO);
        }
        if ("删除".equals(wsOrderDTO.getStatus())) {
            if (!"取消".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）当前状态是" + queryDTO.getStatus() + "，只有取消状态的订单才能删除", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            wsOrderService.delete(wsOrderDTO.getId());
            wsOrderDetailsService.deleteByOrderId(wsOrderDTO.getId());
        }
        if ("送货中".equals(wsOrderDTO.getStatus())) {
            if (!"备货中".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）当前状态是" + queryDTO.getStatus() + "，只有备货中的订单才能去送货", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            if (!wsOrderDTO.getStoreId().equals(queryDTO.getStoreId())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）不是你家的订单，无法去送货", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
        }
        if ("已签收".equals(wsOrderDTO.getStatus())) {
            if (!"送货中".equals(queryDTO.getStatus())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）当前状态是" + queryDTO.getStatus() + "，只有送货状态的订单，才能签收", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }
            if (!wsOrderDTO.getBuyerId().equals(queryDTO.getBuyerId())) {
                ResponseEntity res = new ResponseEntity("此订单（" + wsOrderDTO.getId() + "）不是你的订单，无法签收", HttpStatus.INTERNAL_SERVER_ERROR);
                return res;
            }

            Float priceTotal = 0F;
            List<WsOrderDetailsDTO> wsOrderDetailsDTOS = wsOrderDetailsService.findAllByOrderId(wsOrderDTO.getId());
            for (WsOrderDetailsDTO detail : wsOrderDetailsDTOS) {
                priceTotal += detail.getPrice() * detail.getNum();
                wsProductService.addSalesNum(detail.getProductId(), queryDTO.getStoreId());
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
//    @PutMapping("/ws-orders")
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
    @ApiOperation(value = "分页查询订单", notes = "分页查询订单")
    public ResponseEntity<Page<WsOrderDTO>> getAllWsOrders(Pageable pageable) {
        log.debug("REST request to get a page of WsOrders");
        Page<WsOrderDTO> page = wsOrderService.findAll(pageable);

        makeOrderAndDetails(page);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/ws-orders-byStoreAndStatus")
    @ApiOperation(value = "分页查询订单，根据门店和订单状态", notes = "分页查询订单，根据门店和订单状态")
    public ResponseEntity<Page<WsOrderDTO>> getAllWsOrdersByStoreAndStatus(
        @ApiParam(value = "storeId", name = "门店id") Long storeId,
        @ApiParam(value = "orderType", name = "订单状态（未付款、等待商家接单、取消、删除、备货中、送货中、已签收）") String orderType,
        Pageable pageable) {
        log.debug("REST request to get a page of WsOrders");

        Page<WsOrderDTO> page;
        if (StringUtils.isNotBlank(orderType)) {
            page = wsOrderService.findAllByStoreIdAndStatus(storeId, orderType, pageable);

        } else {
            page = wsOrderService.findAllByStoreId(storeId, pageable);

        }

        makeOrderAndDetails(page);


        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);

    }

    @GetMapping("/ws-orders-byBuyerAndStatus")
    @ApiOperation(value = "分页查询订单，根据买家和订单状态", notes = "分页查询订单，根据买家和订单状态")
    public ResponseEntity<Page<WsOrderDTO>> getAllWsOrdersByBuyerAndStatus(
        @ApiParam(value = "buyerId", name = "买家id") @RequestParam(required = false) Long buyerId,
        @ApiParam(value = "orderType", name = "订单状态（未付款、等待商家接单、取消、删除、备货中、送货中、已签收）") @RequestParam(required = false) String orderType,
        Pageable pageable) {
        log.debug("REST request to get a page of WsOrders");

        Page<WsOrderDTO> page;
        if (StringUtils.isNotBlank(orderType)) {
            page = wsOrderService.findAllByBuyerIdAndStatus(buyerId, orderType, pageable);

        } else {
            page = wsOrderService.findAllByBuyerId(buyerId, pageable);

        }

        makeOrderAndDetails(page);


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
    @ApiOperation(value = "查询单个订单详情", notes = "查询单个订单详情")
    public ResponseEntity<WsOrderDTO> getWsOrder(@ApiParam(value = "订单id", name = "订单id") @PathVariable Long id) {
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
//    @DeleteMapping("/ws-orders/{id}")
    public ResponseEntity<Void> deleteWsOrder(@PathVariable Long id) {
        log.debug("REST request to delete WsOrder : {}", id);
        wsOrderService.delete(id);
        wsOrderDetailsService.deleteByOrderId(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }


    private void makeOrderAndDetails(Page<WsOrderDTO> page) {
        for (WsOrderDTO wsOrderDTO : page.getContent()) {
            wsOrderDTO.setStoreName(wsStoreService.findOne(wsOrderDTO.getStoreId()).get().getName());
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
    }
}
