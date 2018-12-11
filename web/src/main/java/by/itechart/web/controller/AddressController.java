package by.itechart.web.controller;

import by.itechart.common.entity.Country;
import by.itechart.common.entity.Region;
import by.itechart.common.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/address")
public class AddressController {

    final private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/countries")
    public List<Country> getCountriesList() {
        return addressService.getCountriesDirectory();
    }

    @GetMapping("/countries/{countryId}/regions")
    public List<Region> getCountryRegionsList(@PathVariable Integer countryId) {
        return addressService.getRegionsDirectoryByCountryId(countryId);
    }

}
