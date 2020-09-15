package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class WsBuyerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WsBuyerDTO.class);
        WsBuyerDTO wsBuyerDTO1 = new WsBuyerDTO();
        wsBuyerDTO1.setId(1L);
        WsBuyerDTO wsBuyerDTO2 = new WsBuyerDTO();
        assertThat(wsBuyerDTO1).isNotEqualTo(wsBuyerDTO2);
        wsBuyerDTO2.setId(wsBuyerDTO1.getId());
        assertThat(wsBuyerDTO1).isEqualTo(wsBuyerDTO2);
        wsBuyerDTO2.setId(2L);
        assertThat(wsBuyerDTO1).isNotEqualTo(wsBuyerDTO2);
        wsBuyerDTO1.setId(null);
        assertThat(wsBuyerDTO1).isNotEqualTo(wsBuyerDTO2);
    }
}
