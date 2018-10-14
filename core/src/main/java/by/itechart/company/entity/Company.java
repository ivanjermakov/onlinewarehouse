package by.itechart.company.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.enums.CompanySize;
import by.itechart.company.enums.WorkStatus;
import by.itechart.warehouse.entity.Warehouse;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "company")
public class Company extends BaseEntity {

    @OneToMany(mappedBy = "company")
    private List<Warehouse> warehouses;

    @OneToMany(mappedBy = "company")
    private List<User> users;

    @Column(name = "company_size")
    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Column(name = "work_status")
    @Enumerated(EnumType.STRING)
    private WorkStatus workStatus;

    @Column(name = "create_date_time")
    private LocalDateTime createDateTime;

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public CompanySize getCompanySize() {
        return companySize;
    }

    public void setCompanySize(CompanySize companySize) {
        this.companySize = companySize;
    }

    public WorkStatus getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(WorkStatus workStatus) {
        this.workStatus = workStatus;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }
}
