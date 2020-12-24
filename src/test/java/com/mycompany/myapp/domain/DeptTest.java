package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class DeptTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dept.class);
        Dept dept1 = new Dept();
        dept1.setId(1L);
        Dept dept2 = new Dept();
        dept2.setId(dept1.getId());
        assertThat(dept1).isEqualTo(dept2);
        dept2.setId(2L);
        assertThat(dept1).isNotEqualTo(dept2);
        dept1.setId(null);
        assertThat(dept1).isNotEqualTo(dept2);
    }
}
