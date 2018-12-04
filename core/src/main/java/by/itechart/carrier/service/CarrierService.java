package by.itechart.carrier.service;

import by.itechart.carrier.dto.*;
import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface CarrierService {

    Page<CarrierListDto> getCarriers(CarrierFilter filter, Long companyId, Pageable pageable);

    Page<DriverDto> getDrivers(Long carrierId, Pageable pageable);

    Long saveCarrier(@Valid CreateCarrierDto createCarrierDto, Long companyId);

    CarrierDto getCarrier(Long companyId, Long carrierId);

    Long editCarrier(@Valid CreateCarrierDto createCarrierDto, Long companyId, Long carrierId);

    void deleteCarrier(Long companyId, Long carrierId);

    Page<Carrier> findByName(String name, Pageable pageable);

    Page<Driver> findDriversByInfo(String name, Pageable pageable);

    Long saveDriver(long carrierId, DriverDto driverDto);

    Long changeCarrierTrustedValue(long carrierId);
}
