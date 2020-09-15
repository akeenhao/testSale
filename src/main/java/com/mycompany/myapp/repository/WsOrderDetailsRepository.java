package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsOrderDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WsOrderDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsOrderDetailsRepository extends JpaRepository<WsOrderDetails, Long> {
}
