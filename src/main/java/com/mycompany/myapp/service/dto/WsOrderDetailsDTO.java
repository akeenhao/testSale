package com.mycompany.myapp.service.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsOrderDetails} entity.
 */
public class WsOrderDetailsDTO implements Serializable {
    @ApiModelProperty(value = "订单明细id", required = false)
    private Long id;

    @ApiModelProperty(value = "订单id", required = false)
    private Long orderId;

    @ApiModelProperty(value = "商品id", required = false)
    private Long productId;

    @ApiModelProperty(value = "商品名", required = false)
    private String productName;

    @ApiModelProperty(value = "商品价格", required = false)
    private Float price;

//    private String status;

    @ApiModelProperty(value = "商品数量", required = false)
    private Float num;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

/*    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/

    public Float getNum() {
        return num;
    }

    public void setNum(Float num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsOrderDetailsDTO)) {
            return false;
        }

        return id != null && id.equals(((WsOrderDetailsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WsOrderDetailsDTO{" +
            "id=" + getId() +
            ", orderId=" + getOrderId() +
            ", productId=" + getProductId() +
            ", price=" + getPrice() +
//            ", status='" + getStatus() + "'" +
            ", num=" + getNum() +
            "}";
    }
}
