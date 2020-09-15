package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.WsOrderDetailsService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.WsOrderDetailsDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.WsOrderDetails}.
 */
@RestController
@RequestMapping("/api")
public class WsOrderDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WsOrderDetailsResource.class);

    private static final String ENTITY_NAME = "testWsOrderDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WsOrderDetailsService wsOrderDetailsService;

    public WsOrderDetailsResource(WsOrderDetailsService wsOrderDetailsService) {
        this.wsOrderDetailsService = wsOrderDetailsService;
    }

    /**
     * {@code POST  /ws-order-details} : Create a new wsOrderDetails.
     *
     * @param wsOrderDetailsDTO the wsOrderDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wsOrderDetailsDTO, or with status {@code 400 (Bad Request)} if the wsOrderDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ws-order-details")
    public ResponseEntity<WsOrderDetailsDTO> createWsOrderDetails(@RequestBody WsOrderDetailsDTO wsOrderDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save WsOrderDetails : {}", wsOrderDetailsDTO);
        if (wsOrderDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new wsOrderDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WsOrderDetailsDTO result = wsOrderDetailsService.save(wsOrderDetailsDTO);
        return ResponseEntity.created(new URI("/api/ws-order-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ws-order-details} : Updates an existing wsOrderDetails.
     *
     * @param wsOrderDetailsDTO the wsOrderDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wsOrderDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the wsOrderDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wsOrderDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ws-order-details")
    public ResponseEntity<WsOrderDetailsDTO> updateWsOrderDetails(@RequestBody WsOrderDetailsDTO wsOrderDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update WsOrderDetails : {}", wsOrderDetailsDTO);
        if (wsOrderDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WsOrderDetailsDTO result = wsOrderDetailsService.save(wsOrderDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wsOrderDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ws-order-details} : get all the wsOrderDetails.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wsOrderDetails in body.
     */
    @GetMapping("/ws-order-details")
    public ResponseEntity<List<WsOrderDetailsDTO>> getAllWsOrderDetails(Pageable pageable) {
        log.debug("REST request to get a page of WsOrderDetails");
        Page<WsOrderDetailsDTO> page = wsOrderDetailsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ws-order-details/:id} : get the "id" wsOrderDetails.
     *
     * @param id the id of the wsOrderDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wsOrderDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ws-order-details/{id}")
    public ResponseEntity<WsOrderDetailsDTO> getWsOrderDetails(@PathVariable Long id) {
        log.debug("REST request to get WsOrderDetails : {}", id);
        Optional<WsOrderDetailsDTO> wsOrderDetailsDTO = wsOrderDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wsOrderDetailsDTO);
    }

    /**
     * {@code DELETE  /ws-order-details/:id} : delete the "id" wsOrderDetails.
     *
     * @param id the id of the wsOrderDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ws-order-details/{id}")
    public ResponseEntity<Void> deleteWsOrderDetails(@PathVariable Long id) {
        log.debug("REST request to delete WsOrderDetails : {}", id);
        wsOrderDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
