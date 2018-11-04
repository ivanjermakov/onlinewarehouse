package by.itechart.writeoffact.dto;

import by.itechart.common.entity.User;
import by.itechart.company.entity.Company;
import by.itechart.writeoffact.enums.WriteOffActType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

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
}
