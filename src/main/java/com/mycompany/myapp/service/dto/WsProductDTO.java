package com.mycompany.myapp.service.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsProduct} entity.
 */
public class WsProductDTO implements Serializable {

    @ApiModelProperty(value = "id", required = false)
    private Long id;

    @NotNull(message = "商品名不能为空")
    @ApiModelProperty(value = "商品名", required = false)
    private String name;

    @ApiModelProperty(value = "商品照片", required = false)
    private String picture;

    @NotNull(message = "门店不能为空")
    @ApiModelProperty(value = "门店id", required = false)
    private Long storeId;

    @ApiModelProperty(value = "门店名", required = false)
    private String storeName;

    @NotNull(message = "单价不能为空")
    @ApiModelProperty(value = "单价", required = false)
    private Float price;

    @NotNull(message = "规格不能为空")
    @ApiModelProperty(value = "规格：（例如斤、个）", required = false)
    private String unit;

    @ApiModelProperty(value = "备注", required = false)
    private String remark;

    @ApiModelProperty(value = "总销量", required = false)
    private Integer totalSalesNum;

    @ApiModelProperty(value = "评分", required = false)
    private Integer point;

    @ApiModelProperty(value = "删除标识", required = false)
    private boolean delFlag;


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTotalSalesNum() {
        return totalSalesNum;
    }

    public void setTotalSalesNum(Integer totalSalesNum) {
        this.totalSalesNum = totalSalesNum;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsProductDTO)) {
            return false;
        }

        return id != null && id.equals(((WsProductDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WsProductDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", picture='" + picture + '\'' +
            ", storeId=" + storeId +
            ", storeName='" + storeName + '\'' +
            ", price=" + price +
            ", unit='" + unit + '\'' +
            ", remark='" + remark + '\'' +
            ", totalSalesNum=" + totalSalesNum +
            ", point=" + point +
            ", delFlag=" + delFlag +
            '}';
    }
}
