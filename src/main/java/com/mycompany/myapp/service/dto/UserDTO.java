package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsBuyer} entity.
 */
public class UserDTO implements Serializable {

    private String phone;


    private String password;

    private boolean buyerFlag = false;

    public boolean isBuyerFlag() {
        return buyerFlag;
    }

    public void setBuyerFlag(boolean buyerFlag) {
        this.buyerFlag = buyerFlag;
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
            "phone='" + phone + '\'' +
            ", password='" + password + '\'' +
            ", isBuyer=" + buyerFlag +
            '}';
    }
}
