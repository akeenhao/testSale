package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.SalerService;
import com.mycompany.myapp.service.util.PaymentService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.SalerDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
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

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Saler}.
 */
@RestController
@RequestMapping("/api")
public class SalerResource {

    private final Logger log = LoggerFactory.getLogger(SalerResource.class);

    private static final String ENTITY_NAME = "testSaler";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalerService salerService;


    @Autowired
    PaymentService paymentService;

    public SalerResource(SalerService salerService) {
        this.salerService = salerService;
    }

    /**
     * {@code POST  /salers} : Create a new saler.
     *
     * @param salerDTO the salerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salerDTO, or with status {@code 400 (Bad Request)} if the saler has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/salers")
    public ResponseEntity<SalerDTO> createSaler(@RequestBody SalerDTO salerDTO) throws URISyntaxException {
        log.debug("REST request to save Saler : {}", salerDTO);
        if (salerDTO.getId() != null) {
            throw new BadRequestAlertException("A new saler cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalerDTO result = salerService.save(salerDTO);
        return ResponseEntity.created(new URI("/api/salers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /salers} : Updates an existing saler.
     *
     * @param salerDTO the salerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salerDTO,
     * or with status {@code 400 (Bad Request)} if the salerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/salers")
    public ResponseEntity<SalerDTO> updateSaler(@RequestBody SalerDTO salerDTO) throws URISyntaxException {
        log.debug("REST request to update Saler : {}", salerDTO);
        if (salerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SalerDTO result = salerService.save(salerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, salerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /salers} : get all the salers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salers in body.
     */
    @GetMapping("/salers")
    public ResponseEntity<List<SalerDTO>> getAllSalers(Pageable pageable) {
        log.debug("REST request to get a page of Salers");
        Page<SalerDTO> page = salerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /salers/:id} : get the "id" saler.
     *
     * @param id the id of the salerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/salers/{id}")
    public ResponseEntity<SalerDTO> getSaler(@PathVariable Long id) {
        log.debug("REST request to get Saler : {}", id);
        Optional<SalerDTO> salerDTO = salerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salerDTO);
    }

    /**
     * {@code DELETE  /salers/:id} : delete the "id" saler.
     *
     * @param id the id of the salerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/salers/{id}")
    public ResponseEntity<Void> deleteSaler(@PathVariable Long id) {
        log.debug("REST request to delete Saler : {}", id);
        salerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }



    @GetMapping("/pay")
    public  void pay(Pageable pageable) throws UnsupportedEncodingException {

        String remoteAddr = "";
        String orderNumber = "test123";
        String openid = "test";
        paymentService.getPayConfig(remoteAddr,orderNumber,openid);
    }

}
