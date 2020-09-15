package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A WsShopCart.
 */
@Entity
@Table(name = "ws_shop_cart")
public class WsShopCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "buyer_id")
    private Long buyerId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "num")
    private Float num;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "status")
    private Boolean status;

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

    public WsShopCart buyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getProductId() {
        return productId;
    }

    public WsShopCart productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Float getNum() {
        return num;
    }

    public WsShopCart num(Float num) {
        this.num = num;
        return this;
    }

    public void setNum(Float num) {
        this.num = num;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public WsShopCart totalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean isStatus() {
        return status;
    }

    public WsShopCart status(Boolean status) {
        this.status = status;
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsShopCart)) {
            return false;
        }
        return id != null && id.equals(((WsShopCart) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WsShopCart{" +
            "id=" + getId() +
            ", buyerId=" + getBuyerId() +
            ", productId=" + getProductId() +
            ", num=" + getNum() +
            ", totalPrice=" + getTotalPrice() +
            ", status='" + isStatus() + "'" +
            "}";
    }
}
