package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.WsShopCartService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.WsShopCartDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.WsShopCart}.
 */
//@RestController
//@RequestMapping("/api")
public class WsShopCartResource {

    private final Logger log = LoggerFactory.getLogger(WsShopCartResource.class);

    private static final String ENTITY_NAME = "testWsShopCart";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WsShopCartService wsShopCartService;

    public WsShopCartResource(WsShopCartService wsShopCartService) {
        this.wsShopCartService = wsShopCartService;
    }

    /**
     * {@code POST  /ws-shop-carts} : Create a new wsShopCart.
     *
     * @param wsShopCartDTO the wsShopCartDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wsShopCartDTO, or with status {@code 400 (Bad Request)} if the wsShopCart has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ws-shop-carts")
    public ResponseEntity<WsShopCartDTO> createWsShopCart(@RequestBody WsShopCartDTO wsShopCartDTO) throws URISyntaxException {
        log.debug("REST request to save WsShopCart : {}", wsShopCartDTO);
        if (wsShopCartDTO.getId() != null) {
            throw new BadRequestAlertException("A new wsShopCart cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WsShopCartDTO result = wsShopCartService.save(wsShopCartDTO);
        return ResponseEntity.created(new URI("/api/ws-shop-carts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ws-shop-carts} : Updates an existing wsShopCart.
     *
     * @param wsShopCartDTO the wsShopCartDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wsShopCartDTO,
     * or with status {@code 400 (Bad Request)} if the wsShopCartDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wsShopCartDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ws-shop-carts")
    public ResponseEntity<WsShopCartDTO> updateWsShopCart(@RequestBody WsShopCartDTO wsShopCartDTO) throws URISyntaxException {
        log.debug("REST request to update WsShopCart : {}", wsShopCartDTO);
        if (wsShopCartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WsShopCartDTO result = wsShopCartService.save(wsShopCartDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wsShopCartDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ws-shop-carts} : get all the wsShopCarts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wsShopCarts in body.
     */
    @GetMapping("/ws-shop-carts")
    public ResponseEntity<List<WsShopCartDTO>> getAllWsShopCarts(Pageable pageable) {
        log.debug("REST request to get a page of WsShopCarts");
        Page<WsShopCartDTO> page = wsShopCartService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ws-shop-carts/:id} : get the "id" wsShopCart.
     *
     * @param id the id of the wsShopCartDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wsShopCartDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ws-shop-carts/{id}")
    public ResponseEntity<WsShopCartDTO> getWsShopCart(@PathVariable Long id) {
        log.debug("REST request to get WsShopCart : {}", id);
        Optional<WsShopCartDTO> wsShopCartDTO = wsShopCartService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wsShopCartDTO);
    }

    /**
     * {@code DELETE  /ws-shop-carts/:id} : delete the "id" wsShopCart.
     *
     * @param id the id of the wsShopCartDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ws-shop-carts/{id}")
    public ResponseEntity<Void> deleteWsShopCart(@PathVariable Long id) {
        log.debug("REST request to delete WsShopCart : {}", id);
        wsShopCartService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
