package by.itechart.writeoffact.dto;

import by.itechart.warehouse.dto.PlacementGoodsDto;
import by.itechart.writeoffact.enums.WriteOffType;
import lombok.Data;

@Data
public class PlacementWriteOffActGoodsDto {
    private PlacementGoodsDto placementGoods;
    private WriteOffType writeOffType;
    private Integer amount;
}
