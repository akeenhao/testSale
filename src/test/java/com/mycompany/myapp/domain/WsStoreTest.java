package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsStoreTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsStore.class);
        WsStore wsStore1 = new WsStore();
        wsStore1.setId(1L);
        WsStore wsStore2 = new WsStore();
        wsStore2.setId(wsStore1.getId());
        assertThat(wsStore1).isEqualTo(wsStore2);
        wsStore2.setId(2L);
        assertThat(wsStore1).isNotEqualTo(wsStore2);
        wsStore1.setId(null);
        assertThat(wsStore1).isNotEqualTo(wsStore2);
    }
}
