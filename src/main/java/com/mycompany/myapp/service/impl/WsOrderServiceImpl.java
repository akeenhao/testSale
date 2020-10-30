package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.WsOrderService;
import com.mycompany.myapp.domain.WsOrder;
import com.mycompany.myapp.repository.WsOrderRepository;
import com.mycompany.myapp.service.dto.WsOrderDTO;
import com.mycompany.myapp.service.mapper.WsOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WsOrder}.
 */
@Service
@Transactional
public class WsOrderServiceImpl implements WsOrderService {

    private final Logger log = LoggerFactory.getLogger(WsOrderServiceImpl.class);

    private final WsOrderRepository wsOrderRepository;

    private final WsOrderMapper wsOrderMapper;

    public WsOrderServiceImpl(WsOrderRepository wsOrderRepository, WsOrderMapper wsOrderMapper) {
        this.wsOrderRepository = wsOrderRepository;
        this.wsOrderMapper = wsOrderMapper;
    }

    @Override
    public WsOrderDTO save(WsOrderDTO wsOrderDTO) {
        log.debug("Request to save WsOrder : {}", wsOrderDTO);
        WsOrder wsOrder = wsOrderMapper.toEntity(wsOrderDTO);
        wsOrder = wsOrderRepository.save(wsOrder);
        return wsOrderMapper.toDto(wsOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsOrderDTO> findAll(Pageable pageable) {

        log.debug("Request to get all WsOrders");
        if (pageable.getSort().isUnsorted()) {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
            Sort sortTime = Sort.by(order);
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortTime);
        }
        return wsOrderRepository.findAll(pageable)
            .map(wsOrderMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsOrderDTO> findAllByStoreIdAndStatus(Long storeId, String status, Pageable pageable) {
        log.debug("Request to get all WsOrders");
        if (pageable.getSort().isUnsorted()) {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
            Sort sortTime = Sort.by(order);
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortTime);
        }
        return wsOrderRepository.findAllByStoreIdAndStatus(storeId, status, pageable)
            .map(wsOrderMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsOrderDTO> findAllByBuyerIdAndStatus(Long buyerId, String status, Pageable pageable) {
        log.debug("Request to get all WsOrders");
        if (pageable.getSort().isUnsorted()) {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
            Sort sortTime = Sort.by(order);
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortTime);
        }
        return wsOrderRepository.findAllByBuyerIdAndStatus(buyerId, status, pageable)
            .map(wsOrderMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsOrderDTO> findAllByStoreId(Long storeId, Pageable pageable) {
        log.debug("Request to get all WsOrders");
        if (pageable.getSort().isUnsorted()) {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
            Sort sortTime = Sort.by(order);
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortTime);
        }
        return wsOrderRepository.findAllByStoreId(storeId, pageable)
            .map(wsOrderMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsOrderDTO> findAllByBuyerId(Long buyerId, Pageable pageable) {
        log.debug("Request to get all WsOrders");
        if (pageable.getSort().isUnsorted()) {
            Sort.Order order = new Sort.Order(Sort.Direction.DESC, "createTime");
            Sort sortTime = Sort.by(order);
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sortTime);
        }
        return wsOrderRepository.findAllByBuyerId(buyerId, pageable)
            .map(wsOrderMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WsOrderDTO> findOne(Long id) {
        log.debug("Request to get WsOrder : {}", id);
        return wsOrderRepository.findById(id)
            .map(wsOrderMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WsOrder : {}", id);
        wsOrderRepository.deleteById(id);
    }
}
