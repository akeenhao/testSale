package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A WsProduct.
 */
@Entity
@Table(name = "ws_product")
public class WsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "picture")
    private String picture;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "price")
    private Float price;

    @Column(name = "unit")
    private String unit;

    @Column(name = "remark")
    private String remark;

    @Column(name = "total_sales_num")
    private Integer totalSalesNum;

    @Column(name = "point")
    private Integer point;

    @Column(name = "del_flag")
    private boolean delFlag;

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

    public WsProduct name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public WsProduct picture(String picture) {
        this.picture = picture;
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getStoreId() {
        return storeId;
    }

    public WsProduct storeId(Long storeId) {
        this.storeId = storeId;
        return this;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Float getPrice() {
        return price;
    }

    public WsProduct price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public WsProduct unit(String unit) {
        this.unit = unit;
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRemark() {
        return remark;
    }

    public WsProduct remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTotalSalesNum() {
        return totalSalesNum;
    }

    public WsProduct totalSalesNum(Integer totalSalesNum) {
        this.totalSalesNum = totalSalesNum;
        return this;
    }

    public void setTotalSalesNum(Integer totalSalesNum) {
        this.totalSalesNum = totalSalesNum;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getPoint() {
        return point;
    }

    public WsProduct point(Integer point) {
        this.point = point;
        return this;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsProduct)) {
            return false;
        }
        return id != null && id.equals(((WsProduct) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WsProduct{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", picture='" + picture + '\'' +
            ", storeId=" + storeId +
            ", price=" + price +
            ", unit='" + unit + '\'' +
            ", remark='" + remark + '\'' +
            ", totalSalesNum=" + totalSalesNum +
            ", point=" + point +
            ", delFlag=" + delFlag +
            '}';
    }
}
