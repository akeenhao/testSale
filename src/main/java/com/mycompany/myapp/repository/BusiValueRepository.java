package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BusiValue;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the BusiValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusiValueRepository extends JpaRepository<BusiValue, Long> {

    List<BusiValue> findByCOrderByDay(String code);

    @Query(value = "select v from busi_value where c=:code order by day", nativeQuery = true)
    List<Float> getBusiValueByCOrderByDay(@Param("code") String code);
}
