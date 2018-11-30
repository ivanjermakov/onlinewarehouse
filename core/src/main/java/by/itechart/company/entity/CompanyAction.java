package by.itechart.company.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.company.enums.ActionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @Column(name = "change")
    private LocalDateTime change;

    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
}
