package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Dept;
import com.mycompany.myapp.service.DeptService;
import com.mycompany.myapp.service.dto.JsonResult;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Dept}.
 */
@RestController
@RequestMapping("/api")
public class DeptResource {

    private final Logger log = LoggerFactory.getLogger(DeptResource.class);

    private static final String ENTITY_NAME = "testDept";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeptService deptService;

    public DeptResource(DeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * {@code POST  /depts} : Create a new dept.
     *
     * @param dept the dept to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dept, or with status {@code 400 (Bad Request)} if the dept has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/depts")
    public JsonResult<Dept> createDept(@RequestBody Dept dept) throws URISyntaxException {
        log.debug("REST request to save Dept : {}", dept);
        Dept result = deptService.save(dept);
        return JsonResult.get(result);
    }

    /**
     * {@code PUT  /depts} : Updates an existing dept.
     *
     * @param dept the dept to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dept,
     * or with status {@code 400 (Bad Request)} if the dept is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dept couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/depts")
    public ResponseEntity<Dept> updateDept(@RequestBody Dept dept) throws URISyntaxException {
        log.debug("REST request to update Dept : {}", dept);
        if (dept.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Dept result = deptService.save(dept);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dept.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /depts} : get all the depts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of depts in body.
     */
    @GetMapping("/depts")
    public JsonResult<List<Dept>> getAllDepts(Pageable pageable) {
        log.debug("REST request to get a page of Depts");
        Page<Dept> page = deptService.findAll(pageable);
        return JsonResult.get(page.getContent());
    }

    /**
     * {@code GET  /depts/:id} : get the "id" dept.
     *
     * @param id the id of the dept to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dept, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/depts/{id}")
    public ResponseEntity<Dept> getDept(@PathVariable Long id) {
        log.debug("REST request to get Dept : {}", id);
        Optional<Dept> dept = deptService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dept);
    }

    /**
     * {@code DELETE  /depts/:id} : delete the "id" dept.
     *
     * @param id the id of the dept to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/depts/{id}")
    public JsonResult deleteDept(@PathVariable Long id) {
        log.debug("REST request to delete Dept : {}", id);
        deptService.delete(id);
        return JsonResult.ok();
    }
}
