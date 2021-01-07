package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.BusiValue;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link BusiValue}.
 */
public interface BusiValueService {

    /**
     * Save a busiValue.
     *
     * @param busiValue the entity to save.
     * @return the persisted entity.
     */
    BusiValue save(BusiValue busiValue);

    /**
     * Get all the busiValues.
     *
     * @return the list of entities.
     */
    List<BusiValue> findAll();


    /**
     * Get the "id" busiValue.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BusiValue> findOne(Long id);

    /**
     * Delete the "id" busiValue.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
