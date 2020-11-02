package com.mycompany.myapp.domain;


import javax.persistence.*;

import java.io.Serializable;

/**
 * A WsOrderDetails.
 */
@Entity
@Table(name = "ws_order_details")
public class WsOrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "price")
    private Float price;

/*    @Column(name = "status")
    private String status;*/

    @Column(name = "num")
    private Float num;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public WsOrderDetails orderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public WsOrderDetails productId(Long productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Float getPrice() {
        return price;
    }

    public WsOrderDetails price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    /*public String getStatus() {
        return status;
    }

    public WsOrderDetails status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/

    public Float getNum() {
        return num;
    }

    public WsOrderDetails num(Float num) {
        this.num = num;
        return this;
    }

    public void setNum(Float num) {
        this.num = num;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsOrderDetails)) {
            return false;
        }
        return id != null && id.equals(((WsOrderDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WsOrderDetails{" +
            "id=" + getId() +
            ", orderId=" + getOrderId() +
            ", productId=" + getProductId() +
            ", price=" + getPrice() +
//            ", status='" + getStatus() + "'" +
            ", num=" + getNum() +
            "}";
    }
}
