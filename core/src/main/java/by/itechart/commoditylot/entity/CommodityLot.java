package by.itechart.commoditylot.entity;

import by.itechart.commoditylot.enums.CommodityLotStatus;
import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
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
@Table(name = "commodity_lot")
public class CommodityLot extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    @Column(name = "commodity_lot_type")
    @Enumerated(EnumType.STRING)
    private CommodityLotType commodityLotType;

    @Column(name = "commodity_lot_status")
    @Enumerated(EnumType.STRING)
    private CommodityLotStatus commodityLotStatus;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "creation")
    private LocalDate creation;

    @OneToMany(mappedBy = "commodityLot")
    private List<CommodityLotGoods> commodityLotGoodsList;
}
