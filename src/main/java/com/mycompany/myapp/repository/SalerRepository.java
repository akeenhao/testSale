package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Saler;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Saler entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalerRepository extends JpaRepository<Saler, Long> {
}
