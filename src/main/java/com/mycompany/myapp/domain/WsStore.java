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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "openid")
    private String openid;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "balance", precision = 21, scale = 2)
    private BigDecimal balance=BigDecimal.ZERO;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "picture")
    private String picture;

    @Column(name = "password")
    private String password;

    @Column(name = "total_order_num")
    private Integer totalOrderNum=0;

    @Column(name = "point")
    private Integer point=0;

    @Column(name = "open_time")
    private Instant openTime=Instant.now();

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "latitude")
    private Float latitude;

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WsStore name(String name) {
        this.name = name;
        return this;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "WsStore{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", openid='" + openid + '\'' +
            ", personName='" + personName + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", balance=" + balance +
            ", status=" + status +
            ", picture='" + picture + '\'' +
            ", password='" + password + '\'' +
            ", totalOrderNum=" + totalOrderNum +
            ", point=" + point +
            ", openTime=" + openTime +
            ", longitude=" + longitude +
            ", latitude=" + latitude +
            '}';
    }
}
