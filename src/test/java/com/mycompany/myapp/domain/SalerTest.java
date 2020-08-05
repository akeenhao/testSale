package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SalerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Saler.class);
        Saler saler1 = new Saler();
        saler1.setId(1L);
        Saler saler2 = new Saler();
        saler2.setId(saler1.getId());
        assertThat(saler1).isEqualTo(saler2);
        saler2.setId(2L);
        assertThat(saler1).isNotEqualTo(saler2);
        saler1.setId(null);
        assertThat(saler1).isNotEqualTo(saler2);
    }
}
