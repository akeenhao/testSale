package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.WsOrderDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WsOrderDetails} and its DTO {@link WsOrderDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WsOrderDetailsMapper extends EntityMapper<WsOrderDetailsDTO, WsOrderDetails> {



    default WsOrderDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        WsOrderDetails wsOrderDetails = new WsOrderDetails();
        wsOrderDetails.setId(id);
        return wsOrderDetails;
    }
}
