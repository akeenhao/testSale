package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsProduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WsProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsProductRepository extends JpaRepository<WsProduct, Long> {
    Page<WsProduct> findAllByStoreId(Long storeId, Pageable pageable);
}
