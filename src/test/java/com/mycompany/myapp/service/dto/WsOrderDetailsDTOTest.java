package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsOrderDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsOrderDetailsDTO.class);
        WsOrderDetailsDTO wsOrderDetailsDTO1 = new WsOrderDetailsDTO();
        wsOrderDetailsDTO1.setId(1L);
        WsOrderDetailsDTO wsOrderDetailsDTO2 = new WsOrderDetailsDTO();
        assertThat(wsOrderDetailsDTO1).isNotEqualTo(wsOrderDetailsDTO2);
        wsOrderDetailsDTO2.setId(wsOrderDetailsDTO1.getId());
        assertThat(wsOrderDetailsDTO1).isEqualTo(wsOrderDetailsDTO2);
        wsOrderDetailsDTO2.setId(2L);
        assertThat(wsOrderDetailsDTO1).isNotEqualTo(wsOrderDetailsDTO2);
        wsOrderDetailsDTO1.setId(null);
        assertThat(wsOrderDetailsDTO1).isNotEqualTo(wsOrderDetailsDTO2);
    }
}
