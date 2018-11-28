package by.itechart.warehouse.entity;

import by.itechart.common.entity.Address;
import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "warehouse")
@Document(indexName = "warehouse", type = "warehouses")
public class Warehouse extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "warehouse", fetch = FetchType.LAZY)
    private List<Placement> placements;

    @OneToOne(fetch = FetchType.LAZY)
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
