package by.itechart.commoditylot.entity;

import by.itechart.commoditylot.enums.CommodityLotType;
import by.itechart.common.entity.BaseEntity;
import by.itechart.company.entity.Company;
import by.itechart.counterparty.entity.Counterparty;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class CommodityLot extends BaseEntity {

    @ManyToOne
    private Company company;
    @OneToMany
    private List<CLGoods> clGoodsList;
    @ManyToOne
    private Counterparty counterparty;
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
