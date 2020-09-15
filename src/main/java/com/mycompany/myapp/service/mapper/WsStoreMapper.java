package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.WsStoreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WsStore} and its DTO {@link WsStoreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WsStoreMapper extends EntityMapper<WsStoreDTO, WsStore> {



    default WsStore fromId(Long id) {
        if (id == null) {
            return null;
        }
        WsStore wsStore = new WsStore();
        wsStore.setId(id);
        return wsStore;
    }
}
