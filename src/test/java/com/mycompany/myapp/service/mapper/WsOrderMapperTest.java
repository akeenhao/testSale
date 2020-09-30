package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class WsOrderMapperTest {

    private WsOrderMapper wsOrderMapper;

    @BeforeEach
    public void setUp() {
        wsOrderMapper = new WsOrderMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(wsOrderMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(wsOrderMapper.fromId(null)).isNull();
    }
}
