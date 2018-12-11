package by.itechart.common.service;

import by.itechart.common.dto.AddressDto;
import by.itechart.common.entity.Address;
import by.itechart.common.entity.Country;
import by.itechart.common.entity.Region;
import by.itechart.common.repository.AddressRepository;
import by.itechart.common.repository.CountryRepository;
import by.itechart.common.repository.RegionRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;

    public AddressServiceImpl(AddressRepository addressRepository,
                              CountryRepository countryRepository,
                              RegionRepository regionRepository) {
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public Long saveAddress(AddressDto addressDto) {
        Long id = addressRepository.save(ObjectMapperUtils.map(addressDto, Address.class)).getId();
        LOGGER.info("Address was created with id: {}", id);

        return id;
    }

    @Override
    public List<Country> getCountriesDirectory() {
        List<Country> countries = countryRepository.findAll();
        return countries;
    }

    @Override
    public List<Region> getRegionsDirectoryByCountryId(Integer countryId) {
        List<Region> countryRegions = regionRepository.findAllByCountry_Id(countryId);
        return countryRegions;
    }
}
