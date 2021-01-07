package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestApp;
import com.mycompany.myapp.domain.DataRecord;
import com.mycompany.myapp.repository.DataRecordRepository;
import com.mycompany.myapp.service.DataRecordService;
import com.mycompany.myapp.service.dto.DataRecordDTO;
import com.mycompany.myapp.service.mapper.DataRecordMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DataRecordResource} REST controller.
 */
@SpringBootTest(classes = TestApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DataRecordResourceIT {

    private static final Integer DEFAULT_V_1 = 1;
    private static final Integer UPDATED_V_1 = 2;

    private static final Integer DEFAULT_V_2 = 1;
    private static final Integer UPDATED_V_2 = 2;

    private static final Integer DEFAULT_V_3 = 1;
    private static final Integer UPDATED_V_3 = 2;

    private static final Integer DEFAULT_V_4 = 1;
    private static final Integer UPDATED_V_4 = 2;

    private static final Integer DEFAULT_V_5 = 1;
    private static final Integer UPDATED_V_5 = 2;

    private static final Integer DEFAULT_V_6 = 1;
    private static final Integer UPDATED_V_6 = 2;

    private static final Integer DEFAULT_V_7 = 1;
    private static final Integer UPDATED_V_7 = 2;

    @Autowired
    private DataRecordRepository dataRecordRepository;

    @Autowired
    private DataRecordMapper dataRecordMapper;

    @Autowired
    private DataRecordService dataRecordService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDataRecordMockMvc;

    private DataRecord dataRecord;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataRecord createEntity(EntityManager em) {
        DataRecord dataRecord = new DataRecord()
            .v1(DEFAULT_V_1)
            .v2(DEFAULT_V_2)
            .v3(DEFAULT_V_3)
            .v4(DEFAULT_V_4)
            .v5(DEFAULT_V_5)
            .v6(DEFAULT_V_6)
            .v7(DEFAULT_V_7);
        return dataRecord;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataRecord createUpdatedEntity(EntityManager em) {
        DataRecord dataRecord = new DataRecord()
            .v1(UPDATED_V_1)
            .v2(UPDATED_V_2)
            .v3(UPDATED_V_3)
            .v4(UPDATED_V_4)
            .v5(UPDATED_V_5)
            .v6(UPDATED_V_6)
            .v7(UPDATED_V_7);
        return dataRecord;
    }

    @BeforeEach
    public void initTest() {
        dataRecord = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataRecord() throws Exception {
        int databaseSizeBeforeCreate = dataRecordRepository.findAll().size();
        // Create the DataRecord
        DataRecordDTO dataRecordDTO = dataRecordMapper.toDto(dataRecord);
        restDataRecordMockMvc.perform(post("/api/data-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataRecordDTO)))
            .andExpect(status().isCreated());

        // Validate the DataRecord in the database
        List<DataRecord> dataRecordList = dataRecordRepository.findAll();
        assertThat(dataRecordList).hasSize(databaseSizeBeforeCreate + 1);
        DataRecord testDataRecord = dataRecordList.get(dataRecordList.size() - 1);
        assertThat(testDataRecord.getv1()).isEqualTo(DEFAULT_V_1);
        assertThat(testDataRecord.getv2()).isEqualTo(DEFAULT_V_2);
        assertThat(testDataRecord.getv3()).isEqualTo(DEFAULT_V_3);
        assertThat(testDataRecord.getv4()).isEqualTo(DEFAULT_V_4);
        assertThat(testDataRecord.getv5()).isEqualTo(DEFAULT_V_5);
        assertThat(testDataRecord.getv6()).isEqualTo(DEFAULT_V_6);
        assertThat(testDataRecord.getv7()).isEqualTo(DEFAULT_V_7);
    }

    @Test
    @Transactional
    public void createDataRecordWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataRecordRepository.findAll().size();

        // Create the DataRecord with an existing ID
        dataRecord.setId(1L);
        DataRecordDTO dataRecordDTO = dataRecordMapper.toDto(dataRecord);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataRecordMockMvc.perform(post("/api/data-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataRecord in the database
        List<DataRecord> dataRecordList = dataRecordRepository.findAll();
        assertThat(dataRecordList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDataRecords() throws Exception {
        // Initialize the database
        dataRecordRepository.saveAndFlush(dataRecord);

        // Get all the dataRecordList
        restDataRecordMockMvc.perform(get("/api/data-records?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].v1").value(hasItem(DEFAULT_V_1)))
            .andExpect(jsonPath("$.[*].v2").value(hasItem(DEFAULT_V_2)))
            .andExpect(jsonPath("$.[*].v3").value(hasItem(DEFAULT_V_3)))
            .andExpect(jsonPath("$.[*].v4").value(hasItem(DEFAULT_V_4)))
            .andExpect(jsonPath("$.[*].v5").value(hasItem(DEFAULT_V_5)))
            .andExpect(jsonPath("$.[*].v6").value(hasItem(DEFAULT_V_6)))
            .andExpect(jsonPath("$.[*].v7").value(hasItem(DEFAULT_V_7)));
    }
    
    @Test
    @Transactional
    public void getDataRecord() throws Exception {
        // Initialize the database
        dataRecordRepository.saveAndFlush(dataRecord);

        // Get the dataRecord
        restDataRecordMockMvc.perform(get("/api/data-records/{id}", dataRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataRecord.getId().intValue()))
            .andExpect(jsonPath("$.v1").value(DEFAULT_V_1))
            .andExpect(jsonPath("$.v2").value(DEFAULT_V_2))
            .andExpect(jsonPath("$.v3").value(DEFAULT_V_3))
            .andExpect(jsonPath("$.v4").value(DEFAULT_V_4))
            .andExpect(jsonPath("$.v5").value(DEFAULT_V_5))
            .andExpect(jsonPath("$.v6").value(DEFAULT_V_6))
            .andExpect(jsonPath("$.v7").value(DEFAULT_V_7));
    }
    @Test
    @Transactional
    public void getNonExistingDataRecord() throws Exception {
        // Get the dataRecord
        restDataRecordMockMvc.perform(get("/api/data-records/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataRecord() throws Exception {
        // Initialize the database
        dataRecordRepository.saveAndFlush(dataRecord);

        int databaseSizeBeforeUpdate = dataRecordRepository.findAll().size();

        // Update the dataRecord
        DataRecord updatedDataRecord = dataRecordRepository.findById(dataRecord.getId()).get();
        // Disconnect from session so that the updates on updatedDataRecord are not directly saved in db
        em.detach(updatedDataRecord);
        updatedDataRecord
            .v1(UPDATED_V_1)
            .v2(UPDATED_V_2)
            .v3(UPDATED_V_3)
            .v4(UPDATED_V_4)
            .v5(UPDATED_V_5)
            .v6(UPDATED_V_6)
            .v7(UPDATED_V_7);
        DataRecordDTO dataRecordDTO = dataRecordMapper.toDto(updatedDataRecord);

        restDataRecordMockMvc.perform(put("/api/data-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataRecordDTO)))
            .andExpect(status().isOk());

        // Validate the DataRecord in the database
        List<DataRecord> dataRecordList = dataRecordRepository.findAll();
        assertThat(dataRecordList).hasSize(databaseSizeBeforeUpdate);
        DataRecord testDataRecord = dataRecordList.get(dataRecordList.size() - 1);
        assertThat(testDataRecord.getv1()).isEqualTo(UPDATED_V_1);
        assertThat(testDataRecord.getv2()).isEqualTo(UPDATED_V_2);
        assertThat(testDataRecord.getv3()).isEqualTo(UPDATED_V_3);
        assertThat(testDataRecord.getv4()).isEqualTo(UPDATED_V_4);
        assertThat(testDataRecord.getv5()).isEqualTo(UPDATED_V_5);
        assertThat(testDataRecord.getv6()).isEqualTo(UPDATED_V_6);
        assertThat(testDataRecord.getv7()).isEqualTo(UPDATED_V_7);
    }

    @Test
    @Transactional
    public void updateNonExistingDataRecord() throws Exception {
        int databaseSizeBeforeUpdate = dataRecordRepository.findAll().size();

        // Create the DataRecord
        DataRecordDTO dataRecordDTO = dataRecordMapper.toDto(dataRecord);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataRecordMockMvc.perform(put("/api/data-records")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataRecordDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataRecord in the database
        List<DataRecord> dataRecordList = dataRecordRepository.findAll();
        assertThat(dataRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDataRecord() throws Exception {
        // Initialize the database
        dataRecordRepository.saveAndFlush(dataRecord);

        int databaseSizeBeforeDelete = dataRecordRepository.findAll().size();

        // Delete the dataRecord
        restDataRecordMockMvc.perform(delete("/api/data-records/{id}", dataRecord.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataRecord> dataRecordList = dataRecordRepository.findAll();
        assertThat(dataRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
