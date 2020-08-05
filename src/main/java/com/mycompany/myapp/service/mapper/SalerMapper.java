package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.SalerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Saler} and its DTO {@link SalerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SalerMapper extends EntityMapper<SalerDTO, Saler> {



    default Saler fromId(Long id) {
        if (id == null) {
            return null;
        }
        Saler saler = new Saler();
        saler.setId(id);
        return saler;
    }
}
