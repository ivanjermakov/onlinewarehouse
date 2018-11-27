package by.itechart.writeoffact.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.writeoffact.enums.WriteOffActType;
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
@Table(name = "write_off_act")
public class WriteOffAct extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "creation")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate creation;

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

    public WriteOffAct(Long id) {
        super(id);
    }
}
