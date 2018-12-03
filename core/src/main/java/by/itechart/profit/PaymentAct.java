package by.itechart.profit;

import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payment_act")
public class PaymentAct extends BaseEntity {

    private LocalDateTime paymentDate;
    private Company company;
    private Rate rateExacted;
    private LocalDateTime deleted;

}
