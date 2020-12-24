package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DeptService;
import com.mycompany.myapp.domain.Dept;
import com.mycompany.myapp.repository.DeptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dept}.
 */
@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    private final Logger log = LoggerFactory.getLogger(DeptServiceImpl.class);

    private final DeptRepository deptRepository;

    public DeptServiceImpl(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    @Override
    public Dept save(Dept dept) {
        log.debug("Request to save Dept : {}", dept);
        return deptRepository.save(dept);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Dept> findAll(Pageable pageable) {
        log.debug("Request to get all Depts");
        return deptRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Dept> findOne(Long id) {
        log.debug("Request to get Dept : {}", id);
        return deptRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dept : {}", id);
        deptRepository.deleteById(id);
    }
}
