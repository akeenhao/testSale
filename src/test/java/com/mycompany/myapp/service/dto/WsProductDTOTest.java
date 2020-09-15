package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsProductDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsProductDTO.class);
        WsProductDTO wsProductDTO1 = new WsProductDTO();
        wsProductDTO1.setId(1L);
        WsProductDTO wsProductDTO2 = new WsProductDTO();
        assertThat(wsProductDTO1).isNotEqualTo(wsProductDTO2);
        wsProductDTO2.setId(wsProductDTO1.getId());
        assertThat(wsProductDTO1).isEqualTo(wsProductDTO2);
        wsProductDTO2.setId(2L);
        assertThat(wsProductDTO1).isNotEqualTo(wsProductDTO2);
        wsProductDTO1.setId(null);
        assertThat(wsProductDTO1).isNotEqualTo(wsProductDTO2);
    }
}
