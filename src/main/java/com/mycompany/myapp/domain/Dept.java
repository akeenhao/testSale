package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Dept.
 */
@Entity
@Table(name = "dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_no")
    private String deptNo;

    @Column(name = "edit_user")
    private String editUser;

    @Column(name = "edit_time")
    private Instant editTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public Dept deptName(String deptName) {
        this.deptName = deptName;
        return this;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public Dept deptNo(String deptNo) {
        this.deptNo = deptNo;
        return this;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getEditUser() {
        return editUser;
    }

    public Dept editUser(String editUser) {
        this.editUser = editUser;
        return this;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    public Instant getEditTime() {
        return editTime;
    }

    public Dept editTime(Instant editTime) {
        this.editTime = editTime;
        return this;
    }

    public void setEditTime(Instant editTime) {
        this.editTime = editTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dept)) {
            return false;
        }
        return id != null && id.equals(((Dept) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Dept{" +
            "id=" + getId() +
            ", deptName='" + getDeptName() + "'" +
            ", deptNo='" + getDeptNo() + "'" +
            ", editUser='" + getEditUser() + "'" +
            ", editTime='" + getEditTime() + "'" +
            "}";
    }
}
