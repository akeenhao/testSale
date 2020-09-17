package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsShopCart} entity.
 */
public class WsShopCartDTO implements Serializable {

    private Long id;

    private Long buyerId;
    private String buyerName;

    private Long productId;
    private String productName;

    private Float num;

    private Float totalPrice;

    private Boolean status;

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Float getNum() {
        return num;
    }

    public void setNum(Float num) {
        this.num = num;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsShopCartDTO)) {
            return false;
        }

        return id != null && id.equals(((WsShopCartDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WsShopCartDTO{" +
            "id=" + getId() +
            ", buyerId=" + getBuyerId() +
            ", productId=" + getProductId() +
            ", num=" + getNum() +
            ", totalPrice=" + getTotalPrice() +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
