package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.WsProductService;
import com.mycompany.myapp.domain.WsProduct;
import com.mycompany.myapp.repository.WsProductRepository;
import com.mycompany.myapp.service.WsStoreService;
import com.mycompany.myapp.service.dto.WsProductDTO;
import com.mycompany.myapp.service.dto.WsStoreDTO;
import com.mycompany.myapp.service.mapper.WsProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    WsStoreService wsStoreService;

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
    public Page<WsProductDTO> findAllByStore(Pageable pageable) {
        log.debug("Request to get all WsProducts");
        Page<WsProductDTO> wsProductDTOPage = wsProductRepository.findAll(pageable)
            .map(wsProductMapper::toDto);

        setStoreName(wsProductDTOPage);

        return wsProductDTOPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsProductDTO> findAllByStore(Long storeId, Pageable pageable) {
        log.debug("Request to get all WsProducts");
        Page<WsProductDTO> wsProductDTOPage = wsProductRepository.findAllByStoreId(storeId, pageable)
            .map(wsProductMapper::toDto);
        setStoreName(wsProductDTOPage);

        return wsProductDTOPage;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WsProductDTO> findAll(String name, Float minPrice, Float maxPrice, Pageable pageable) {
        log.debug("Request to get all WsProducts");
        Page<WsProductDTO> wsProductDTOPage = null;
        if (null != minPrice && null != maxPrice) {
            wsProductDTOPage = wsProductRepository.findAllByNameLikeAndPriceGreaterThanEqualAndPriceLessThanEqual(name, minPrice, maxPrice, pageable)
                .map(wsProductMapper::toDto);
        }
        if (null != minPrice && null == maxPrice) {
            wsProductDTOPage = wsProductRepository.findAllByNameLikeAndPriceGreaterThanEqual(name, minPrice, pageable)
                .map(wsProductMapper::toDto);
        }
        if (null == minPrice && null != maxPrice) {
            wsProductDTOPage = wsProductRepository.findAllByNameLikeAndPriceLessThanEqual(name, maxPrice, pageable)
                .map(wsProductMapper::toDto);
        }
        if (null == minPrice && null == maxPrice) {
            wsProductDTOPage = wsProductRepository.findAllByNameLike(name, pageable)
                .map(wsProductMapper::toDto);
        }

        setStoreName(wsProductDTOPage);

        return wsProductDTOPage;
    }

    private void setStoreName(Page<WsProductDTO> wsProductDTOPage) {
        if (null == wsProductDTOPage) return;
        wsProductDTOPage.getContent().forEach(e -> {
            Optional<WsStoreDTO> wsStoreDTO = wsStoreService.findOne(e.getStoreId());
            if (wsStoreDTO.isPresent()) {
                e.setStoreName(wsStoreDTO.get().getName());
            }
        });
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<WsProductDTO> findOne(Long id) {
        log.debug("Request to get WsProduct : {}", id);

        Optional<WsProductDTO> wsProductDTOOptional = wsProductRepository.findById(id).map(wsProductMapper::toDto);


        wsProductDTOOptional.get().setStoreName(wsStoreService.findOne(wsProductDTOOptional.get().getStoreId()).get().getName());

        return wsProductDTOOptional;
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WsProduct : {}", id);
        wsProductRepository.deleteById(id);
    }

    @Override
    public void addSalesNum(Long productId, Long storeId) {
        log.debug("Request to addSalesNum WsProduct : product:{} store:{}", productId, storeId);
        wsProductRepository.addSalesNum(productId, storeId);
    }
}
