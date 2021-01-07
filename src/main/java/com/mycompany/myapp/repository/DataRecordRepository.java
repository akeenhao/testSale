package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DataRecord;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DataRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataRecordRepository extends JpaRepository<DataRecord, Long> {
}
