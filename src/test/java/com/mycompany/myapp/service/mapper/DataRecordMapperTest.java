package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DataRecordMapperTest {

    private DataRecordMapper dataRecordMapper;

    @BeforeEach
    public void setUp() {
        dataRecordMapper = new DataRecordMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(dataRecordMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(dataRecordMapper.fromId(null)).isNull();
    }
}
