package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SalerService;
import com.mycompany.myapp.domain.Saler;
import com.mycompany.myapp.repository.SalerRepository;
import com.mycompany.myapp.service.dto.SalerDTO;
import com.mycompany.myapp.service.mapper.SalerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Saler}.
 */
@Service
@Transactional
public class SalerServiceImpl implements SalerService {

    private final Logger log = LoggerFactory.getLogger(SalerServiceImpl.class);

    private final SalerRepository salerRepository;

    private final SalerMapper salerMapper;

    public SalerServiceImpl(SalerRepository salerRepository, SalerMapper salerMapper) {
        this.salerRepository = salerRepository;
        this.salerMapper = salerMapper;
    }

    @Override
    public SalerDTO save(SalerDTO salerDTO) {
        log.debug("Request to save Saler : {}", salerDTO);
        Saler saler = salerMapper.toEntity(salerDTO);
        saler = salerRepository.save(saler);
        return salerMapper.toDto(saler);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SalerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Salers");
        return salerRepository.findAll(pageable)
            .map(salerMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<SalerDTO> findOne(Long id) {
        log.debug("Request to get Saler : {}", id);
        return salerRepository.findById(id)
            .map(salerMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Saler : {}", id);
        salerRepository.deleteById(id);
    }
}
