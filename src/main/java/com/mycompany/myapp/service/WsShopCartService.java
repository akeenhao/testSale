package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.WsShopCartDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.WsShopCart}.
 */
public interface WsShopCartService {

    /**
     * Save a wsShopCart.
     *
     * @param wsShopCartDTO the entity to save.
     * @return the persisted entity.
     */
    WsShopCartDTO save(WsShopCartDTO wsShopCartDTO);

    /**
     * Get all the wsShopCarts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WsShopCartDTO> findAll(Pageable pageable);


    /**
     * Get the "id" wsShopCart.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WsShopCartDTO> findOne(Long id);

    /**
     * Delete the "id" wsShopCart.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
