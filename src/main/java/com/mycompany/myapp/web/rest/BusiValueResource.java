package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BusiValue;
import com.mycompany.myapp.service.BusiValueService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.BusiValue}.
 */
@RestController
@RequestMapping("/api")
public class BusiValueResource {

    private final Logger log = LoggerFactory.getLogger(BusiValueResource.class);

    private static final String ENTITY_NAME = "testBusiValue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BusiValueService busiValueService;

    public BusiValueResource(BusiValueService busiValueService) {
        this.busiValueService = busiValueService;
    }

    /**
     * {@code POST  /busi-values} : Create a new busiValue.
     *
     * @param busiValue the busiValue to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new busiValue, or with status {@code 400 (Bad Request)} if the busiValue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/busi-values")
    public ResponseEntity<BusiValue> createBusiValue(@RequestBody BusiValue busiValue) throws URISyntaxException {
        log.debug("REST request to save BusiValue : {}", busiValue);
        if (busiValue.getId() != null) {
            throw new BadRequestAlertException("A new busiValue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusiValue result = busiValueService.save(busiValue);
        return ResponseEntity.created(new URI("/api/busi-values/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /busi-values} : Updates an existing busiValue.
     *
     * @param busiValue the busiValue to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated busiValue,
     * or with status {@code 400 (Bad Request)} if the busiValue is not valid,
     * or with status {@code 500 (Internal Server Error)} if the busiValue couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/busi-values")
    public ResponseEntity<BusiValue> updateBusiValue(@RequestBody BusiValue busiValue) throws URISyntaxException {
        log.debug("REST request to update BusiValue : {}", busiValue);
        if (busiValue.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BusiValue result = busiValueService.save(busiValue);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, busiValue.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /busi-values} : get all the busiValues.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of busiValues in body.
     */
    @GetMapping("/busi-values")
    public List<BusiValue> getAllBusiValues() {
        log.debug("REST request to get all BusiValues");
        return busiValueService.findAll();
    }

    /**
     * {@code GET  /busi-values/:id} : get the "id" busiValue.
     *
     * @param id the id of the busiValue to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the busiValue, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/busi-values/{id}")
    public ResponseEntity<BusiValue> getBusiValue(@PathVariable Long id) {
        log.debug("REST request to get BusiValue : {}", id);
        Optional<BusiValue> busiValue = busiValueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(busiValue);
    }

    /**
     * {@code DELETE  /busi-values/:id} : delete the "id" busiValue.
     *
     * @param id the id of the busiValue to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/busi-values/{id}")
    public ResponseEntity<Void> deleteBusiValue(@PathVariable Long id) {
        log.debug("REST request to delete BusiValue : {}", id);
        busiValueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
