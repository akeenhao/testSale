package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.WsOrderDetailsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.WsOrderDetails}.
 */
public interface WsOrderDetailsService {

    /**
     * Save a wsOrderDetails.
     *
     * @param wsOrderDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    WsOrderDetailsDTO save(WsOrderDetailsDTO wsOrderDetailsDTO);

    /**
     * Get all the wsOrderDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WsOrderDetailsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" wsOrderDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WsOrderDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" wsOrderDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
