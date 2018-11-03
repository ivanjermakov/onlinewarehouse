package by.itechart.writeoffact.dto;

import by.itechart.common.entity.User;
import by.itechart.company.dto.CompanyDto;
import by.itechart.writeoffact.enums.WriteOffActType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateWriteOffActDto {

    private CompanyDto company;
    private LocalDate creation;
    private User creator;
    private String responsiblePerson;
    private Integer totalAmount;
    private WriteOffActType writeOffActType;
}
