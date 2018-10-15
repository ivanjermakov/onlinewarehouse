package by.itechart.consignmentnote.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;

import javax.persistence.*;

@Entity
@Table(name = "consignment_note_goods")
public class ConsignmentNoteGoods extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "consignment_note_id")
    private ConsignmentNote consignmentNote;

    @Column(name = "amount")
    private Integer amount;

    public ConsignmentNoteGoods() {
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public ConsignmentNote getConsignmentNote() {
        return consignmentNote;
    }

    public void setConsignmentNote(ConsignmentNote consignmentNote) {
        this.consignmentNote = consignmentNote;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}