package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WsOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsOrderRepository extends JpaRepository<WsOrder, Long> {

    Page<WsOrder> findAllByStoreIdAndStatus(Long StoreId, String status, Pageable pageable);

    Page<WsOrder> findAllByStoreId(Long StoreId, Pageable pageable);
}
