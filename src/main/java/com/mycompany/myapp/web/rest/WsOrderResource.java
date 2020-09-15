package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.WsOrderService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.WsOrderDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<WsOrderDTO> createWsOrder(@RequestBody WsOrderDTO wsOrderDTO) throws URISyntaxException {
        log.debug("REST request to save WsOrder : {}", wsOrderDTO);
        if (wsOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new wsOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WsOrderDTO result = wsOrderService.save(wsOrderDTO);
        return ResponseEntity.created(new URI("/api/ws-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
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
    public ResponseEntity<List<WsOrderDTO>> getAllWsOrders(Pageable pageable) {
        log.debug("REST request to get a page of WsOrders");
        Page<WsOrderDTO> page = wsOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
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
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
