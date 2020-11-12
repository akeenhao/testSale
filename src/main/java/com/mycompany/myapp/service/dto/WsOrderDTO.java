package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsOrder} entity.
 */
public class WsOrderDTO implements Serializable {
    @ApiModelProperty(value = "订单id", required = false)
    private Long id;

    @ApiModelProperty(value = "买家id", required = false)
    private Long buyerId;
    @ApiModelProperty(value = "买家姓名", required = false)
    private String buyerName;
    @ApiModelProperty(value = "买家地址", required = false)
    private String buyerAddr;
    @ApiModelProperty(value = "买家电话", required = false)
    private String buyerTel;
    @ApiModelProperty(value = "卖家id", required = false)
    private Long storeId;
    @ApiModelProperty(value = "卖家名", required = false)
    private String storeName;

//    private Long parkerId;

    @ApiModelProperty(value = "总金额", required = false)
    private Float totalPrice;

    @ApiModelProperty(value = "订单状态（未付款、等待商家接单、取消、删除、备货中、送货中、已签收）", required = false)
    private String status;
    @ApiModelProperty(value = "备注", required = false)
    private String remark;

    @ApiModelProperty(value = "创建时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "订单明细", required = false)
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
            ", buyerAddr='" + buyerAddr + '\'' +
            ", buyerTel='" + buyerTel + '\'' +
            ", storeId=" + storeId +
            ", storeName='" + storeName + '\'' +
            ", totalPrice=" + totalPrice +
            ", status='" + status + '\'' +
            ", remark='" + remark + '\'' +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", details=" + details +
            '}';
    }
}
