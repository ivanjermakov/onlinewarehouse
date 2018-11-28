package by.itechart.counterparty.entity;

import by.itechart.common.entity.Address;
import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
import by.itechart.counterparty.enums.CounterpartyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "counterparty")
@Document(indexName = "warehouse", type = "counterparties")
public class Counterparty extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "counterparty_type")
    @Enumerated(EnumType.STRING)
    private CounterpartyType counterpartyType;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "deleted")
    private LocalDate deleted;
}