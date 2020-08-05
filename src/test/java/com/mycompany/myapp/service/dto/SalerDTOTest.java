package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SalerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalerDTO.class);
        SalerDTO salerDTO1 = new SalerDTO();
        salerDTO1.setId(1L);
        SalerDTO salerDTO2 = new SalerDTO();
        assertThat(salerDTO1).isNotEqualTo(salerDTO2);
        salerDTO2.setId(salerDTO1.getId());
        assertThat(salerDTO1).isEqualTo(salerDTO2);
        salerDTO2.setId(2L);
        assertThat(salerDTO1).isNotEqualTo(salerDTO2);
        salerDTO1.setId(null);
        assertThat(salerDTO1).isNotEqualTo(salerDTO2);
    }
}
