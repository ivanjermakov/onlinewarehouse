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
}
