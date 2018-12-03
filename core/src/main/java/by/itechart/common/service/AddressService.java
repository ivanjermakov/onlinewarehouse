package by.itechart.common.service;

import by.itechart.common.dto.AddressDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface AddressService {

    Long saveAddress(@Valid AddressDto addressDto);

}
