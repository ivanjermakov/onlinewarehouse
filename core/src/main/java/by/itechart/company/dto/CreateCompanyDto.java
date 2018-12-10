package by.itechart.company.dto;

import by.itechart.common.dto.CreateUserDto;
import by.itechart.company.enums.CompanySize;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateCompanyDto {
    @NotEmpty
    private String name;
    @NotNull
    private CompanySize sizeType;
    @NotNull
    private CreateUserDto createUserDto;
    private String logo;
}
