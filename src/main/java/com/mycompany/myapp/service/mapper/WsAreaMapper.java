package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.WsAreaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WsArea} and its DTO {@link WsAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WsAreaMapper extends EntityMapper<WsAreaDTO, WsArea> {



    default WsArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        WsArea wsArea = new WsArea();
        wsArea.setId(id);
        return wsArea;
    }
}
