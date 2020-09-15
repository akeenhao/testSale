package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsOrderDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsOrderDTO.class);
        WsOrderDTO wsOrderDTO1 = new WsOrderDTO();
        wsOrderDTO1.setId(1L);
        WsOrderDTO wsOrderDTO2 = new WsOrderDTO();
        assertThat(wsOrderDTO1).isNotEqualTo(wsOrderDTO2);
        wsOrderDTO2.setId(wsOrderDTO1.getId());
        assertThat(wsOrderDTO1).isEqualTo(wsOrderDTO2);
        wsOrderDTO2.setId(2L);
        assertThat(wsOrderDTO1).isNotEqualTo(wsOrderDTO2);
        wsOrderDTO1.setId(null);
        assertThat(wsOrderDTO1).isNotEqualTo(wsOrderDTO2);
    }
}
