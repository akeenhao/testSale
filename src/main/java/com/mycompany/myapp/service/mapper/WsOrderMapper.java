package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.WsOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WsOrder} and its DTO {@link WsOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WsOrderMapper extends EntityMapper<WsOrderDTO, WsOrder> {



    default WsOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        WsOrder wsOrder = new WsOrder();
        wsOrder.setId(id);
        return wsOrder;
    }
}
