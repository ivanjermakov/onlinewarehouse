package by.itechart.warehouse.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "warehouse")
public class Warehouse extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "warehouse")
    private List<Placement> placements;

    public Warehouse() {
    }

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
