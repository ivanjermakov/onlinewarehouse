package by.itechart.writeoffact.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;
import by.itechart.writeoffact.enums.WriteOffType;

import javax.persistence.ManyToOne;

public class AGoods extends BaseEntity {

    @ManyToOne
    private Goods goods;
    private Integer count;
    private WriteOffType writeOffType;

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

    public WriteOffType getWriteOffType() {
        return writeOffType;
    }

    public void setWriteOffType(WriteOffType writeOffType) {
        this.writeOffType = writeOffType;
    }
}
