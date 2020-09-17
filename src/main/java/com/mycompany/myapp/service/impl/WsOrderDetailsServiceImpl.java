package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.WsOrderDetailsService;
import com.mycompany.myapp.domain.WsOrderDetails;
import com.mycompany.myapp.repository.WsOrderDetailsRepository;
import com.mycompany.myapp.service.WsOrderService;
import com.mycompany.myapp.service.WsProductService;
import com.mycompany.myapp.service.dto.WsOrderDTO;
import com.mycompany.myapp.service.dto.WsOrderDetailsDTO;
import com.mycompany.myapp.service.dto.WsProductDTO;
import com.mycompany.myapp.service.mapper.WsOrderDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WsOrderDetails}.
 */
@Service
@Transactional
public class WsOrderDetailsServiceImpl implements WsOrderDetailsService {

    private final Logger log = LoggerFactory.getLogger(WsOrderDetailsServiceImpl.class);

    private final WsOrderDetailsRepository wsOrderDetailsRepository;

    private final WsOrderDetailsMapper wsOrderDetailsMapper;

    @Autowired
    WsOrderService wsOrderService;
    @Autowired
    WsProductService wsProductService;

    public WsOrderDetailsServiceImpl(WsOrderDetailsRepository wsOrderDetailsRepository, WsOrderDetailsMapper wsOrderDetailsMapper) {
        this.wsOrderDetailsRepository = wsOrderDetailsRepository;
        this.wsOrderDetailsMapper = wsOrderDetailsMapper;
    }

    @Override
    public WsOrderDetailsDTO save(WsOrderDetailsDTO wsOrderDetailsDTO) {
        log.debug("Request to save WsOrderDetails : {}", wsOrderDetailsDTO);
        WsOrderDetails wsOrderDetails = wsOrderDetailsMapper.toEntity(wsOrderDetailsDTO);
        wsOrderDetails = wsOrderDetailsRepository.save(wsOrderDetails);
        return wsOrderDetailsMapper.toDto(wsOrderDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsOrderDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WsOrderDetails");
        Page<WsOrderDetailsDTO> wsOrderDetailsDTOPage = wsOrderDetailsRepository.findAll(pageable)
            .map(wsOrderDetailsMapper::toDto);
        wsOrderDetailsDTOPage.getContent().forEach(e -> {
            Optional<WsProductDTO> wsProductDTO = wsProductService.findOne(e.getProductId());
            if(wsProductDTO.isPresent()){
                e.setProductName(wsProductDTO.get().getName());
            }
        });

        return wsOrderDetailsDTOPage;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WsOrderDetailsDTO> findOne(Long id) {
        log.debug("Request to get WsOrderDetails : {}", id);
        return wsOrderDetailsRepository.findById(id)
            .map(wsOrderDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WsOrderDetails : {}", id);
        wsOrderDetailsRepository.deleteById(id);
    }
}
