package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SalerMapperTest {

    private SalerMapper salerMapper;

    @BeforeEach
    public void setUp() {
        salerMapper = new SalerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(salerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(salerMapper.fromId(null)).isNull();
    }
}
