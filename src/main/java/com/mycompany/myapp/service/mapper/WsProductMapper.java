package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.WsProductDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WsProduct} and its DTO {@link WsProductDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WsProductMapper extends EntityMapper<WsProductDTO, WsProduct> {



    default WsProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        WsProduct wsProduct = new WsProduct();
        wsProduct.setId(id);
        return wsProduct;
    }
}
