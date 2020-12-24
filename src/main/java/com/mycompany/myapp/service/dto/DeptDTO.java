package com.mycompany.myapp.service.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.WsArea} entity.
 */
public class DeptDTO implements Serializable {
    @ApiModelProperty(value = "名称", required = false)
    private String deptName;

    @ApiModelProperty(value = "区域地址", required = false)
    private String deptNo;

    @ApiModelProperty(value = "区域描述", required = false)
    private String editUser;


    @Override
    public String toString() {
        return "DeptDTO{" +
            "deptName='" + deptName + '\'' +
            ", deptNo='" + deptNo + '\'' +
            ", editUser='" + editUser + '\'' +
            '}';
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }
}
