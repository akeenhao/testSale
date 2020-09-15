package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsArea;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WsArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsAreaRepository extends JpaRepository<WsArea, Long> {
}
