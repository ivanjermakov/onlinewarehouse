package by.itechart.commoditylot.dto;

import by.itechart.common.dto.GoodsDto;
import lombok.Data;

@Data
public class CommodityLotGoodsDto {
    private Integer amount;
    private GoodsDto goods;
}
