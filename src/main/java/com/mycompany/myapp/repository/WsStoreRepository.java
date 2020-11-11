package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsStore;
import com.mycompany.myapp.service.dto.WsStoreDTO;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Spring Data  repository for the WsStore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsStoreRepository extends JpaRepository<WsStore, Long> {
    Page<WsStore> findAllByAreaId(Long areaId, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update WsStore set totalOrderNum=totalOrderNum+1, balance=balance + ?2 where id = ?1")
    void addBalance(@Param("id") Long id, @Param("money") BigDecimal money);

    @Transactional
    @Modifying
    @Query(value = "update WsStore set balance=balance - ?2 where id = ?1")
    void subBalance(@Param("id") Long id, @Param("money") BigDecimal money);

    WsStore findByPhone(String phone);
}
