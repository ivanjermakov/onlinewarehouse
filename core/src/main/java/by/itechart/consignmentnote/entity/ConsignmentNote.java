package by.itechart.consignmentnote.entity;

import by.itechart.carrier.entity.Carrier;
import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.entity.Counterparty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "consignment_note")
public class ConsignmentNote extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "number")
    private String number;

    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    @ManyToOne
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "registration_date_time")
    private LocalDateTime registrationDateTime;

    @OneToMany(mappedBy = "consignmentNote")
    private List<ConsignmentNoteGoods> consignmentNoteGoodsList;

    @Column(name = "consignment_note_type")
    @Enumerated(EnumType.STRING)
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

    public List<ConsignmentNoteGoods> getConsignmentNoteGoodsList() {
        return consignmentNoteGoodsList;
    }

    public void setConsignmentNoteGoodsList(List<ConsignmentNoteGoods> consignmentNoteGoodsList) {
        this.consignmentNoteGoodsList = consignmentNoteGoodsList;
    }

    public ConsignmentNoteType getConsignmentNoteType() {
        return consignmentNoteType;
    }

    public void setConsignmentNoteType(ConsignmentNoteType consignmentNoteType) {
        this.consignmentNoteType = consignmentNoteType;
    }
}
