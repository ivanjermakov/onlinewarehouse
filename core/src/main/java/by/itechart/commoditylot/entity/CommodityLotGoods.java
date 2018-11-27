package by.itechart.commoditylot.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "commodity_lot_goods")
public class CommodityLotGoods extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "commodity_lot_id")
    private CommodityLot commodityLot;

    @Column(name = "amount")
    private Integer amount;
}
