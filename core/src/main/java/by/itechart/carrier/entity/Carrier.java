package by.itechart.carrier.entity;

import by.itechart.carrier.enums.CarrierType;
import by.itechart.common.entity.Address;
import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
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
@Table(name = "carrier")
public class Carrier extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "carrier_type")
    @Enumerated(EnumType.STRING)
    private CarrierType carrierType;

    @Column(name = "tax_number")
    private String taxNumber;

    @OneToMany(mappedBy = "carrier", fetch = FetchType.LAZY)
    private List<Driver> drivers;

    @Column(name = "trusted")
    private Boolean trusted;

    @Column(name = "deleted")
    private LocalDate deleted;
}
