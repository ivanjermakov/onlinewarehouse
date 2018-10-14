package by.itechart.writeoffact.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;
import by.itechart.writeoffact.enums.WriteOffType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "a_goods")
public class AGoods extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Column(name = "count")
    private Integer count;

    @Column(name = "write_off_type")
    @Enumerated(EnumType.STRING)
    private WriteOffType writeOffType;

    @ManyToOne
    @JoinColumn(name = "write_off_act_id")
    private WriteOffAct writeOffAct;

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

    public WriteOffAct getWriteOffAct() {
        return writeOffAct;
    }

    public void setWriteOffAct(WriteOffAct writeOffAct) {
        this.writeOffAct = writeOffAct;
    }
}
