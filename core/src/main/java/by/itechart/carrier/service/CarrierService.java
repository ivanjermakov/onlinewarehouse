package by.itechart.carrier.service;

import by.itechart.carrier.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarrierService {

    Page<CarrierListDto> getCarriers(CarrierFilter filter, Long companyId, Pageable pageable);

    Page<DriverDto> getDrivers(Long carrierId, Pageable pageable);

    Long saveCarrier(CreateCarrierDto createCarrierDto, Long companyId);

    CarrierDto getCarrier(Long companyId, Long carrierId);

    Long editCarrier(CreateCarrierDto createCarrierDto, Long companyId, Long carrierId);

    void deleteCarrier(Long companyId, Long carrierId);

}
