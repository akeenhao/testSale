package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsBuyerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsBuyer.class);
        WsBuyer wsBuyer1 = new WsBuyer();
        wsBuyer1.setId(1L);
        WsBuyer wsBuyer2 = new WsBuyer();
        wsBuyer2.setId(wsBuyer1.getId());
        assertThat(wsBuyer1).isEqualTo(wsBuyer2);
        wsBuyer2.setId(2L);
        assertThat(wsBuyer1).isNotEqualTo(wsBuyer2);
        wsBuyer1.setId(null);
        assertThat(wsBuyer1).isNotEqualTo(wsBuyer2);
    }
}
