package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class BusiValueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusiValue.class);
        BusiValue busiValue1 = new BusiValue();
        busiValue1.setId(1L);
        BusiValue busiValue2 = new BusiValue();
        busiValue2.setId(busiValue1.getId());
        assertThat(busiValue1).isEqualTo(busiValue2);
        busiValue2.setId(2L);
        assertThat(busiValue1).isNotEqualTo(busiValue2);
        busiValue1.setId(null);
        assertThat(busiValue1).isNotEqualTo(busiValue2);
    }
}
