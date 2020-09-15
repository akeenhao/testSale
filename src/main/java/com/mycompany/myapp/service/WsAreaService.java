package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.WsAreaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.WsArea}.
 */
public interface WsAreaService {

    /**
     * Save a wsArea.
     *
     * @param wsAreaDTO the entity to save.
     * @return the persisted entity.
     */
    WsAreaDTO save(WsAreaDTO wsAreaDTO);

    /**
     * Get all the wsAreas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WsAreaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" wsArea.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WsAreaDTO> findOne(Long id);

    /**
     * Delete the "id" wsArea.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
