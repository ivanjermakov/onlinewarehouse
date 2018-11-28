package by.itechart.carrier.service;

import by.itechart.carrier.dto.*;
import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.entity.Driver;
import by.itechart.carrier.repository.CarrierRepository;
import by.itechart.carrier.repository.DriverRepository;
import by.itechart.common.repository.AddressRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.exception.NotFoundEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrierServiceImpl implements CarrierService {

    private final CarrierRepository carrierRepository;
    private final AddressRepository addressRepository;
    private final DriverRepository driverRepository;

    public CarrierServiceImpl(CarrierRepository carrierRepository, AddressRepository addressRepository, DriverRepository driverRepository) {
        this.carrierRepository = carrierRepository;
        this.addressRepository = addressRepository;
        this.driverRepository = driverRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CarrierListDto> getCarriers(CarrierFilter filter, Long companyId, Pageable pageable) {
        return carrierRepository.findAll(CarrierPredicates.findFilter(filter, companyId), pageable)
                .map(carrier -> ObjectMapperUtils.map(carrier, CarrierListDto.class));
    }

    @Transactional
    @Override
    public Long saveCarrier(CreateCarrierDto createCarrierDto, Long companyId) {
        Carrier carrier = ObjectMapperUtils.map(createCarrierDto, Carrier.class);

        Long addressId = addressRepository.save(carrier.getAddress()).getId();
        carrier.getAddress().setId(addressId);
        carrier.setCompany(new Company(companyId));

        return carrierRepository.save(carrier).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public CarrierDto getCarrier(Long companyId, Long carrierId) {
        Carrier carrier = carrierRepository.findOne(CarrierPredicates.findByCompanyIdAndId(companyId, carrierId)).orElseThrow(() -> new NotFoundEntityException("Carrier"));
        CarrierDto carrierDto = ObjectMapperUtils.map(carrier, CarrierDto.class);
        ArrayList<String> driversInfo = new ArrayList<>();
        driverRepository.findAll(CarrierPredicates.findDriverByCarrierId(carrierId)).forEach(driver -> driversInfo.add(driver.getInfo()));
        carrierDto.setDriverInfo(driversInfo);

        return carrierDto;
    }

    @Transactional
    @Override
    public Long editCarrier(CreateCarrierDto createCarrierDto, Long companyId, Long carrierId) {
        deleteCarrier(companyId, carrierId);
        return saveCarrier(createCarrierDto, companyId);
    }

    @Transactional
    @Override
    public void deleteCarrier(Long companyId, Long carrierId) {
        carrierRepository.setDeleted(carrierId, companyId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DriverDto> getDrivers(Long carrierId, Pageable pageable) {
        Page<Driver> drivers = driverRepository.findByCarrierId(carrierId, pageable);
        return drivers.map(driver -> ObjectMapperUtils.map(driver, DriverDto.class));
    }
}
