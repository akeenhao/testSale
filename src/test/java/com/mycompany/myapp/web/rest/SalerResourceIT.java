package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestApp;
import com.mycompany.myapp.domain.Saler;
import com.mycompany.myapp.repository.SalerRepository;
import com.mycompany.myapp.service.SalerService;
import com.mycompany.myapp.service.dto.SalerDTO;
import com.mycompany.myapp.service.mapper.SalerMapper;

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
 * Integration tests for the {@link SalerResource} REST controller.
 */
@SpringBootTest(classes = TestApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SalerResourceIT {

    private static final String DEFAULT_SALE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SALE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PIC = "AAAAAAAAAA";
    private static final String UPDATED_PIC = "BBBBBBBBBB";

    @Autowired
    private SalerRepository salerRepository;

    @Autowired
    private SalerMapper salerMapper;

    @Autowired
    private SalerService salerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalerMockMvc;

    private Saler saler;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Saler createEntity(EntityManager em) {
        Saler saler = new Saler()
            .saleName(DEFAULT_SALE_NAME)
            .pic(DEFAULT_PIC);
        return saler;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Saler createUpdatedEntity(EntityManager em) {
        Saler saler = new Saler()
            .saleName(UPDATED_SALE_NAME)
            .pic(UPDATED_PIC);
        return saler;
    }

    @BeforeEach
    public void initTest() {
        saler = createEntity(em);
    }

    @Test
    @Transactional
    public void createSaler() throws Exception {
        int databaseSizeBeforeCreate = salerRepository.findAll().size();
        // Create the Saler
        SalerDTO salerDTO = salerMapper.toDto(saler);
        restSalerMockMvc.perform(post("/api/salers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salerDTO)))
            .andExpect(status().isCreated());

        // Validate the Saler in the database
        List<Saler> salerList = salerRepository.findAll();
        assertThat(salerList).hasSize(databaseSizeBeforeCreate + 1);
        Saler testSaler = salerList.get(salerList.size() - 1);
        assertThat(testSaler.getSaleName()).isEqualTo(DEFAULT_SALE_NAME);
        assertThat(testSaler.getPic()).isEqualTo(DEFAULT_PIC);
    }

    @Test
    @Transactional
    public void createSalerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = salerRepository.findAll().size();

        // Create the Saler with an existing ID
        saler.setId(1L);
        SalerDTO salerDTO = salerMapper.toDto(saler);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalerMockMvc.perform(post("/api/salers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Saler in the database
        List<Saler> salerList = salerRepository.findAll();
        assertThat(salerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSalers() throws Exception {
        // Initialize the database
        salerRepository.saveAndFlush(saler);

        // Get all the salerList
        restSalerMockMvc.perform(get("/api/salers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(saler.getId().intValue())))
            .andExpect(jsonPath("$.[*].saleName").value(hasItem(DEFAULT_SALE_NAME)))
            .andExpect(jsonPath("$.[*].pic").value(hasItem(DEFAULT_PIC)));
    }
    
    @Test
    @Transactional
    public void getSaler() throws Exception {
        // Initialize the database
        salerRepository.saveAndFlush(saler);

        // Get the saler
        restSalerMockMvc.perform(get("/api/salers/{id}", saler.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(saler.getId().intValue()))
            .andExpect(jsonPath("$.saleName").value(DEFAULT_SALE_NAME))
            .andExpect(jsonPath("$.pic").value(DEFAULT_PIC));
    }
    @Test
    @Transactional
    public void getNonExistingSaler() throws Exception {
        // Get the saler
        restSalerMockMvc.perform(get("/api/salers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSaler() throws Exception {
        // Initialize the database
        salerRepository.saveAndFlush(saler);

        int databaseSizeBeforeUpdate = salerRepository.findAll().size();

        // Update the saler
        Saler updatedSaler = salerRepository.findById(saler.getId()).get();
        // Disconnect from session so that the updates on updatedSaler are not directly saved in db
        em.detach(updatedSaler);
        updatedSaler
            .saleName(UPDATED_SALE_NAME)
            .pic(UPDATED_PIC);
        SalerDTO salerDTO = salerMapper.toDto(updatedSaler);

        restSalerMockMvc.perform(put("/api/salers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salerDTO)))
            .andExpect(status().isOk());

        // Validate the Saler in the database
        List<Saler> salerList = salerRepository.findAll();
        assertThat(salerList).hasSize(databaseSizeBeforeUpdate);
        Saler testSaler = salerList.get(salerList.size() - 1);
        assertThat(testSaler.getSaleName()).isEqualTo(UPDATED_SALE_NAME);
        assertThat(testSaler.getPic()).isEqualTo(UPDATED_PIC);
    }

    @Test
    @Transactional
    public void updateNonExistingSaler() throws Exception {
        int databaseSizeBeforeUpdate = salerRepository.findAll().size();

        // Create the Saler
        SalerDTO salerDTO = salerMapper.toDto(saler);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalerMockMvc.perform(put("/api/salers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(salerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Saler in the database
        List<Saler> salerList = salerRepository.findAll();
        assertThat(salerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSaler() throws Exception {
        // Initialize the database
        salerRepository.saveAndFlush(saler);

        int databaseSizeBeforeDelete = salerRepository.findAll().size();

        // Delete the saler
        restSalerMockMvc.perform(delete("/api/salers/{id}", saler.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Saler> salerList = salerRepository.findAll();
        assertThat(salerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
