package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A BusiValue.
 */
@Entity
@Table(name = "busi_value")
public class BusiValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day")
    private String day;

    @Column(name = "v")
    private Float v;

    @Column(name = "c")
    private String c;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public BusiValue day(String day) {
        this.day = day;
        return this;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Float getV() {
        return v;
    }

    public BusiValue v(Float v) {
        this.v = v;
        return this;
    }

    public void setV(Float v) {
        this.v = v;
    }

    public String getC() {
        return c;
    }

    public BusiValue c(String c) {
        this.c = c;
        return this;
    }

    public void setC(String c) {
        this.c = c;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BusiValue)) {
            return false;
        }
        return id != null && id.equals(((BusiValue) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BusiValue{" +
            "id=" + getId() +
            ", day='" + getDay() + "'" +
            ", v=" + getV() +
            ", c='" + getC() + "'" +
            "}";
    }
}
