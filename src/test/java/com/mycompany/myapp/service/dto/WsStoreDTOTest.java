package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsStoreDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsStoreDTO.class);
        WsStoreDTO wsStoreDTO1 = new WsStoreDTO();
        wsStoreDTO1.setId(1L);
        WsStoreDTO wsStoreDTO2 = new WsStoreDTO();
        assertThat(wsStoreDTO1).isNotEqualTo(wsStoreDTO2);
        wsStoreDTO2.setId(wsStoreDTO1.getId());
        assertThat(wsStoreDTO1).isEqualTo(wsStoreDTO2);
        wsStoreDTO2.setId(2L);
        assertThat(wsStoreDTO1).isNotEqualTo(wsStoreDTO2);
        wsStoreDTO1.setId(null);
        assertThat(wsStoreDTO1).isNotEqualTo(wsStoreDTO2);
    }
}
