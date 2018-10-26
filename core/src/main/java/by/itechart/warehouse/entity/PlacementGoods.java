package by.itechart.warehouse.entity;

import by.itechart.common.entity.BaseEntity;
import by.itechart.common.entity.Goods;
import by.itechart.counterparty.entity.Counterparty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
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

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne
    @JoinColumn(name = "placement_id")
    private Placement placement;

    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "storage_time_days")
    private Integer storageTimeDays;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "deleted")
    private LocalDate deleted;

}