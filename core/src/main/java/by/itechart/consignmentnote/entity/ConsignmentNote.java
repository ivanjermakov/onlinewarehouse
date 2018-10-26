package by.itechart.consignmentnote.entity;

import by.itechart.carrier.entity.Carrier;
import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.consignmentnote.enums.ConsignmentNoteType;
import by.itechart.counterparty.entity.Counterparty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "consignment_note")
public class ConsignmentNote extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "number")
    private String number;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
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

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "registration")
    private LocalDate registration;

    @OneToMany(mappedBy = "consignmentNote")
    private List<ConsignmentNoteGoods> consignmentNoteGoodsList;

    @Column(name = "consignment_note_type")
    @Enumerated(EnumType.STRING)
    private ConsignmentNoteType consignmentNoteType;

}
