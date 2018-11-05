package by.itechart.writeoffact.dto;

import by.itechart.writeoffact.enums.WriteOffActType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class WriteOffActDto {

    private Long id;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate creation;
    private Long creatorId;
    private String responsiblePerson;
    private Integer totalAmount;
    private WriteOffActType writeOffActType;
    private List<WriteOffActGoodsDto> writeOffActGoodsList;
}
