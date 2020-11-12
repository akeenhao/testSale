package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsStore} entity.
 */
public class WsStoreDTO implements Serializable {

    @ApiModelProperty(value = "门店id", required = false)
    private Long id;

    @NotNull(message = "门店名不能为空")
    @ApiModelProperty(value = "门店名", required = false)
    private String name;

    @NotNull(message = "店主不能为空")
    @ApiModelProperty(value = "门店店主名", required = false)
    private String personName;

    @NotNull(message = "店主电话不能为空")
    @ApiModelProperty(value = "门店店主电话", required = false)
    private String phone;

    @NotNull(message = "店主地址不能为空")
    @ApiModelProperty(value = "门店地址", required = false)
    private String address;

    @ApiModelProperty(value = "门店当前余额", required = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @ApiModelProperty(value = "门店当前状态", required = false)
    private Boolean status = true;

    @ApiModelProperty(value = "门店照片", required = false)
    private String picture;

    @NotNull(message = "登陆密码不能为空")
    @ApiModelProperty(value = "登陆密码", required = false)
    private String password;

    @ApiModelProperty(value = "订单总数", required = false)
    private Integer totalOrderNum = 0;

    @ApiModelProperty(value = "门店评分", required = false)
    private Integer point = 0;

    @ApiModelProperty(value = "开店日期", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant openTime = Instant.now();

    @NotNull(message = "门店区域不能为空")
    @ApiModelProperty(value = "门店区域", required = false)
    private Long areaId;

    @ApiModelProperty(value = "门店区域名称", required = false)
    private String areaName;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getTotalOrderNum() {
        return totalOrderNum;
    }

    public void setTotalOrderNum(Integer totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Instant getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Instant openTime) {
        this.openTime = openTime;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsStoreDTO)) {
            return false;
        }

        return id != null && id.equals(((WsStoreDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WsStoreDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", personName='" + getPersonName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            ", balance=" + getBalance() +
            ", status='" + isStatus() + "'" +
            ", password='" + getPassword() + "'" +
            ", picture='" + getPicture() + "'" +
            ", totalOrderNum=" + getTotalOrderNum() +
            ", point=" + getPoint() +
            ", openTime='" + getOpenTime() + "'" +
            ", areaId=" + getAreaId() +
            "}";
    }
}
