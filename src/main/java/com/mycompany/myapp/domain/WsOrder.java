package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * A WsOrder.
 */
@Entity
@Table(name = "ws_order")
public class WsOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "store_id")
    private Long storeId;
/*
    @Column(name = "parker_id")
    private Long parkerId;*/

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "remark")
    private String remark;


    @Column(name = "create_time")
    private Date createTime;


    @Column(name = "update_time")
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public WsOrder buyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    /*public Long getParkerId() {
        return parkerId;
    }

    public WsOrder parkerId(Long parkerId) {
        this.parkerId = parkerId;
        return this;
    }

    public void setParkerId(Long parkerId) {
        this.parkerId = parkerId;
    }*/

    public Float getTotalPrice() {
        return totalPrice;
    }

    public WsOrder totalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public WsOrder status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsOrder)) {
            return false;
        }
        return id != null && id.equals(((WsOrder) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WsOrder{" +
            "id=" + id +
            ", buyerId=" + buyerId +
            ", storeId=" + storeId +
            ", totalPrice=" + totalPrice +
            ", status='" + status + '\'' +
            ", remark='" + remark + '\'' +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            '}';
    }
}
