package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.WsArea;
import com.mycompany.myapp.service.WsAreaService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.WsAreaDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.WsArea}.
 */
@RestController
@RequestMapping("/api")
public class WsAreaResource {

    private final Logger log = LoggerFactory.getLogger(WsAreaResource.class);

    private static final String ENTITY_NAME = "testWsArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WsAreaService wsAreaService;

    public WsAreaResource(WsAreaService wsAreaService) {
        this.wsAreaService = wsAreaService;
    }

    /**
     * {@code POST  /ws-areas} : Create a new wsArea.
     *
     * @param wsAreaDTO the wsAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wsAreaDTO, or with status {@code 400 (Bad Request)} if the wsArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ws-areas")
    public ResponseEntity<WsAreaDTO> createWsArea(@RequestBody WsAreaDTO wsAreaDTO) throws URISyntaxException {
        log.debug("REST request to save WsArea : {}", wsAreaDTO);
        if (wsAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new wsArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WsAreaDTO result = wsAreaService.save(wsAreaDTO);
        return ResponseEntity.created(new URI("/api/ws-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ws-areas} : Updates an existing wsArea.
     *
     * @param wsAreaDTO the wsAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wsAreaDTO,
     * or with status {@code 400 (Bad Request)} if the wsAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wsAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ws-areas")
    public ResponseEntity<WsAreaDTO> updateWsArea(@RequestBody WsAreaDTO wsAreaDTO) throws URISyntaxException {
        log.debug("REST request to update WsArea : {}", wsAreaDTO);
        if (wsAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WsAreaDTO result = wsAreaService.save(wsAreaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wsAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ws-areas} : get all the wsAreas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wsAreas in body.
     */
    @GetMapping("/ws-areas")
    public ResponseEntity<Page<WsAreaDTO>> getAllWsAreas(Pageable pageable) {
        log.debug("REST request to get a page of WsAreas");
        Page<WsAreaDTO> page = wsAreaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    /**
     * {@code GET  /ws-areas-all} : get all the wsAreas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wsAreas in body.
     */
    @GetMapping("/ws-areas-all")
    public ResponseEntity<List<WsArea>> getAllWsAreas() {
        log.debug("REST request to get a page of WsAreas");
        List<WsArea> wsAreas = wsAreaService.findAll();
        return ResponseEntity.ok().body(wsAreas);
    }

    /**
     * {@code GET  /ws-areas/:id} : get the "id" wsArea.
     *
     * @param id the id of the wsAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wsAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ws-areas/{id}")
    public ResponseEntity<WsAreaDTO> getWsArea(@PathVariable Long id) {
        log.debug("REST request to get WsArea : {}", id);
        Optional<WsAreaDTO> wsAreaDTO = wsAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wsAreaDTO);
    }

    /**
     * {@code DELETE  /ws-areas/:id} : delete the "id" wsArea.
     *
     * @param id the id of the wsAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ws-areas/{id}")
    public ResponseEntity<Void> deleteWsArea(@PathVariable Long id) {
        log.debug("REST request to delete WsArea : {}", id);
        wsAreaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
