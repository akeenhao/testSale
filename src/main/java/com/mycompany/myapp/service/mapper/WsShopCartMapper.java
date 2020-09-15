package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.WsShopCartDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WsShopCart} and its DTO {@link WsShopCartDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WsShopCartMapper extends EntityMapper<WsShopCartDTO, WsShopCart> {



    default WsShopCart fromId(Long id) {
        if (id == null) {
            return null;
        }
        WsShopCart wsShopCart = new WsShopCart();
        wsShopCart.setId(id);
        return wsShopCart;
    }
}
