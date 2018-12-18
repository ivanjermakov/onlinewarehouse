package by.itechart.commoditylot.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateCommodityLotGoodsDto {
    @NotNull
    @Positive
    private Integer amount;
    private Long goodsId;
}
