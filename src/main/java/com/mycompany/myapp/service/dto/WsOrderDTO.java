package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsOrder} entity.
 */
public class WsOrderDTO implements Serializable {

    private Long id;

    private Long buyerId;
    private String buyerName;

    private Long storeId;
    private String storeName;

    private Long parkerId;

    private Float totalPrice;

    private String status;

    private List<WsOrderDetailsDTO> details;

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

    public Long getParkerId() {
        return parkerId;
    }

    public void setParkerId(Long parkerId) {
        this.parkerId = parkerId;
    }

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
            ", storeId=" + storeId +
            ", storeName='" + storeName + '\'' +
            ", parkerId=" + parkerId +
            ", totalPrice=" + totalPrice +
            ", status='" + status + '\'' +
            ", details=" + details +
            '}';
    }
}
