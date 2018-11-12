package by.itechart.warehouse.dto;

import by.itechart.common.dto.GoodsDto;
import by.itechart.counterparty.dto.CounterpartyDto;
import lombok.Data;

@Data
public class PlacementGoodsDto {

    private GoodsDto goods;
    private CounterpartyDto counterparty;
    private Integer amount;
    private Integer storageTimeDays;
}
