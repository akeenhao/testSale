package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WsBuyer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WsBuyer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WsBuyerRepository extends JpaRepository<WsBuyer, Long> {
}
