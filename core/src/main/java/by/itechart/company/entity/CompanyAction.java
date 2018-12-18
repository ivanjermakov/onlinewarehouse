package by.itechart.company.entity;

import by.itechart.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "company_action")
public class CompanyAction extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "date_start")
    private LocalDateTime start;

    @Column(name = "date_end")
    private LocalDateTime end;
}
