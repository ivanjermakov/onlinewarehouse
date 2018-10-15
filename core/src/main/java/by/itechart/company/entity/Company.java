package by.itechart.company.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.enums.CompanySize;
import by.itechart.warehouse.entity.Warehouse;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company")
public class Company extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "size_type")
    @Enumerated(EnumType.STRING)
    private CompanySize sizeType;

    @OneToMany(mappedBy = "company")
    private List<Warehouse> warehouses;

    @OneToMany(mappedBy = "company")
    private List<User> users;

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanySize getSizeType() {
        return sizeType;
    }

    public void setSizeType(CompanySize sizeType) {
        this.sizeType = sizeType;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}