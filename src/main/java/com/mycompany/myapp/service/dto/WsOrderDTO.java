package com.mycompany.myapp.service.dto;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsOrder} entity.
 */
public class WsOrderDTO implements Serializable {

    private Long id;

    private Long buyerId;
    private String buyerName;
    private String buyerAddr;
    private String buyerTel;
    private Long storeId;
    private String storeName;

//    private Long parkerId;

    private Float totalPrice;

    private String status;

    private Date createTime;

    private Date updateTime;

    private List<WsOrderDetailsDTO> details;


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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<WsOrderDetailsDTO> getDetails() {
        return details;
    }

    public void setDetails(List<WsOrderDetailsDTO> details) {
        this.details = details;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddr() {
        return buyerAddr;
    }

    public String getBuyerTel() {
        return buyerTel;
    }

    public void setBuyerTel(String buyerTel) {
        this.buyerTel = buyerTel;
    }

    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }
/*
    public Long getParkerId() {
        return parkerId;
    }

    public void setParkerId(Long parkerId) {
        this.parkerId = parkerId;
    }*/

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsOrderDTO)) {
            return false;
        }

        return id != null && id.equals(((WsOrderDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WsOrderDTO{" +
            "id=" + id +
            ", buyerId=" + buyerId +
            ", buyerName='" + buyerName + '\'' +
            ", storeId=" + storeId +
            ", storeName='" + storeName + '\'' +
//            ", parkerId=" + parkerId +
            ", totalPrice=" + totalPrice +
            ", status='" + status + '\'' +
            ", details=" + details +
            '}';
    }
}
