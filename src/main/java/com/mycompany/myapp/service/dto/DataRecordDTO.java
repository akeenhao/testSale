package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DataRecord} entity.
 */
public class DataRecordDTO implements Serializable {
    
    private Long id;

    private Integer v1;

    private Integer v2;

    private Integer v3;

    private Integer v4;

    private Integer v5;

    private Integer v6;

    private Integer v7;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getv1() {
        return v1;
    }

    public void setv1(Integer v1) {
        this.v1 = v1;
    }

    public Integer getv2() {
        return v2;
    }

    public void setv2(Integer v2) {
        this.v2 = v2;
    }

    public Integer getv3() {
        return v3;
    }

    public void setv3(Integer v3) {
        this.v3 = v3;
    }

    public Integer getv4() {
        return v4;
    }

    public void setv4(Integer v4) {
        this.v4 = v4;
    }

    public Integer getv5() {
        return v5;
    }

    public void setv5(Integer v5) {
        this.v5 = v5;
    }

    public Integer getv6() {
        return v6;
    }

    public void setv6(Integer v6) {
        this.v6 = v6;
    }

    public Integer getv7() {
        return v7;
    }

    public void setv7(Integer v7) {
        this.v7 = v7;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataRecordDTO)) {
            return false;
        }

        return id != null && id.equals(((DataRecordDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataRecordDTO{" +
            "id=" + getId() +
            ", v1=" + getv1() +
            ", v2=" + getv2() +
            ", v3=" + getv3() +
            ", v4=" + getv4() +
            ", v5=" + getv5() +
            ", v6=" + getv6() +
            ", v7=" + getv7() +
            "}";
    }
}
