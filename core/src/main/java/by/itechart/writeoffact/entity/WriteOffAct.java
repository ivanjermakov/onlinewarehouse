package by.itechart.writeoffact.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class WriteOffAct extends BaseEntity {

    @ManyToOne
    private Company company;
    private LocalDate createDate;
    @ManyToOne
    private User creator;
    private String responsiblePerson;
    private Integer totalAmount;
    @OneToMany
    private List<AGoods> aGoodsList;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<AGoods> getAGoodsList() {
        return aGoodsList;
    }

    public void setAGoodsList(List<AGoods> aGoodsList) {
        this.aGoodsList = aGoodsList;
    }
}
