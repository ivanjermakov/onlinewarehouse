package by.itechart.consignmentnote.entity;

import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.entity.Driver;
import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.enums.ConsignmentNoteStatus;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.entity.Counterparty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "consignment_note")
@Document(indexName = "warehouse", type = "consignmentNotes")
public class ConsignmentNote extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "number")
    private String number;

    @Column(name = "shipment")
    private LocalDate shipment;

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

    @Column(name = "registration")
    private LocalDate registration;

    @OneToMany(mappedBy = "consignmentNote")
    private List<ConsignmentNoteGoods> consignmentNoteGoodsList;

    @Column(name = "consignment_note_type")
    @Enumerated(EnumType.STRING)
    private ConsignmentNoteType consignmentNoteType;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "consignment_note_status")
    @Enumerated(EnumType.STRING)
    private ConsignmentNoteStatus consignmentNoteStatus;

    @Column(name = "description")
    private String description;

    public ConsignmentNote(Long id) {
        super(id);
    }
}
