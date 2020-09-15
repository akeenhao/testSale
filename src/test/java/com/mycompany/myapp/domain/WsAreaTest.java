package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsAreaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsArea.class);
        WsArea wsArea1 = new WsArea();
        wsArea1.setId(1L);
        WsArea wsArea2 = new WsArea();
        wsArea2.setId(wsArea1.getId());
        assertThat(wsArea1).isEqualTo(wsArea2);
        wsArea2.setId(2L);
        assertThat(wsArea1).isNotEqualTo(wsArea2);
        wsArea1.setId(null);
        assertThat(wsArea1).isNotEqualTo(wsArea2);
    }
}
