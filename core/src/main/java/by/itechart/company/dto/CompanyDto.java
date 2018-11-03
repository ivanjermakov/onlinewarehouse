package by.itechart.company.dto;

import by.itechart.company.entity.Company;
import by.itechart.company.enums.ActionType;
import by.itechart.company.enums.CompanySize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyDto {
    private Long id;
    private String name;
    private CompanySize sizeType;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime change;
    private ActionType actionType;

    public CompanyDto() {
    }

    public CompanyDto(Company company) {
        id = company.getId();
        name = company.getName();
        sizeType = company.getSizeType();
    }

}
