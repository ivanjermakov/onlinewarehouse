package by.itechart.warehouse.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;

import javax.persistence.*;

@Entity
@Table(name = "placement_goods")
public class PlacementGoods extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "placement_id")
    private Placement placement;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "storage_time_days")
    private Integer storageTimeDays;

    public PlacementGoods() {
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStorageTimeDays() {
        return storageTimeDays;
    }

    public void setStorageTimeDays(Integer storageTimeDays) {
        this.storageTimeDays = storageTimeDays;
    }
}