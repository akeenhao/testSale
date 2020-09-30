package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.TestApp;
import com.mycompany.myapp.domain.WsOrder;
import com.mycompany.myapp.repository.WsOrderRepository;
import com.mycompany.myapp.service.WsOrderService;
import com.mycompany.myapp.service.dto.WsOrderDTO;
import com.mycompany.myapp.service.mapper.WsOrderMapper;

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
 * Integration tests for the {@link WsOrderResource} REST controller.
 */
@SpringBootTest(classes = TestApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class WsOrderResourceIT {

    private static final Long DEFAULT_BUYER_ID = 1L;
    private static final Long UPDATED_BUYER_ID = 2L;

    private static final Long DEFAULT_PARKER_ID = 1L;
    private static final Long UPDATED_PARKER_ID = 2L;

    private static final Float DEFAULT_TOTAL_PRICE = 1F;
    private static final Float UPDATED_TOTAL_PRICE = 2F;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private WsOrderRepository wsOrderRepository;

    @Autowired
    private WsOrderMapper wsOrderMapper;

    @Autowired
    private WsOrderService wsOrderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWsOrderMockMvc;

    private WsOrder wsOrder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WsOrder createEntity(EntityManager em) {
        WsOrder wsOrder = new WsOrder()
            .buyerId(DEFAULT_BUYER_ID)
            .parkerId(DEFAULT_PARKER_ID)
            .totalPrice(DEFAULT_TOTAL_PRICE)
            .status(DEFAULT_STATUS);
        return wsOrder;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WsOrder createUpdatedEntity(EntityManager em) {
        WsOrder wsOrder = new WsOrder()
            .buyerId(UPDATED_BUYER_ID)
            .parkerId(UPDATED_PARKER_ID)
            .totalPrice(UPDATED_TOTAL_PRICE)
            .status(UPDATED_STATUS);
        return wsOrder;
    }

    @BeforeEach
    public void initTest() {
        wsOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createWsOrder() throws Exception {
        int databaseSizeBeforeCreate = wsOrderRepository.findAll().size();
        // Create the WsOrder
        WsOrderDTO wsOrderDTO = wsOrderMapper.toDto(wsOrder);
        restWsOrderMockMvc.perform(post("/api/ws-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wsOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the WsOrder in the database
        List<WsOrder> wsOrderList = wsOrderRepository.findAll();
        assertThat(wsOrderList).hasSize(databaseSizeBeforeCreate + 1);
        WsOrder testWsOrder = wsOrderList.get(wsOrderList.size() - 1);
        assertThat(testWsOrder.getBuyerId()).isEqualTo(DEFAULT_BUYER_ID);
        assertThat(testWsOrder.getParkerId()).isEqualTo(DEFAULT_PARKER_ID);
        assertThat(testWsOrder.getTotalPrice()).isEqualTo(DEFAULT_TOTAL_PRICE);
        assertThat(testWsOrder.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createWsOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wsOrderRepository.findAll().size();

        // Create the WsOrder with an existing ID
        wsOrder.setId(1L);
        WsOrderDTO wsOrderDTO = wsOrderMapper.toDto(wsOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWsOrderMockMvc.perform(post("/api/ws-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wsOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WsOrder in the database
        List<WsOrder> wsOrderList = wsOrderRepository.findAll();
        assertThat(wsOrderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllWsOrders() throws Exception {
        // Initialize the database
        wsOrderRepository.saveAndFlush(wsOrder);

        // Get all the wsOrderList
        restWsOrderMockMvc.perform(get("/api/ws-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wsOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].buyerId").value(hasItem(DEFAULT_BUYER_ID.intValue())))
            .andExpect(jsonPath("$.[*].parkerId").value(hasItem(DEFAULT_PARKER_ID.intValue())))
            .andExpect(jsonPath("$.[*].totalPrice").value(hasItem(DEFAULT_TOTAL_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getWsOrder() throws Exception {
        // Initialize the database
        wsOrderRepository.saveAndFlush(wsOrder);

        // Get the wsOrder
        restWsOrderMockMvc.perform(get("/api/ws-orders/{id}", wsOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(wsOrder.getId().intValue()))
            .andExpect(jsonPath("$.buyerId").value(DEFAULT_BUYER_ID.intValue()))
            .andExpect(jsonPath("$.parkerId").value(DEFAULT_PARKER_ID.intValue()))
            .andExpect(jsonPath("$.totalPrice").value(DEFAULT_TOTAL_PRICE.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }
    @Test
    @Transactional
    public void getNonExistingWsOrder() throws Exception {
        // Get the wsOrder
        restWsOrderMockMvc.perform(get("/api/ws-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWsOrder() throws Exception {
        // Initialize the database
        wsOrderRepository.saveAndFlush(wsOrder);

        int databaseSizeBeforeUpdate = wsOrderRepository.findAll().size();

        // Update the wsOrder
        WsOrder updatedWsOrder = wsOrderRepository.findById(wsOrder.getId()).get();
        // Disconnect from session so that the updates on updatedWsOrder are not directly saved in db
        em.detach(updatedWsOrder);
        updatedWsOrder
            .buyerId(UPDATED_BUYER_ID)
            .parkerId(UPDATED_PARKER_ID)
            .totalPrice(UPDATED_TOTAL_PRICE)
            .status(UPDATED_STATUS);
        WsOrderDTO wsOrderDTO = wsOrderMapper.toDto(updatedWsOrder);

        restWsOrderMockMvc.perform(put("/api/ws-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wsOrderDTO)))
            .andExpect(status().isOk());

        // Validate the WsOrder in the database
        List<WsOrder> wsOrderList = wsOrderRepository.findAll();
        assertThat(wsOrderList).hasSize(databaseSizeBeforeUpdate);
        WsOrder testWsOrder = wsOrderList.get(wsOrderList.size() - 1);
        assertThat(testWsOrder.getBuyerId()).isEqualTo(UPDATED_BUYER_ID);
        assertThat(testWsOrder.getParkerId()).isEqualTo(UPDATED_PARKER_ID);
        assertThat(testWsOrder.getTotalPrice()).isEqualTo(UPDATED_TOTAL_PRICE);
        assertThat(testWsOrder.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingWsOrder() throws Exception {
        int databaseSizeBeforeUpdate = wsOrderRepository.findAll().size();

        // Create the WsOrder
        WsOrderDTO wsOrderDTO = wsOrderMapper.toDto(wsOrder);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWsOrderMockMvc.perform(put("/api/ws-orders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wsOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the WsOrder in the database
        List<WsOrder> wsOrderList = wsOrderRepository.findAll();
        assertThat(wsOrderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWsOrder() throws Exception {
        // Initialize the database
        wsOrderRepository.saveAndFlush(wsOrder);

        int databaseSizeBeforeDelete = wsOrderRepository.findAll().size();

        // Delete the wsOrder
        restWsOrderMockMvc.perform(delete("/api/ws-orders/{id}", wsOrder.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WsOrder> wsOrderList = wsOrderRepository.findAll();
        assertThat(wsOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
