package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.BusiValueService;
import com.mycompany.myapp.domain.BusiValue;
import com.mycompany.myapp.repository.BusiValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link BusiValue}.
 */
@Service
@Transactional
public class BusiValueServiceImpl implements BusiValueService {

    private final Logger log = LoggerFactory.getLogger(BusiValueServiceImpl.class);

    private final BusiValueRepository busiValueRepository;

    public BusiValueServiceImpl(BusiValueRepository busiValueRepository) {
        this.busiValueRepository = busiValueRepository;
    }

    @Override
    public BusiValue save(BusiValue busiValue) {
        log.debug("Request to save BusiValue : {}", busiValue);
        return busiValueRepository.save(busiValue);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusiValue> findAll() {
        log.debug("Request to get all BusiValues");
        return busiValueRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BusiValue> findOne(Long id) {
        log.debug("Request to get BusiValue : {}", id);
        return busiValueRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BusiValue : {}", id);
        busiValueRepository.deleteById(id);
    }
}
