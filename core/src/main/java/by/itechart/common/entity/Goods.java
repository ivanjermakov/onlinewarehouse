package by.itechart.common.entity;

import by.itechart.common.enums.PlacementType;
import by.itechart.company.entity.Company;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

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

    public Goods() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlacementType getPlacementType() {
        return placementType;
    }

    public void setPlacementType(PlacementType placementType) {
        this.placementType = placementType;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getLabelling() {
        return labelling;
    }

    public void setLabelling(String labelling) {
        this.labelling = labelling;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeleted() {
        return deleted;
    }

    public void setDeleted(LocalDate deleted) {
        this.deleted = deleted;
    }
}
