package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.DataRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DataRecord} and its DTO {@link DataRecordDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DataRecordMapper extends EntityMapper<DataRecordDTO, DataRecord> {



    default DataRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        DataRecord dataRecord = new DataRecord();
        dataRecord.setId(id);
        return dataRecord;
    }
}
