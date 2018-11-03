package by.itechart.writeoffact.dto;

import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.writeoffact.entity.WriteOffAct;
import by.itechart.writeoffact.enums.WriteOffActType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class WriteOffActDto {

    private Long id;
    private Company company;
    private LocalDate creation;
    private User creator;
    private String responsiblePerson;
    private Integer totalAmount;
    private List<WriteOffActGoodsDto> writeOffActGoodsList;
    private WriteOffActType writeOffActType;

    public WriteOffActDto(WriteOffAct writeOffAct) {
        id = writeOffAct.getId();
        company = writeOffAct.getCompany();
        creation = writeOffAct.getCreation();
        creator = writeOffAct.getCreator();
        responsiblePerson = writeOffAct.getResponsiblePerson();
        totalAmount = writeOffAct.getTotalAmount();
        writeOffActGoodsList = writeOffAct.getWriteOffActGoodsList().stream()
                .map(WriteOffActGoodsDto::new)
                .collect(Collectors.toList());
        writeOffActType = writeOffAct.getWriteOffActType();
    }
}
