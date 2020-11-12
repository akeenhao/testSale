package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsProduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Spring Data  repository for the WsProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsProductRepository extends JpaRepository<WsProduct, Long> {

    Page<WsProduct> findAllByStoreId(Long storeId, Pageable pageable);

    Page<WsProduct> findAllByNameLike(String name, Pageable pageable);

    Page<WsProduct> findAllByNameLikeAndPriceLessThanEqual(String name, Float maxPrice, Pageable pageable);

    Page<WsProduct> findAllByNameLikeAndPriceGreaterThanEqual(String name, Float minPrice, Pageable pageable);

    Page<WsProduct> findAllByNameLikeAndPriceGreaterThanEqualAndPriceLessThanEqual(String name, Float minPrice, Float maxPrice, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update WsProduct set totalOrderNum=totalOrderNum+1 where id = ?1 and storeId= ?2")
    void addSalesNum(@Param("productId") Long productId, @Param("storeId") Long storeId);
}
