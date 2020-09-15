package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsStore;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WsStore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsStoreRepository extends JpaRepository<WsStore, Long> {
}
