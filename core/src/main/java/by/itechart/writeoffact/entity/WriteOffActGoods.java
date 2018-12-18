package by.itechart.writeoffact.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;
import by.itechart.writeoffact.enums.WriteOffType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "write_off_act_goods")
public class WriteOffActGoods extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "write_off_act_id")
    private WriteOffAct writeOffAct;

    @Column(name = "write_off_type")
    @Enumerated(EnumType.STRING)
    private WriteOffType writeOffType;

    @Column(name = "amount")
    private Integer amount;
}