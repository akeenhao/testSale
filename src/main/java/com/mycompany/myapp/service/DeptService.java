package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Dept;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Dept}.
 */
public interface DeptService {

    /**
     * Save a dept.
     *
     * @param dept the entity to save.
     * @return the persisted entity.
     */
    Dept save(Dept dept);

    /**
     * Get all the depts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Dept> findAll(Pageable pageable);


    /**
     * Get the "id" dept.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Dept> findOne(Long id);

    /**
     * Delete the "id" dept.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
