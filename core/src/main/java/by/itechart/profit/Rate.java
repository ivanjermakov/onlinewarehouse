package by.itechart.profit;

import by.itechart.common.entity.BaseEntity;
import by.itechart.company.enums.CompanySize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rate")
public class Rate extends BaseEntity {

    private CompanySize companySize;
    private Long rate;
    @Column(unique = true)
    private LocalDateTime applyDate;
    private LocalDateTime deleted;
}
