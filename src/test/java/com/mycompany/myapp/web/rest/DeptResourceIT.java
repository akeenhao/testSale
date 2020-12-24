package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestApp;
import com.mycompany.myapp.domain.Dept;
import com.mycompany.myapp.repository.DeptRepository;
import com.mycompany.myapp.service.DeptService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DeptResource} REST controller.
 */
@SpringBootTest(classes = TestApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DeptResourceIT {

    private static final String DEFAULT_DEPT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_NO = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_EDIT_USER = "AAAAAAAAAA";
    private static final String UPDATED_EDIT_USER = "BBBBBBBBBB";

    private static final Instant DEFAULT_EDIT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EDIT_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptService deptService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeptMockMvc;

    private Dept dept;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dept createEntity(EntityManager em) {
        Dept dept = new Dept()
            .deptName(DEFAULT_DEPT_NAME)
            .deptNo(DEFAULT_DEPT_NO)
            .editUser(DEFAULT_EDIT_USER)
            .editTime(DEFAULT_EDIT_TIME);
        return dept;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dept createUpdatedEntity(EntityManager em) {
        Dept dept = new Dept()
            .deptName(UPDATED_DEPT_NAME)
            .deptNo(UPDATED_DEPT_NO)
            .editUser(UPDATED_EDIT_USER)
            .editTime(UPDATED_EDIT_TIME);
        return dept;
    }

    @BeforeEach
    public void initTest() {
        dept = createEntity(em);
    }

    @Test
    @Transactional
    public void createDept() throws Exception {
        int databaseSizeBeforeCreate = deptRepository.findAll().size();
        // Create the Dept
        restDeptMockMvc.perform(post("/api/depts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dept)))
            .andExpect(status().isCreated());

        // Validate the Dept in the database
        List<Dept> deptList = deptRepository.findAll();
        assertThat(deptList).hasSize(databaseSizeBeforeCreate + 1);
        Dept testDept = deptList.get(deptList.size() - 1);
        assertThat(testDept.getDeptName()).isEqualTo(DEFAULT_DEPT_NAME);
        assertThat(testDept.getDeptNo()).isEqualTo(DEFAULT_DEPT_NO);
        assertThat(testDept.getEditUser()).isEqualTo(DEFAULT_EDIT_USER);
        assertThat(testDept.getEditTime()).isEqualTo(DEFAULT_EDIT_TIME);
    }

    @Test
    @Transactional
    public void createDeptWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = deptRepository.findAll().size();

        // Create the Dept with an existing ID
        dept.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeptMockMvc.perform(post("/api/depts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dept)))
            .andExpect(status().isBadRequest());

        // Validate the Dept in the database
        List<Dept> deptList = deptRepository.findAll();
        assertThat(deptList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDepts() throws Exception {
        // Initialize the database
        deptRepository.saveAndFlush(dept);

        // Get all the deptList
        restDeptMockMvc.perform(get("/api/depts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dept.getId().intValue())))
            .andExpect(jsonPath("$.[*].deptName").value(hasItem(DEFAULT_DEPT_NAME)))
            .andExpect(jsonPath("$.[*].deptNo").value(hasItem(DEFAULT_DEPT_NO)))
            .andExpect(jsonPath("$.[*].editUser").value(hasItem(DEFAULT_EDIT_USER)))
            .andExpect(jsonPath("$.[*].editTime").value(hasItem(DEFAULT_EDIT_TIME.toString())));
    }
    
    @Test
    @Transactional
    public void getDept() throws Exception {
        // Initialize the database
        deptRepository.saveAndFlush(dept);

        // Get the dept
        restDeptMockMvc.perform(get("/api/depts/{id}", dept.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dept.getId().intValue()))
            .andExpect(jsonPath("$.deptName").value(DEFAULT_DEPT_NAME))
            .andExpect(jsonPath("$.deptNo").value(DEFAULT_DEPT_NO))
            .andExpect(jsonPath("$.editUser").value(DEFAULT_EDIT_USER))
            .andExpect(jsonPath("$.editTime").value(DEFAULT_EDIT_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingDept() throws Exception {
        // Get the dept
        restDeptMockMvc.perform(get("/api/depts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDept() throws Exception {
        // Initialize the database
        deptService.save(dept);

        int databaseSizeBeforeUpdate = deptRepository.findAll().size();

        // Update the dept
        Dept updatedDept = deptRepository.findById(dept.getId()).get();
        // Disconnect from session so that the updates on updatedDept are not directly saved in db
        em.detach(updatedDept);
        updatedDept
            .deptName(UPDATED_DEPT_NAME)
            .deptNo(UPDATED_DEPT_NO)
            .editUser(UPDATED_EDIT_USER)
            .editTime(UPDATED_EDIT_TIME);

        restDeptMockMvc.perform(put("/api/depts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDept)))
            .andExpect(status().isOk());

        // Validate the Dept in the database
        List<Dept> deptList = deptRepository.findAll();
        assertThat(deptList).hasSize(databaseSizeBeforeUpdate);
        Dept testDept = deptList.get(deptList.size() - 1);
        assertThat(testDept.getDeptName()).isEqualTo(UPDATED_DEPT_NAME);
        assertThat(testDept.getDeptNo()).isEqualTo(UPDATED_DEPT_NO);
        assertThat(testDept.getEditUser()).isEqualTo(UPDATED_EDIT_USER);
        assertThat(testDept.getEditTime()).isEqualTo(UPDATED_EDIT_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingDept() throws Exception {
        int databaseSizeBeforeUpdate = deptRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeptMockMvc.perform(put("/api/depts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dept)))
            .andExpect(status().isBadRequest());

        // Validate the Dept in the database
        List<Dept> deptList = deptRepository.findAll();
        assertThat(deptList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDept() throws Exception {
        // Initialize the database
        deptService.save(dept);

        int databaseSizeBeforeDelete = deptRepository.findAll().size();

        // Delete the dept
        restDeptMockMvc.perform(delete("/api/depts/{id}", dept.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dept> deptList = deptRepository.findAll();
        assertThat(deptList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
