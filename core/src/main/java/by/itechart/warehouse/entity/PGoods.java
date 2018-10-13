package by.itechart.warehouse.entity;

import by.itechart.common.entity.Goods;

import javax.persistence.Entity;

@Entity
public class PGoods {
    private Goods goods;
    private Integer count;
    private Integer storageTimeDays;

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

    public Integer getStorageTimeDays() {
        return storageTimeDays;
    }

    public void setStorageTimeDays(Integer storageTimeDays) {
        this.storageTimeDays = storageTimeDays;
    }
}
