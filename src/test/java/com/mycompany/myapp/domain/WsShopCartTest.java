package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsShopCartTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsShopCart.class);
        WsShopCart wsShopCart1 = new WsShopCart();
        wsShopCart1.setId(1L);
        WsShopCart wsShopCart2 = new WsShopCart();
        wsShopCart2.setId(wsShopCart1.getId());
        assertThat(wsShopCart1).isEqualTo(wsShopCart2);
        wsShopCart2.setId(2L);
        assertThat(wsShopCart1).isNotEqualTo(wsShopCart2);
        wsShopCart1.setId(null);
        assertThat(wsShopCart1).isNotEqualTo(wsShopCart2);
    }
}
