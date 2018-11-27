package by.itechart.writeoffact.dto;

import by.itechart.writeoffact.enums.WriteOffActType;
import lombok.Data;

import java.util.List;

@Data
public class CreateWriteOffActDto {
    private Long creatorId;
    private String responsiblePerson;
    private WriteOffActType writeOffActType;
    private List<CreateWriteOffActGoodsDto> writeOffActGoodsDtoList;
}
