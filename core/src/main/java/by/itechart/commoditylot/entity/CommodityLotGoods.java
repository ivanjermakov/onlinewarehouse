package by.itechart.commoditylot.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;

import javax.persistence.*;

@Entity
@Table(name = "commodity_lot_goods")
public class CommodityLotGoods extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "commodity_lot_id")
    private CommodityLot commodityLot;

    @Column(name = "amount")
    private Integer amount;

    public CommodityLotGoods() {
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public CommodityLot getCommodityLot() {
        return commodityLot;
    }

    public void setCommodityLot(CommodityLot commodityLot) {
        this.commodityLot = commodityLot;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
