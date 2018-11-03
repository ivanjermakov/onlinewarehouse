package by.itechart.writeoffact.dto;

import by.itechart.common.dto.GoodsDto;
import by.itechart.writeoffact.entity.WriteOffActGoods;
import by.itechart.writeoffact.enums.WriteOffType;
import lombok.Data;

@Data
public class WriteOffActGoodsDto {

    private Long id;
    private GoodsDto goods;
    WriteOffActDto writeOffAct;
    WriteOffType writeOffType;
    Integer amount;

    public WriteOffActGoodsDto(WriteOffActGoods writeOffActGoods) {
        this.id = writeOffActGoods.getId();
        this.goods = new GoodsDto(writeOffActGoods.getGoods());
        this.writeOffAct = new WriteOffActDto(writeOffActGoods.getWriteOffAct());
        this.writeOffType = writeOffActGoods.getWriteOffType();
        this.amount = writeOffActGoods.getAmount();
    }
}
