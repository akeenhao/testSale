package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsAreaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsAreaDTO.class);
        WsAreaDTO wsAreaDTO1 = new WsAreaDTO();
        wsAreaDTO1.setId(1L);
        WsAreaDTO wsAreaDTO2 = new WsAreaDTO();
        assertThat(wsAreaDTO1).isNotEqualTo(wsAreaDTO2);
        wsAreaDTO2.setId(wsAreaDTO1.getId());
        assertThat(wsAreaDTO1).isEqualTo(wsAreaDTO2);
        wsAreaDTO2.setId(2L);
        assertThat(wsAreaDTO1).isNotEqualTo(wsAreaDTO2);
        wsAreaDTO1.setId(null);
        assertThat(wsAreaDTO1).isNotEqualTo(wsAreaDTO2);
    }
}
