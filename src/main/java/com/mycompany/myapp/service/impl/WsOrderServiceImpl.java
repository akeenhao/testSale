package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.WsBuyerService;
import com.mycompany.myapp.service.WsOrderService;
import com.mycompany.myapp.domain.WsOrder;
import com.mycompany.myapp.repository.WsOrderRepository;
import com.mycompany.myapp.service.dto.WsBuyerDTO;
import com.mycompany.myapp.service.dto.WsOrderDTO;
import com.mycompany.myapp.service.mapper.WsOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    WsBuyerService wsBuyerService;


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
        Page<WsOrderDTO> wsOrderDTOPage = wsOrderRepository.findAll(pageable)
            .map(wsOrderMapper::toDto);

        wsOrderDTOPage.getContent().forEach(e -> {
            Optional<WsBuyerDTO> wsBuyerDTO = wsBuyerService.findOne(e.getBuyerId());
            if (wsBuyerDTO.isPresent()) {
                e.setBuyerName(wsBuyerDTO.get().getName());
            }
        });
        return wsOrderDTOPage;
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
