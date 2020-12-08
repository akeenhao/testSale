package com.mycompany.myapp.service.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsBuyer} entity.
 */
public class UserDTO implements Serializable {
    @ApiModelProperty(value = "id", required = false)
    private Long id;
    @ApiModelProperty(value = "手机号", required = false)
    private String phone;
    @ApiModelProperty(value = "openid", required = false)
    private String openid;
    @ApiModelProperty(value = "密码", required = false)
    private String password;
    @ApiModelProperty(value = "是否是买家", required = false)
    private boolean buyerFlag = false;
    @ApiModelProperty(value = "token", required = false)
    private String token;
    @ApiModelProperty(value = "返回信息", required = false)
    private String msg;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public boolean isBuyerFlag() {
        return buyerFlag;
    }

    public void setBuyerFlag(boolean buyerFlag) {
        this.buyerFlag = buyerFlag;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(phone, userDTO.phone) &&
            Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(phone, password);
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "id=" + id +
            ", phone='" + phone + '\'' +
            ", openid='" + openid + '\'' +
            ", password='" + password + '\'' +
            ", buyerFlag=" + buyerFlag +
            ", token='" + token + '\'' +
            ", msg='" + msg + '\'' +
            '}';
    }
}
