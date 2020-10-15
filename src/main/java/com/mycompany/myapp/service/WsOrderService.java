package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.WsOrderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.WsOrder}.
 */
public interface WsOrderService {

    /**
     * Save a wsOrder.
     *
     * @param wsOrderDTO the entity to save.
     * @return the persisted entity.
     */
    WsOrderDTO save(WsOrderDTO wsOrderDTO);

    /**
     * Get all the wsOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WsOrderDTO> findAll(Pageable pageable);

    Page<WsOrderDTO> findAllByStoreIdAndStatus(Long storeId, String status, Pageable pageable);

    Page<WsOrderDTO> findAllByStoreId(Long storeId, Pageable pageable);


    /**
     * Get the "id" wsOrder.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WsOrderDTO> findOne(Long id);

    /**
     * Delete the "id" wsOrder.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
