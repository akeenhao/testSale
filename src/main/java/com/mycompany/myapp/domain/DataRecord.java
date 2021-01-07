package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A DataRecord.
 */
@Entity
@Table(name = "data_record")
public class DataRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "v_1")
    private Integer v1;

    @Column(name = "v_2")
    private Integer v2;

    @Column(name = "v_3")
    private Integer v3;

    @Column(name = "v_4")
    private Integer v4;

    @Column(name = "v_5")
    private Integer v5;

    @Column(name = "v_6")
    private Integer v6;

    @Column(name = "v_7")
    private Integer v7;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getv1() {
        return v1;
    }

    public DataRecord v1(Integer v1) {
        this.v1 = v1;
        return this;
    }

    public void setv1(Integer v1) {
        this.v1 = v1;
    }

    public Integer getv2() {
        return v2;
    }

    public DataRecord v2(Integer v2) {
        this.v2 = v2;
        return this;
    }

    public void setv2(Integer v2) {
        this.v2 = v2;
    }

    public Integer getv3() {
        return v3;
    }

    public DataRecord v3(Integer v3) {
        this.v3 = v3;
        return this;
    }

    public void setv3(Integer v3) {
        this.v3 = v3;
    }

    public Integer getv4() {
        return v4;
    }

    public DataRecord v4(Integer v4) {
        this.v4 = v4;
        return this;
    }

    public void setv4(Integer v4) {
        this.v4 = v4;
    }

    public Integer getv5() {
        return v5;
    }

    public DataRecord v5(Integer v5) {
        this.v5 = v5;
        return this;
    }

    public void setv5(Integer v5) {
        this.v5 = v5;
    }

    public Integer getv6() {
        return v6;
    }

    public DataRecord v6(Integer v6) {
        this.v6 = v6;
        return this;
    }

    public void setv6(Integer v6) {
        this.v6 = v6;
    }

    public Integer getv7() {
        return v7;
    }

    public DataRecord v7(Integer v7) {
        this.v7 = v7;
        return this;
    }

    public void setv7(Integer v7) {
        this.v7 = v7;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataRecord)) {
            return false;
        }
        return id != null && id.equals(((DataRecord) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataRecord{" +
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
