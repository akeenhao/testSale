package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsOrderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsOrder.class);
        WsOrder wsOrder1 = new WsOrder();
        wsOrder1.setId(1L);
        WsOrder wsOrder2 = new WsOrder();
        wsOrder2.setId(wsOrder1.getId());
        assertThat(wsOrder1).isEqualTo(wsOrder2);
        wsOrder2.setId(2L);
        assertThat(wsOrder1).isNotEqualTo(wsOrder2);
        wsOrder1.setId(null);
        assertThat(wsOrder1).isNotEqualTo(wsOrder2);
    }
}
