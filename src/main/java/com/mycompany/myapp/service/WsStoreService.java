package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.WsStoreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.WsStore}.
 */
public interface WsStoreService {

    /**
     * Save a wsStore.
     *
     * @param wsStoreDTO the entity to save.
     * @return the persisted entity.
     */
    WsStoreDTO save(WsStoreDTO wsStoreDTO);

    /**
     * Get all the wsStores.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WsStoreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" wsStore.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WsStoreDTO> findOne(Long id);

    /**
     * Delete the "id" wsStore.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
