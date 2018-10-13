package by.itechart.common.entity;

import by.itechart.common.enums.PlacementType;
import by.itechart.company.entity.Company;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Goods extends BaseEntity {

    private String description;
    private String labelling;
    private String name;
    private Double weight;
    @Enumerated(EnumType.STRING)
    private PlacementType placementType;
    private Integer cost;
    @ManyToOne
    private Company company;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabelling() {
        return labelling;
    }

    public void setLabelling(String labelling) {
        this.labelling = labelling;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
