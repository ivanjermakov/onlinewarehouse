package by.itechart.common.entity;

import javax.persistence.Entity;

@Entity
public class Role extends BaseEntity {

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
