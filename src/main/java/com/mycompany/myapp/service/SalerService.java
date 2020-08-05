package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.SalerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Saler}.
 */
public interface SalerService {

    /**
     * Save a saler.
     *
     * @param salerDTO the entity to save.
     * @return the persisted entity.
     */
    SalerDTO save(SalerDTO salerDTO);

    /**
     * Get all the salers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SalerDTO> findAll(Pageable pageable);


    /**
     * Get the "id" saler.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SalerDTO> findOne(Long id);

    /**
     * Delete the "id" saler.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
