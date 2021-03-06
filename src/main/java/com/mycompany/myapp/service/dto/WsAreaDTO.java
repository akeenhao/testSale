package com.mycompany.myapp.service.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsArea} entity.
 */
public class WsAreaDTO implements Serializable {
    @ApiModelProperty(value = "区域id", required = false)
    private Long id;

    @ApiModelProperty(value = "区域名称", required = false)
    private String name;

    @ApiModelProperty(value = "区域地址", required = false)
    private String address;

    @ApiModelProperty(value = "区域描述", required = false)
    private String remark;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WsAreaDTO)) {
            return false;
        }

        return id != null && id.equals(((WsAreaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WsAreaDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
