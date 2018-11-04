package by.itechart.writeoffact.dto;

import by.itechart.common.dto.GoodsDto;
import by.itechart.writeoffact.enums.WriteOffType;
import lombok.Data;

@Data
public class WriteOffActGoodsDto {

    private Long id;
    private GoodsDto goods;
    WriteOffActDto writeOffAct;
    WriteOffType writeOffType;
    Integer amount;
}
