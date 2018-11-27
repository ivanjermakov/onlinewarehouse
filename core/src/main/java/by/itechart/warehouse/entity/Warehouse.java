package by.itechart.warehouse.entity;

import by.itechart.common.entity.Address;
import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "warehouse")
public class Warehouse extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "warehouse")
    private List<Placement> placements;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "name")
    private String name;

    @Column(name = "deleted")
    private LocalDate deleted;

    public Warehouse(Long id) {
        super(id);
    }
}
