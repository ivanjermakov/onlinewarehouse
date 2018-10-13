package by.itechart.consignmentnote.entity;

import by.itechart.carrier.entity.Carrier;
import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.entity.Counterparty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ConsignmentNote extends BaseEntity {

    @ManyToOne
    private Company company;
    private String number;
    private LocalDate createDate;
    @ManyToOne
    private Counterparty counterparty;
    @ManyToOne
    private Carrier carrier;
    private String vehicleNumber;
    @ManyToOne
    private User creator;
    private LocalDateTime registrationDateTime;
    @OneToMany
    private List<CNGoods> cnGoodsList;
    private ConsignmentNoteType consignmentNoteType;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LocalDateTime getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(LocalDateTime registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }

    public List<CNGoods> getCnGoodsList() {
        return cnGoodsList;
    }

    public void setCnGoodsList(List<CNGoods> cnGoodsList) {
        this.cnGoodsList = cnGoodsList;
    }

    public ConsignmentNoteType getConsignmentNoteType() {
        return consignmentNoteType;
    }

    public void setConsignmentNoteType(ConsignmentNoteType consignmentNoteType) {
        this.consignmentNoteType = consignmentNoteType;
    }
}
