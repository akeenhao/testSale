package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.WsBuyerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.WsBuyer}.
 */
public interface WsBuyerService {

    /**
     * Save a wsBuyer.
     *
     * @param wsBuyerDTO the entity to save.
     * @return the persisted entity.
     */
    WsBuyerDTO save(WsBuyerDTO wsBuyerDTO);
    String addBalance(WsBuyerDTO wsBuyerDTO);

    /**
     * Get all the wsBuyers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WsBuyerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" wsBuyer.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WsBuyerDTO> findOne(Long id);

    /**
     * Delete the "id" wsBuyer.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
