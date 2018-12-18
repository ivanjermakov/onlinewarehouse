package by.itechart.writeoffact.dto;

import by.itechart.common.dto.GoodsDto;
import by.itechart.writeoffact.enums.WriteOffType;
import lombok.Data;

@Data
public class WriteOffActGoodsDto {
    private GoodsDto goods;
    private WriteOffType writeOffType;
    private Integer amount;
}
