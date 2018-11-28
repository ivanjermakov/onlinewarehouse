package by.itechart.warehouse.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;
import by.itechart.counterparty.entity.Counterparty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "placement_goods")
public class PlacementGoods extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placement_id")
    private Placement placement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "storage_time_days")
    private Integer storageTimeDays;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "deleted")
    private LocalDate deleted;

}