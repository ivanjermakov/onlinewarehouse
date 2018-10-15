package by.itechart.writeoffact.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;
import by.itechart.writeoffact.enums.WriteOffType;

import javax.persistence.*;

@Entity
@Table(name = "write_off_act_goods")
public class WriteOffActGoods extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "write_off_act_id")
    private WriteOffAct writeOffAct;

    @Column(name = "write_off_type")
    @Enumerated(EnumType.STRING)
    private WriteOffType writeOffType;

    @Column(name = "amount")
    private Integer amount;

    public WriteOffActGoods() {
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public WriteOffAct getWriteOffAct() {
        return writeOffAct;
    }

    public void setWriteOffAct(WriteOffAct writeOffAct) {
        this.writeOffAct = writeOffAct;
    }

    public WriteOffType getWriteOffType() {
        return writeOffType;
    }

    public void setWriteOffType(WriteOffType writeOffType) {
        this.writeOffType = writeOffType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}