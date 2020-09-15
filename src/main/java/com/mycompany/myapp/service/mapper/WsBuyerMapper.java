package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.WsBuyerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WsBuyer} and its DTO {@link WsBuyerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WsBuyerMapper extends EntityMapper<WsBuyerDTO, WsBuyer> {



    default WsBuyer fromId(Long id) {
        if (id == null) {
            return null;
        }
        WsBuyer wsBuyer = new WsBuyer();
        wsBuyer.setId(id);
        return wsBuyer;
    }
}
