package by.itechart.writeoffact.dto;

import by.itechart.writeoffact.enums.WriteOffType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateWriteOffActGoodsDto {
    private Long goodsId;
    @NotNull
    private WriteOffType writeOffType;
    @NotNull
    private Integer amount;
}
