package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Dept;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Dept entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeptRepository extends JpaRepository<Dept, Long> {
}
