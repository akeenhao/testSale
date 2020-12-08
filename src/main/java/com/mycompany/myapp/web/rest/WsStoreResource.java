package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.WsStoreService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.WsStoreDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

import javax.validation.Valid;
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
    @ApiOperation(value = "创建门店", notes = "创建门店")
    public ResponseEntity<String> createWsStore(@ApiParam(value = "wsStoreDTO", name = "门店信息") @Valid @RequestBody WsStoreDTO wsStoreDTO) throws URISyntaxException {
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
    @ApiOperation(value = "更新门店", notes = "更新门店")
    public ResponseEntity<WsStoreDTO> updateWsStore(@ApiParam(value = "wsStoreDTO", name = "门店信息") @RequestBody WsStoreDTO wsStoreDTO) throws URISyntaxException {
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
    @ApiOperation(value = "分页获取门店列表", notes = "分页获取门店列表")
    public ResponseEntity<Page<WsStoreDTO>> getAllWsStores(Pageable pageable) {
        log.debug("REST request to get a page of WsStores");
        Page<WsStoreDTO> page = wsStoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }
/*
    @GetMapping("/ws-stores-byAreaId/{areaId}")
    @ApiOperation(value = "分页获取某区域下的门店列表", notes = "分页获取某区域下的门店列表")
    public ResponseEntity<Page<WsStoreDTO>> getAllWsStores(
        Pageable pageable,
        @ApiParam(value = "areaId", name = "区域id") @PathVariable Long areaId) {

        log.debug("REST request to get a page of WsStores");
        Page<WsStoreDTO> page = wsStoreService.findAll(areaId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/ws-stores-byName")
    @ApiOperation(value = "分页获取的门店列表（按名称模糊查询）", notes = "分页获取的门店列表（按名称模糊查询）")
    public ResponseEntity<Page<WsStoreDTO>> getAllWsStores(
        @ApiParam(value = "name", name = "门店名称") @RequestParam(required = false) String name,
        Pageable pageable) {
        log.debug("REST request to get a page of WsStores");
        Page<WsStoreDTO> page = wsStoreService.findAll("%" + name + "%", pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/ws-stores-byParam")
    @ApiOperation(value = "分页获取某区域下的门店列表（按名称模糊查询）", notes = "分页获取某区域下的门店列表（按名称模糊查询）")
    public ResponseEntity<Page<WsStoreDTO>> getAllWsStores(
        @ApiParam(value = "name", name = "门店名称") @RequestParam(required = false) String name,
        @ApiParam(value = "areaId", name = "区域id") @RequestParam(required = false) Long areaId,
        Pageable pageable) {
        log.debug("REST request to get a page of WsStores");
        if (null == areaId) {
            Page<WsStoreDTO> page = wsStoreService.findAll("%" + name + "%", pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            return ResponseEntity.ok().headers(headers).body(page);
        } else {
            Page<WsStoreDTO> page = wsStoreService.findAll("%" + name + "%", pageable);
//            Page<WsStoreDTO> page = wsStoreService.findAll(areaId, "%" + name + "%", pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
            return ResponseEntity.ok().headers(headers).body(page);
        }

    }

    /**
     * {@code GET  /ws-stores/:id} : get the "id" wsStore.
     *
     * @param id the id of the wsStoreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wsStoreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ws-stores/{id}")
    @ApiOperation(value = "获取某个门店的详细信息", notes = "获取某个门店的详细信息")
    public ResponseEntity<WsStoreDTO> getWsStore(@ApiParam(value = "id", name = "门店id") @PathVariable Long id) {
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
    @ApiOperation(value = "注销门店", notes = "注销门店")
    public ResponseEntity<Void> deleteWsStore(@ApiParam(value = "id", name = "门店id") @PathVariable Long id) {
        log.debug("REST request to delete WsStore : {}", id);
        wsStoreService.remove(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
