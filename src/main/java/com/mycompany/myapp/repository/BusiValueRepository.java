package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BusiValue;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BusiValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusiValueRepository extends JpaRepository<BusiValue, Long> {
}
