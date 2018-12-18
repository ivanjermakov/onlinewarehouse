package by.itechart.company.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.dto.CompanyDto;
import by.itechart.company.enums.CompanySize;
import by.itechart.warehouse.entity.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "company")
@Document(indexName = "warehouse", type = "companies")
public class Company extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "size_type")
    @Enumerated(EnumType.STRING)
    private CompanySize sizeType;

    @Column(name = "logo")
    private String logo;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Warehouse> warehouses;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<User> users;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
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