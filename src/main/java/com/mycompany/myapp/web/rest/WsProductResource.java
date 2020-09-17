package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.WsProductService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.WsProductDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.WsProduct}.
 */
@RestController
@RequestMapping("/api")
public class WsProductResource {

    private final Logger log = LoggerFactory.getLogger(WsProductResource.class);

    private static final String ENTITY_NAME = "testWsProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WsProductService wsProductService;

    public WsProductResource(WsProductService wsProductService) {
        this.wsProductService = wsProductService;
    }

    /**
     * {@code POST  /ws-products} : Create a new wsProduct.
     *
     * @param wsProductDTO the wsProductDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wsProductDTO, or with status {@code 400 (Bad Request)} if the wsProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ws-products")
    public ResponseEntity<WsProductDTO> createWsProduct(@RequestBody WsProductDTO wsProductDTO) throws URISyntaxException {
        log.debug("REST request to save WsProduct : {}", wsProductDTO);
        if (wsProductDTO.getId() != null) {
            throw new BadRequestAlertException("A new wsProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WsProductDTO result = wsProductService.save(wsProductDTO);
        return ResponseEntity.created(new URI("/api/ws-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ws-products} : Updates an existing wsProduct.
     *
     * @param wsProductDTO the wsProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wsProductDTO,
     * or with status {@code 400 (Bad Request)} if the wsProductDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wsProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ws-products")
    public ResponseEntity<WsProductDTO> updateWsProduct(@RequestBody WsProductDTO wsProductDTO) throws URISyntaxException {
        log.debug("REST request to update WsProduct : {}", wsProductDTO);
        if (wsProductDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WsProductDTO result = wsProductService.save(wsProductDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wsProductDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ws-products} : get all the wsProducts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wsProducts in body.
     */
    @GetMapping("/ws-products")
    public ResponseEntity<List<WsProductDTO>> getAllWsProducts(Pageable pageable) {
        log.debug("REST request to get a page of WsProducts");
        Page<WsProductDTO> page = wsProductService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/ws-products-byStoreId/{storeId}")
    public ResponseEntity<Page<WsProductDTO>> getAllWsProductsByStoreId(@PathVariable Long storeId, Pageable pageable) {
        log.debug("REST request to get a page of WsProducts");
        Page<WsProductDTO> page = wsProductService.findAll(storeId,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    /**
     * {@code GET  /ws-products/:id} : get the "id" wsProduct.
     *
     * @param id the id of the wsProductDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wsProductDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ws-products/{id}")
    public ResponseEntity<WsProductDTO> getWsProduct(@PathVariable Long id) {
        log.debug("REST request to get WsProduct : {}", id);
        Optional<WsProductDTO> wsProductDTO = wsProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wsProductDTO);
    }

    /**
     * {@code DELETE  /ws-products/:id} : delete the "id" wsProduct.
     *
     * @param id the id of the wsProductDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ws-products/{id}")
    public ResponseEntity<Void> deleteWsProduct(@PathVariable Long id) {
        log.debug("REST request to delete WsProduct : {}", id);
        wsProductService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
