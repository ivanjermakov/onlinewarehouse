package by.itechart.common.dto;

import by.itechart.common.entity.Authority;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String patronymic;
    private String email;
    private AddressDto address;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birth;
    private Boolean enabled;
    private List<Authority> authorities;
}
