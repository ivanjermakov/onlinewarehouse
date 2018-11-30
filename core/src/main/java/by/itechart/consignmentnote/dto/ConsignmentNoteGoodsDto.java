package by.itechart.consignmentnote.dto;

import by.itechart.common.dto.GoodsDto;
import lombok.Data;

@Data
public class ConsignmentNoteGoodsDto {
    private Long id;
    private GoodsDto goods;
    private Integer amount;
}
