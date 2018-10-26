package by.itechart.common.entity;

import by.itechart.common.enums.MeasurementUnit;
import by.itechart.common.enums.PlacementType;
import by.itechart.company.entity.Company;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "goods")
public class Goods extends BaseEntity {

    @ManyToOne
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

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "deleted")
    private LocalDate deleted;

}
