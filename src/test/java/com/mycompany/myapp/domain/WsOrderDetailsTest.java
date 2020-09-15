package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsOrderDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsOrderDetails.class);
        WsOrderDetails wsOrderDetails1 = new WsOrderDetails();
        wsOrderDetails1.setId(1L);
        WsOrderDetails wsOrderDetails2 = new WsOrderDetails();
        wsOrderDetails2.setId(wsOrderDetails1.getId());
        assertThat(wsOrderDetails1).isEqualTo(wsOrderDetails2);
        wsOrderDetails2.setId(2L);
        assertThat(wsOrderDetails1).isNotEqualTo(wsOrderDetails2);
        wsOrderDetails1.setId(null);
        assertThat(wsOrderDetails1).isNotEqualTo(wsOrderDetails2);
    }
}
