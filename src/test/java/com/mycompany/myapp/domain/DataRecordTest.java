package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DataRecordTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DataRecord.class);
        DataRecord dataRecord1 = new DataRecord();
        dataRecord1.setId(1L);
        DataRecord dataRecord2 = new DataRecord();
        dataRecord2.setId(dataRecord1.getId());
        assertThat(dataRecord1).isEqualTo(dataRecord2);
        dataRecord2.setId(2L);
        assertThat(dataRecord1).isNotEqualTo(dataRecord2);
        dataRecord1.setId(null);
        assertThat(dataRecord1).isNotEqualTo(dataRecord2);
    }
}
