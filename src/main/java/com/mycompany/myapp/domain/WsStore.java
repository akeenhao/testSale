package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A WsStore.
 */
@Entity
@Table(name = "ws_store")
public class WsStore implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "balance", precision = 21, scale = 2)
    private BigDecimal balance;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "picture")
    private String picture;

    @Column(name = "total_order_num")
    private Integer totalOrderNum;

    @Column(name = "point")
    private Integer point;

    @Column(name = "open_time")
    private Instant openTime;

    @Column(name = "area_id")
    private Long areaId;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public WsStore name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonName() {
        return personName;
    }

    public WsStore personName(String personName) {
        this.personName = personName;
        return this;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhone() {
        return phone;
    }

    public WsStore phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public WsStore address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public WsStore balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean isStatus() {
        return status;
    }

    public WsStore status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public WsStore picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    public WsStore totalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
        return this;
    }

    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    public Integer getPoint() {
        return point;
    }

    public WsStore point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Instant getOpenTime() {
        return openTime;
    }

    public WsStore openTime(Instant openTime) {
        this.openTime = openTime;
        return this;
    }

    public void setOpenTime(Instant openTime) {
        this.openTime = openTime;
    }

    public Long getAreaId() {
        return areaId;
    }

    public WsStore areaId(Long areaId) {
        this.areaId = areaId;
        return this;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsStore)) {
            return false;
        }
        return id != null && id.equals(((WsStore) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WsStore{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", personName='" + getPersonName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", balance=" + getBalance() +
            ", status='" + isStatus() + "'" +
            ", picture='" + getPicture() + "'" +
            ", totalOrderNum=" + getTotalOrderNum() +
            ", point=" + getPoint() +
            ", openTime='" + getOpenTime() + "'" +
            ", areaId=" + getAreaId() +
            "}";
    }
}
