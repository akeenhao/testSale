package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.WsAreaService;
import com.mycompany.myapp.service.WsStoreService;
import com.mycompany.myapp.domain.WsStore;
import com.mycompany.myapp.repository.WsStoreRepository;
import com.mycompany.myapp.service.dto.WsAreaDTO;
import com.mycompany.myapp.service.dto.WsStoreDTO;
import com.mycompany.myapp.service.mapper.WsStoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WsStore}.
 */
@Service
@Transactional
public class WsStoreServiceImpl implements WsStoreService {

    private final Logger log = LoggerFactory.getLogger(WsStoreServiceImpl.class);

    @Autowired
    public WsAreaService wsAreaService;
    private final WsStoreRepository wsStoreRepository;

    private final WsStoreMapper wsStoreMapper;

    public WsStoreServiceImpl(WsStoreRepository wsStoreRepository, WsStoreMapper wsStoreMapper) {
        this.wsStoreRepository = wsStoreRepository;
        this.wsStoreMapper = wsStoreMapper;
    }

    @Override
    public WsStoreDTO save(WsStoreDTO wsStoreDTO) {
        log.debug("Request to save WsStore : {}", wsStoreDTO);
        WsStore wsStore = wsStoreMapper.toEntity(wsStoreDTO);
        wsStore = wsStoreRepository.save(wsStore);
        return wsStoreMapper.toDto(wsStore);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsStoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WsStores");
        Page<WsStoreDTO> wsStoreDTOPage = wsStoreRepository.findAll(pageable).map(wsStoreMapper::toDto);

        wsStoreDTOPage.getContent().forEach(e -> {
            Optional<WsAreaDTO> wsAreaDTO = wsAreaService.findOne(e.getAreaId());
            e.setAreaName(wsAreaDTO.isPresent()?wsAreaDTO.get().getName():"");
        });
        return wsStoreDTOPage;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WsStoreDTO> findOne(Long id) {
        log.debug("Request to get WsStore : {}", id);
        return wsStoreRepository.findById(id)
            .map(wsStoreMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WsStore : {}", id);
        wsStoreRepository.deleteById(id);
    }
}
