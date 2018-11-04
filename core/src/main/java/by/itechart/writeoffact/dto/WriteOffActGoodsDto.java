package by.itechart.writeoffact.dto;

import by.itechart.writeoffact.enums.WriteOffType;
import lombok.Data;

@Data
public class WriteOffActGoodsDto {

    private Long goodsId;
    private WriteOffType writeOffType;
    private Integer amount;
}
