package by.itechart.common.entity;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import by.itechart.company.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "goods")
@Document(indexName = "warehouse", type = "goods")
public class Goods extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "placement_type")
    @Enumerated(EnumType.STRING)
    private PlacementType placementType;

    @Column(name = "measurement_unit_type")
    @Enumerated(EnumType.STRING)
    private MeasurementUnit measurementUnit;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "labelling")
    private String labelling;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted")
    private LocalDate deleted;

    public Goods(Long id) {
        super(id);
    }
}
