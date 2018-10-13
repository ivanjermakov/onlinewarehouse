package by.itechart.commoditylot.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CLGoods extends BaseEntity {

    @OneToOne
    private Goods goods;
    private Integer count;

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
}
