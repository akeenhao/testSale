package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Saler} entity.
 */
public class SalerDTO implements Serializable {
    
    private Long id;

    private String saleName;

    private String pic;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SalerDTO)) {
            return false;
        }

        return id != null && id.equals(((SalerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SalerDTO{" +
            "id=" + getId() +
            ", saleName='" + getSaleName() + "'" +
            ", pic='" + getPic() + "'" +
            "}";
    }
}
