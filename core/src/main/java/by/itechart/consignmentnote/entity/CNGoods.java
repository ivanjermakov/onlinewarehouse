package by.itechart.consignmentnote.entity;

import by.itechart.common.entity.Goods;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CNGoods {

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
