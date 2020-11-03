package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.WsBuyerService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.WsBuyerDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.WsBuyer}.
 */
@RestController
@RequestMapping("/api")
public class WsBuyerResource {

    private final Logger log = LoggerFactory.getLogger(WsBuyerResource.class);

    private static final String ENTITY_NAME = "testWsBuyer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WsBuyerService wsBuyerService;

    public WsBuyerResource(WsBuyerService wsBuyerService) {
        this.wsBuyerService = wsBuyerService;
    }

    /**
     * {@code POST  /ws-buyers} : Create a new wsBuyer.
     *
     * @param wsBuyerDTO the wsBuyerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wsBuyerDTO, or with status {@code 400 (Bad Request)} if the wsBuyer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ws-buyers")
    public ResponseEntity<String> createWsBuyer(@RequestBody WsBuyerDTO wsBuyerDTO) throws URISyntaxException {
        log.debug("REST request to save WsBuyer : {}", wsBuyerDTO);
        if (wsBuyerDTO.getId() != null) {
            throw new BadRequestAlertException("A new wsBuyer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        try {
            wsBuyerService.insert(wsBuyerDTO);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity("注册成功", HttpStatus.OK);
    }

    @PostMapping("/buyer-addbalance")
    public ResponseEntity<String> addBalance(@RequestBody WsBuyerDTO wsBuyerDTO) throws URISyntaxException {

        String msg = wsBuyerService.addBalance(wsBuyerDTO);

        return ResponseEntity.created(new URI("/api/buyer-addbalance"))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, msg))
            .body(msg);
    }

    /**
     * {@code PUT  /ws-buyers} : Updates an existing wsBuyer.
     *
     * @param wsBuyerDTO the wsBuyerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wsBuyerDTO,
     * or with status {@code 400 (Bad Request)} if the wsBuyerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wsBuyerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ws-buyers")
    public ResponseEntity<String> updateWsBuyer(@RequestBody WsBuyerDTO wsBuyerDTO) throws URISyntaxException {
        log.debug("REST request to update WsBuyer : {}", wsBuyerDTO);
        if (wsBuyerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        wsBuyerService.save(wsBuyerDTO);
        return new ResponseEntity("更新成功", HttpStatus.OK);
    }

    /**
     * {@code GET  /ws-buyers} : get all the wsBuyers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wsBuyers in body.
     */
    @GetMapping("/ws-buyers")
    public ResponseEntity<List<WsBuyerDTO>> getAllWsBuyers(Pageable pageable) {
        log.debug("REST request to get a page of WsBuyers");
        Page<WsBuyerDTO> page = wsBuyerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ws-buyers/:id} : get the "id" wsBuyer.
     *
     * @param id the id of the wsBuyerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wsBuyerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ws-buyers/{id}")
    public ResponseEntity<WsBuyerDTO> getWsBuyer(@PathVariable Long id) {
        log.debug("REST request to get WsBuyer : {}", id);
        Optional<WsBuyerDTO> wsBuyerDTO = wsBuyerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wsBuyerDTO);
    }

    /**
     * {@code DELETE  /ws-buyers/:id} : delete the "id" wsBuyer.
     *
     * @param id the id of the wsBuyerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ws-buyers/{id}")
    public ResponseEntity<Void> deleteWsBuyer(@PathVariable Long id) {
        log.debug("REST request to delete WsBuyer : {}", id);
        wsBuyerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
