package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestApp;
import com.mycompany.myapp.domain.BusiValue;
import com.mycompany.myapp.repository.BusiValueRepository;
import com.mycompany.myapp.service.BusiValueService;

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
 * Integration tests for the {@link BusiValueResource} REST controller.
 */
@SpringBootTest(classes = TestApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BusiValueResourceIT {

    private static final String DEFAULT_DAY = "AAAAAAAAAA";
    private static final String UPDATED_DAY = "BBBBBBBBBB";

    private static final Float DEFAULT_V = 1F;
    private static final Float UPDATED_V = 2F;

    private static final String DEFAULT_C = "AAAAAAAAAA";
    private static final String UPDATED_C = "BBBBBBBBBB";

    @Autowired
    private BusiValueRepository busiValueRepository;

    @Autowired
    private BusiValueService busiValueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBusiValueMockMvc;

    private BusiValue busiValue;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusiValue createEntity(EntityManager em) {
        BusiValue busiValue = new BusiValue()
            .day(DEFAULT_DAY)
            .v(DEFAULT_V)
            .c(DEFAULT_C);
        return busiValue;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusiValue createUpdatedEntity(EntityManager em) {
        BusiValue busiValue = new BusiValue()
            .day(UPDATED_DAY)
            .v(UPDATED_V)
            .c(UPDATED_C);
        return busiValue;
    }

    @BeforeEach
    public void initTest() {
        busiValue = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusiValue() throws Exception {
        int databaseSizeBeforeCreate = busiValueRepository.findAll().size();
        // Create the BusiValue
        restBusiValueMockMvc.perform(post("/api/busi-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busiValue)))
            .andExpect(status().isCreated());

        // Validate the BusiValue in the database
        List<BusiValue> busiValueList = busiValueRepository.findAll();
        assertThat(busiValueList).hasSize(databaseSizeBeforeCreate + 1);
        BusiValue testBusiValue = busiValueList.get(busiValueList.size() - 1);
        assertThat(testBusiValue.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testBusiValue.getV()).isEqualTo(DEFAULT_V);
        assertThat(testBusiValue.getC()).isEqualTo(DEFAULT_C);
    }

    @Test
    @Transactional
    public void createBusiValueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = busiValueRepository.findAll().size();

        // Create the BusiValue with an existing ID
        busiValue.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusiValueMockMvc.perform(post("/api/busi-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busiValue)))
            .andExpect(status().isBadRequest());

        // Validate the BusiValue in the database
        List<BusiValue> busiValueList = busiValueRepository.findAll();
        assertThat(busiValueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBusiValues() throws Exception {
        // Initialize the database
        busiValueRepository.saveAndFlush(busiValue);

        // Get all the busiValueList
        restBusiValueMockMvc.perform(get("/api/busi-values?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(busiValue.getId().intValue())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY)))
            .andExpect(jsonPath("$.[*].v").value(hasItem(DEFAULT_V.doubleValue())))
            .andExpect(jsonPath("$.[*].c").value(hasItem(DEFAULT_C)));
    }
    
    @Test
    @Transactional
    public void getBusiValue() throws Exception {
        // Initialize the database
        busiValueRepository.saveAndFlush(busiValue);

        // Get the busiValue
        restBusiValueMockMvc.perform(get("/api/busi-values/{id}", busiValue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(busiValue.getId().intValue()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY))
            .andExpect(jsonPath("$.v").value(DEFAULT_V.doubleValue()))
            .andExpect(jsonPath("$.c").value(DEFAULT_C));
    }
    @Test
    @Transactional
    public void getNonExistingBusiValue() throws Exception {
        // Get the busiValue
        restBusiValueMockMvc.perform(get("/api/busi-values/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusiValue() throws Exception {
        // Initialize the database
        busiValueService.save(busiValue);

        int databaseSizeBeforeUpdate = busiValueRepository.findAll().size();

        // Update the busiValue
        BusiValue updatedBusiValue = busiValueRepository.findById(busiValue.getId()).get();
        // Disconnect from session so that the updates on updatedBusiValue are not directly saved in db
        em.detach(updatedBusiValue);
        updatedBusiValue
            .day(UPDATED_DAY)
            .v(UPDATED_V)
            .c(UPDATED_C);

        restBusiValueMockMvc.perform(put("/api/busi-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBusiValue)))
            .andExpect(status().isOk());

        // Validate the BusiValue in the database
        List<BusiValue> busiValueList = busiValueRepository.findAll();
        assertThat(busiValueList).hasSize(databaseSizeBeforeUpdate);
        BusiValue testBusiValue = busiValueList.get(busiValueList.size() - 1);
        assertThat(testBusiValue.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testBusiValue.getV()).isEqualTo(UPDATED_V);
        assertThat(testBusiValue.getC()).isEqualTo(UPDATED_C);
    }

    @Test
    @Transactional
    public void updateNonExistingBusiValue() throws Exception {
        int databaseSizeBeforeUpdate = busiValueRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBusiValueMockMvc.perform(put("/api/busi-values")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(busiValue)))
            .andExpect(status().isBadRequest());

        // Validate the BusiValue in the database
        List<BusiValue> busiValueList = busiValueRepository.findAll();
        assertThat(busiValueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBusiValue() throws Exception {
        // Initialize the database
        busiValueService.save(busiValue);

        int databaseSizeBeforeDelete = busiValueRepository.findAll().size();

        // Delete the busiValue
        restBusiValueMockMvc.perform(delete("/api/busi-values/{id}", busiValue.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BusiValue> busiValueList = busiValueRepository.findAll();
        assertThat(busiValueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
