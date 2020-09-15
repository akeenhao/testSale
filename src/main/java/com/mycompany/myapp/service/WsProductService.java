package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.WsProductDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.WsProduct}.
 */
public interface WsProductService {

    /**
     * Save a wsProduct.
     *
     * @param wsProductDTO the entity to save.
     * @return the persisted entity.
     */
    WsProductDTO save(WsProductDTO wsProductDTO);

    /**
     * Get all the wsProducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WsProductDTO> findAll(Pageable pageable);


    /**
     * Get the "id" wsProduct.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WsProductDTO> findOne(Long id);

    /**
     * Delete the "id" wsProduct.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
