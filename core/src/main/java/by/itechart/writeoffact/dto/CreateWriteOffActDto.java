package by.itechart.writeoffact.dto;

import by.itechart.writeoffact.enums.WriteOffActType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateWriteOffActDto {
    @NotNull
    private Long creatorId;
    @NotEmpty
    private String responsiblePerson;
    @NotNull
    private WriteOffActType writeOffActType;
    @NotEmpty
    private List<CreateWriteOffActGoodsDto> writeOffActGoodsDtoList;
}
