package by.itechart.company.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.company.enums.ActionType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "company_action")
public class CompanyAction extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "change")
    private LocalDateTime change;

    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    public CompanyAction() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocalDateTime getChange() {
        return change;
    }

    public void setChange(LocalDateTime change) {
        this.change = change;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}