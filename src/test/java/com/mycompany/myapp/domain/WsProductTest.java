package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsProductTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsProduct.class);
        WsProduct wsProduct1 = new WsProduct();
        wsProduct1.setId(1L);
        WsProduct wsProduct2 = new WsProduct();
        wsProduct2.setId(wsProduct1.getId());
        assertThat(wsProduct1).isEqualTo(wsProduct2);
        wsProduct2.setId(2L);
        assertThat(wsProduct1).isNotEqualTo(wsProduct2);
        wsProduct1.setId(null);
        assertThat(wsProduct1).isNotEqualTo(wsProduct2);
    }
}
