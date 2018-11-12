package by.itechart.warehouse.dto;

import by.itechart.common.dto.GoodsDto;
import lombok.Data;

@Data
public class PlacementGoodsDto {

    private Integer amount;
    private GoodsDto goods;
    private Long counterpartyId;
    private Integer storageTimeDays;
}
