package by.itechart.company.dto;

import by.itechart.company.enums.CompanySize;
import lombok.Data;

@Data
public class CreateCompanyDto {
    private String name;
    private CompanySize sizeType;
    private String username;
    private String password;
}
