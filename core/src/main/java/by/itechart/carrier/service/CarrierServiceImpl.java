package by.itechart.carrier.service;

import by.itechart.carrier.dto.*;
import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.entity.Driver;
import by.itechart.carrier.repository.CarrierElasticRepository;
import by.itechart.carrier.repository.CarrierRepository;
import by.itechart.carrier.repository.DriverElasticRepository;
import by.itechart.carrier.repository.DriverRepository;
import by.itechart.common.repository.AddressRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import by.itechart.exception.NotFoundEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class CarrierServiceImpl implements CarrierService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CarrierServiceImpl.class);

    private final CarrierRepository carrierRepository;
    private final AddressRepository addressRepository;
    private final DriverRepository driverRepository;
    private final CarrierElasticRepository carrierElasticRepository;
    private final DriverElasticRepository driverElasticRepository;


    @Autowired
    public CarrierServiceImpl(CarrierRepository carrierRepository, AddressRepository addressRepository, DriverRepository driverRepository, CarrierElasticRepository carrierElasticRepository, DriverElasticRepository driverElasticRepository) {
        this.carrierRepository = carrierRepository;
        this.addressRepository = addressRepository;
        this.driverRepository = driverRepository;
        this.carrierElasticRepository = carrierElasticRepository;
        this.driverElasticRepository = driverElasticRepository;
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
        carrierElasticRepository.save(carrier);
        Long id = carrierRepository.save(carrier).getId();

        LOGGER.info("Carrier was created with id: {}", id);
        return id;
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
        LOGGER.info("Edit carrier with id: {}", carrierId);

        deleteCarrier(companyId, carrierId);
        return saveCarrier(createCarrierDto, companyId);
    }

    @Transactional
    @Override
    public void deleteCarrier(Long companyId, Long carrierId) {
        LOGGER.info("Delete carrier with id: {}", carrierId);
        carrierRepository.setDeleted(carrierId, companyId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DriverDto> getDrivers(Long carrierId, Pageable pageable) {
        Page<Driver> drivers = driverRepository.findByCarrierId(carrierId, pageable);
        return drivers.map(driver -> ObjectMapperUtils.map(driver, DriverDto.class));
    }

    @Transactional
    @Override
    public Page<Carrier> findByName(String name, Pageable pageable) {
        LOGGER.info("Find carrier by name: {}", name);

        return carrierElasticRepository.findByName(name, pageable);
    }

    @Transactional
    @Override
    public Page<Driver> findDriversByInfo(String name, Pageable pageable) {
        LOGGER.info("Find drivers by info: {}", name);
        return driverElasticRepository.findDriversByInfoAndDeletedIsFalse(name, pageable);
    }
}
