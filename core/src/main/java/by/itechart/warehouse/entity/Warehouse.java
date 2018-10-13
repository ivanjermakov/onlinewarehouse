package by.itechart.warehouse.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Warehouse extends BaseEntity {

    @ManyToOne
    private Company company;
    @OneToMany
    private List<Placement> placements;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Placement> getPlacements() {
        return placements;
    }

    public void setPlacements(List<Placement> placements) {
        this.placements = placements;
    }
}
