package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.WsShopCartService;
import com.mycompany.myapp.domain.WsShopCart;
import com.mycompany.myapp.repository.WsShopCartRepository;
import com.mycompany.myapp.service.dto.WsShopCartDTO;
import com.mycompany.myapp.service.mapper.WsShopCartMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WsShopCart}.
 */
@Service
@Transactional
public class WsShopCartServiceImpl implements WsShopCartService {

    private final Logger log = LoggerFactory.getLogger(WsShopCartServiceImpl.class);

    private final WsShopCartRepository wsShopCartRepository;

    private final WsShopCartMapper wsShopCartMapper;

    public WsShopCartServiceImpl(WsShopCartRepository wsShopCartRepository, WsShopCartMapper wsShopCartMapper) {
        this.wsShopCartRepository = wsShopCartRepository;
        this.wsShopCartMapper = wsShopCartMapper;
    }

    @Override
    public WsShopCartDTO save(WsShopCartDTO wsShopCartDTO) {
        log.debug("Request to save WsShopCart : {}", wsShopCartDTO);
        WsShopCart wsShopCart = wsShopCartMapper.toEntity(wsShopCartDTO);
        wsShopCart = wsShopCartRepository.save(wsShopCart);
        return wsShopCartMapper.toDto(wsShopCart);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsShopCartDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WsShopCarts");
        return wsShopCartRepository.findAll(pageable)
            .map(wsShopCartMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WsShopCartDTO> findOne(Long id) {
        log.debug("Request to get WsShopCart : {}", id);
        return wsShopCartRepository.findById(id)
            .map(wsShopCartMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WsShopCart : {}", id);
        wsShopCartRepository.deleteById(id);
    }
}
