package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DataRecordDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataRecordDTO.class);
        DataRecordDTO dataRecordDTO1 = new DataRecordDTO();
        dataRecordDTO1.setId(1L);
        DataRecordDTO dataRecordDTO2 = new DataRecordDTO();
        assertThat(dataRecordDTO1).isNotEqualTo(dataRecordDTO2);
        dataRecordDTO2.setId(dataRecordDTO1.getId());
        assertThat(dataRecordDTO1).isEqualTo(dataRecordDTO2);
        dataRecordDTO2.setId(2L);
        assertThat(dataRecordDTO1).isNotEqualTo(dataRecordDTO2);
        dataRecordDTO1.setId(null);
        assertThat(dataRecordDTO1).isNotEqualTo(dataRecordDTO2);
    }
}
