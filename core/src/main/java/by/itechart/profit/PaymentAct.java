package by.itechart.profit;

import by.itechart.common.entity.BaseEntity;
import by.itechart.warehouse.entity.Placement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_act")
public class PaymentAct extends BaseEntity {

    @Column(name = "creation")
    private LocalDate creation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placement_id")
    private Placement placement;

    @Column(name = "amount")
    private Integer amount;
}
