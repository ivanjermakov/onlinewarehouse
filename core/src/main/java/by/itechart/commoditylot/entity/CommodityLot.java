package by.itechart.commoditylot.entity;

import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
import by.itechart.counterparty.entity.Counterparty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "commodity_lot")
public class CommodityLot extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "commodityLot")
    private List<CLGoods> clGoodsList;

    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    @Column(name = "commodity_lot_type")
    @Enumerated(EnumType.STRING)
    private CommodityLotType commodityLotType;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<CLGoods> getClGoodsList() {
        return clGoodsList;
    }

    public void setClGoodsList(List<CLGoods> clGoodsList) {
        this.clGoodsList = clGoodsList;
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
}
