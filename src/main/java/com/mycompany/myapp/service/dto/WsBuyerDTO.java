package com.mycompany.myapp.service.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsBuyer} entity.
 */
public class WsBuyerDTO implements Serializable {
    @ApiModelProperty(value = "买家id", required = false)
    private Long id;

    @ApiModelProperty(value = "openid", required = false)
    private String openid;

    @ApiModelProperty(value = "买家名称", required = false)
    private String name;

    @ApiModelProperty(value = "买家手机号", required = false)
    private String phone;

    @ApiModelProperty(value = "买家地址", required = false)
    private String address;

    @ApiModelProperty(value = "买家密码", required = false)
    private String password;

    @ApiModelProperty(value = "管理员密码", required = false)
    private String adminPassword;

    @ApiModelProperty(value = "状态（暂时不用）", required = false)
    private Boolean status;

    @ApiModelProperty(value = "买家余额（充值时作为充值金额使用）", required = false)
    private Float balance=0f;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
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

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsBuyerDTO)) {
            return false;
        }

        return id != null && id.equals(((WsBuyerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "WsBuyerDTO{" +
            "id=" + id +
            ", openid='" + openid + '\'' +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", password='" + password + '\'' +
            ", adminPassword='" + adminPassword + '\'' +
            ", status=" + status +
            ", balance=" + balance +
            '}';
    }
}
