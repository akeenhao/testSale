package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.WsAreaService;
import com.mycompany.myapp.domain.WsArea;
import com.mycompany.myapp.repository.WsAreaRepository;
import com.mycompany.myapp.service.dto.WsAreaDTO;
import com.mycompany.myapp.service.mapper.WsAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WsArea}.
 */
@Service
@Transactional
public class WsAreaServiceImpl implements WsAreaService {

    private final Logger log = LoggerFactory.getLogger(WsAreaServiceImpl.class);

    private final WsAreaRepository wsAreaRepository;

    private final WsAreaMapper wsAreaMapper;

    public WsAreaServiceImpl(WsAreaRepository wsAreaRepository, WsAreaMapper wsAreaMapper) {
        this.wsAreaRepository = wsAreaRepository;
        this.wsAreaMapper = wsAreaMapper;
    }

    @Override
    public WsAreaDTO save(WsAreaDTO wsAreaDTO) {
        log.debug("Request to save WsArea : {}", wsAreaDTO);
        WsArea wsArea = wsAreaMapper.toEntity(wsAreaDTO);
        wsArea = wsAreaRepository.save(wsArea);
        return wsAreaMapper.toDto(wsArea);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WsAreas");
        return wsAreaRepository.findAll(pageable)
            .map(wsAreaMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WsAreaDTO> findOne(Long id) {
        log.debug("Request to get WsArea : {}", id);
        return wsAreaRepository.findById(id)
            .map(wsAreaMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WsArea : {}", id);
        wsAreaRepository.deleteById(id);
    }
}
