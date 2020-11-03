package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.WsStoreService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.WsStoreDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.WsStore}.
 */
@RestController
@RequestMapping("/api")
public class WsStoreResource {

    private final Logger log = LoggerFactory.getLogger(WsStoreResource.class);

    private static final String ENTITY_NAME = "testWsStore";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WsStoreService wsStoreService;

    public WsStoreResource(WsStoreService wsStoreService) {
        this.wsStoreService = wsStoreService;
    }

    /**
     * {@code POST  /ws-stores} : Create a new wsStore.
     *
     * @param wsStoreDTO the wsStoreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wsStoreDTO, or with status {@code 400 (Bad Request)} if the wsStore has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ws-stores")
    public ResponseEntity<String> createWsStore(@RequestBody WsStoreDTO wsStoreDTO) throws URISyntaxException {
        log.debug("REST request to save WsStore : {}", wsStoreDTO);
        if (wsStoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new wsStore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        try {

            WsStoreDTO result = wsStoreService.insert(wsStoreDTO);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("注册成功", HttpStatus.OK);

    }

    /**
     * {@code PUT  /ws-stores} : Updates an existing wsStore.
     *
     * @param wsStoreDTO the wsStoreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wsStoreDTO,
     * or with status {@code 400 (Bad Request)} if the wsStoreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wsStoreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ws-stores")
    public ResponseEntity<WsStoreDTO> updateWsStore(@RequestBody WsStoreDTO wsStoreDTO) throws URISyntaxException {
        log.debug("REST request to update WsStore : {}", wsStoreDTO);
        if (wsStoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WsStoreDTO result = wsStoreService.save(wsStoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wsStoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ws-stores} : get all the wsStores.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wsStores in body.
     */
    @GetMapping("/ws-stores")
    public ResponseEntity<Page<WsStoreDTO>> getAllWsStores(Pageable pageable) {
        log.debug("REST request to get a page of WsStores");
        Page<WsStoreDTO> page = wsStoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/ws-stores-byAreaId/{areaId}")
    public ResponseEntity<Page<WsStoreDTO>> getAllWsStores(Pageable pageable, @PathVariable Long areaId) {
        log.debug("REST request to get a page of WsStores");
        Page<WsStoreDTO> page = wsStoreService.findAll(areaId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    /**
     * {@code GET  /ws-stores/:id} : get the "id" wsStore.
     *
     * @param id the id of the wsStoreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wsStoreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ws-stores/{id}")
    public ResponseEntity<WsStoreDTO> getWsStore(@PathVariable Long id) {
        log.debug("REST request to get WsStore : {}", id);
        Optional<WsStoreDTO> wsStoreDTO = wsStoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wsStoreDTO);
    }

    /**
     * {@code DELETE  /ws-stores/:id} : delete the "id" wsStore.
     *
     * @param id the id of the wsStoreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ws-stores/{id}")
    public ResponseEntity<Void> deleteWsStore(@PathVariable Long id) {
        log.debug("REST request to delete WsStore : {}", id);
        wsStoreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
