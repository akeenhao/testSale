package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsOrder;

import com.mycompany.myapp.service.dto.WsOrderDTO;
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

    Page<WsOrder> findAllByBuyerIdAndStatus(Long buyerId, String status, Pageable pageable);

    Page<WsOrder> findAllByStoreId(Long StoreId, Pageable pageable);

    Page<WsOrder> findAllByBuyerId(Long buyerId, Pageable pageable);

}
