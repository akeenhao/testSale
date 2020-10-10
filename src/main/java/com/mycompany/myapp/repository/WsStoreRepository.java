package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsStore;
import com.mycompany.myapp.service.dto.WsStoreDTO;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Spring Data  repository for the WsStore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsStoreRepository extends JpaRepository<WsStore, Long> {
    Page<WsStore> findAllByAreaId(Long areaId, Pageable pageable);

    @Query(value = "update ws_store set balance=balance+ :money where id =:id", nativeQuery = true)
    void addBalance(@Param("id") Long id, @Param("money") BigDecimal money);

    @Query(value = "update ws_store set balance=balance- :money where id =:id", nativeQuery = true)
    void subBalance(@Param("id") Long id, @Param("money") BigDecimal money);
}
