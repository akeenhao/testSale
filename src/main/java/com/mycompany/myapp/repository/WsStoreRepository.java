package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsStore;
import com.mycompany.myapp.service.dto.WsStoreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the WsStore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsStoreRepository extends JpaRepository<WsStore, Long> {
    Page<WsStore>  findAllByAreaId(Long areaId, Pageable pageable);
}
