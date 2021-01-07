package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DataRecordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.FileNotFoundException;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.DataRecord}.
 */
public interface DataRecordService {

    /**
     * Save a dataRecord.
     *
     * @param dataRecordDTO the entity to save.
     * @return the persisted entity.
     */
    DataRecordDTO save(DataRecordDTO dataRecordDTO);

    /**
     * Get all the dataRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DataRecordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dataRecord.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DataRecordDTO> findOne(Long id);

    /**
     * Delete the "id" dataRecord.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);



    void saveRecord() throws Exception;
}
