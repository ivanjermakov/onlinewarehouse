package by.itechart.writeoffact.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.writeoffact.enums.WriteOffActType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "write_off_act")
public class WriteOffAct extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "responsible_person")
    private String responsiblePerson;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @OneToMany(mappedBy = "writeOffAct")
    private List<WriteOffActGoods> writeOffActGoodsList;

    @Column(name = "write_off_act_type")
    @Enumerated(EnumType.STRING)
    private WriteOffActType writeOffActType;

    public WriteOffAct() {
    }

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

    public List<WriteOffActGoods> getAGoodsList() {
        return writeOffActGoodsList;
    }

    public void setAGoodsList(List<WriteOffActGoods> writeOffActGoodsList) {
        this.writeOffActGoodsList = writeOffActGoodsList;
    }

    public WriteOffActType getWriteOffActType() {
        return writeOffActType;
    }

    public void setWriteOffActType(WriteOffActType writeOffActType) {
        this.writeOffActType = writeOffActType;
    }
}
