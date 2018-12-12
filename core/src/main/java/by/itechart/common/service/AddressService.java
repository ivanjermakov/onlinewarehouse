package by.itechart.common.service;

import by.itechart.common.dto.AddressDto;
import by.itechart.common.entity.Country;
import by.itechart.common.entity.Region;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface AddressService {

    Long saveAddress(@Valid AddressDto addressDto);

    List<Country> getCountriesDirectory();

    List<Region> getRegionsDirectoryByCountryId(Integer countryId);

}
