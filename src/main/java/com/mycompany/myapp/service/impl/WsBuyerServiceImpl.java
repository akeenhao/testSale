package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.WsBuyerService;
import com.mycompany.myapp.domain.WsBuyer;
import com.mycompany.myapp.repository.WsBuyerRepository;
import com.mycompany.myapp.service.dto.WsBuyerDTO;
import com.mycompany.myapp.service.mapper.WsBuyerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WsBuyer}.
 */
@Service
@Transactional
public class WsBuyerServiceImpl implements WsBuyerService {

    private final Logger log = LoggerFactory.getLogger(WsBuyerServiceImpl.class);

    private final WsBuyerRepository wsBuyerRepository;

    private final WsBuyerMapper wsBuyerMapper;

    public WsBuyerServiceImpl(WsBuyerRepository wsBuyerRepository, WsBuyerMapper wsBuyerMapper) {
        this.wsBuyerRepository = wsBuyerRepository;
        this.wsBuyerMapper = wsBuyerMapper;
    }

    @Override
    public WsBuyerDTO save(WsBuyerDTO wsBuyerDTO) {
        log.debug("Request to save WsBuyer : {}", wsBuyerDTO);
        WsBuyer wsBuyer = wsBuyerMapper.toEntity(wsBuyerDTO);
        wsBuyer = wsBuyerRepository.save(wsBuyer);
        return wsBuyerMapper.toDto(wsBuyer);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsBuyerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WsBuyers");
        return wsBuyerRepository.findAll(pageable)
            .map(wsBuyerMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WsBuyerDTO> findOne(Long id) {
        log.debug("Request to get WsBuyer : {}", id);
        return wsBuyerRepository.findById(id)
            .map(wsBuyerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WsBuyer : {}", id);
        wsBuyerRepository.deleteById(id);
    }
}
