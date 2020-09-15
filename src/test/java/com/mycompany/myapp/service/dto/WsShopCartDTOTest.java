package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsShopCartDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsShopCartDTO.class);
        WsShopCartDTO wsShopCartDTO1 = new WsShopCartDTO();
        wsShopCartDTO1.setId(1L);
        WsShopCartDTO wsShopCartDTO2 = new WsShopCartDTO();
        assertThat(wsShopCartDTO1).isNotEqualTo(wsShopCartDTO2);
        wsShopCartDTO2.setId(wsShopCartDTO1.getId());
        assertThat(wsShopCartDTO1).isEqualTo(wsShopCartDTO2);
        wsShopCartDTO2.setId(2L);
        assertThat(wsShopCartDTO1).isNotEqualTo(wsShopCartDTO2);
        wsShopCartDTO1.setId(null);
        assertThat(wsShopCartDTO1).isNotEqualTo(wsShopCartDTO2);
    }
}
