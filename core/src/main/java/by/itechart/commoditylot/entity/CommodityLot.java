package by.itechart.commoditylot.entity;

import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
import by.itechart.counterparty.entity.Counterparty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "creation")
    private LocalDate creation;

    @OneToMany(mappedBy = "commodityLot")
    private List<CommodityLotGoods> commodityLotGoodsList;

    public CommodityLot() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Counterparty getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Counterparty counterparty) {
        this.counterparty = counterparty;
    }

    public CommodityLotType getCommodityLotType() {
        return commodityLotType;
    }

    public void setCommodityLotType(CommodityLotType commodityLotType) {
        this.commodityLotType = commodityLotType;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public void setCreation(LocalDate creation) {
        this.creation = creation;
    }

    public List<CommodityLotGoods> getCommodityLotGoodsList() {
        return commodityLotGoodsList;
    }

    public void setCommodityLotGoodsList(List<CommodityLotGoods> commodityLotGoodsList) {
        this.commodityLotGoodsList = commodityLotGoodsList;
    }
}
