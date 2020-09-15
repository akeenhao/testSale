package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.WsProductService;
import com.mycompany.myapp.domain.WsProduct;
import com.mycompany.myapp.repository.WsProductRepository;
import com.mycompany.myapp.service.dto.WsProductDTO;
import com.mycompany.myapp.service.mapper.WsProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WsProduct}.
 */
@Service
@Transactional
public class WsProductServiceImpl implements WsProductService {

    private final Logger log = LoggerFactory.getLogger(WsProductServiceImpl.class);

    private final WsProductRepository wsProductRepository;

    private final WsProductMapper wsProductMapper;

    public WsProductServiceImpl(WsProductRepository wsProductRepository, WsProductMapper wsProductMapper) {
        this.wsProductRepository = wsProductRepository;
        this.wsProductMapper = wsProductMapper;
    }

    @Override
    public WsProductDTO save(WsProductDTO wsProductDTO) {
        log.debug("Request to save WsProduct : {}", wsProductDTO);
        WsProduct wsProduct = wsProductMapper.toEntity(wsProductDTO);
        wsProduct = wsProductRepository.save(wsProduct);
        return wsProductMapper.toDto(wsProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsProductDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WsProducts");
        return wsProductRepository.findAll(pageable)
            .map(wsProductMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WsProductDTO> findOne(Long id) {
        log.debug("Request to get WsProduct : {}", id);
        return wsProductRepository.findById(id)
            .map(wsProductMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WsProduct : {}", id);
        wsProductRepository.deleteById(id);
    }
}
