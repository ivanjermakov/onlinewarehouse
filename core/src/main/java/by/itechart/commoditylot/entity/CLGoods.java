package by.itechart.commoditylot.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "c_l_goods")
public class CLGoods extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "commodity_lot_id")
    private CommodityLot commodityLot;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CommodityLot getCommodityLot() {
        return commodityLot;
    }

    public void setCommodityLot(CommodityLot commodityLot) {
        this.commodityLot = commodityLot;
    }
}
