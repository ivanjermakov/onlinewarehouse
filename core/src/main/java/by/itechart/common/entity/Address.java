package by.itechart.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @Column(name = "country")
    private String country;

    @Column(name = "region")
    private String region;

    @Column(name = "locality")
    private String locality;

    public Address(Long id) {
        super(id);
    }
}
