package by.itechart.company.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.dto.CompanyDto;
import by.itechart.company.enums.CompanySize;
import by.itechart.warehouse.entity.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "company")
    private List<CompanyAction> companyActions;

    public Company(Long id) {
        super(id);
    }

    public Company(CompanyDto company) {
        setId(company.getId());
        setName(company.getName());
        setSizeType(company.getSizeType());
    }
}