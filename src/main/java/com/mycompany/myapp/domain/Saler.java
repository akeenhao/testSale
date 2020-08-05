package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A Saler.
 */
@Entity
@Table(name = "saler")
public class Saler implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "sale_name")
    private String saleName;

    @Column(name = "pic")
    private String pic;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaleName() {
        return saleName;
    }

    public Saler saleName(String saleName) {
        this.saleName = saleName;
        return this;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getPic() {
        return pic;
    }

    public Saler pic(String pic) {
        this.pic = pic;
        return this;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Saler)) {
            return false;
        }
        return id != null && id.equals(((Saler) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Saler{" +
            "id=" + getId() +
            ", saleName='" + getSaleName() + "'" +
            ", pic='" + getPic() + "'" +
            "}";
    }
}
