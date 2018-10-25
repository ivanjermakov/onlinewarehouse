package by.itechart.counterparty.entity;

import by.itechart.common.entity.Address;
import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
import by.itechart.counterparty.enums.CounterpartyType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "counterparty")
public class Counterparty extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "counterparty_type")
    @Enumerated(EnumType.STRING)
    private CounterpartyType counterpartyType;

    @Column(name = "tax_number")
    private String taxNumber;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "deleted")
    private LocalDate deleted;

    public Counterparty() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public CounterpartyType getCounterpartyType() {
        return counterpartyType;
    }

    public void setCounterpartyType(CounterpartyType counterpartyType) {
        this.counterpartyType = counterpartyType;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public LocalDate getDeleted() {
        return deleted;
    }

    public void setDeleted(LocalDate deleted) {
        this.deleted = deleted;
    }
}