package by.itechart.consignmentnote.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
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
}