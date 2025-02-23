package by.itechart.web.controller;

import by.itechart.carrier.dto.*;
import by.itechart.carrier.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies/{companyId}/carriers")
public class CarrierController {

    final private CarrierService carrierService;

    @Autowired
    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @GetMapping
    public Page<CarrierListDto> getCarriersList(@PathVariable long companyId,
                                                Pageable pageable,
                                                CarrierFilter carrierFilter) {
        return carrierService.getCarriers(carrierFilter, companyId, pageable);
    }

    @PostMapping
    public Long saveCarrier(@PathVariable long companyId, @RequestBody CreateCarrierDto createCarrierDto) {
        return carrierService.saveCarrier(createCarrierDto, companyId);
    }

    @GetMapping("/{carrierId}")
    public CarrierDto getCarrier(@PathVariable long companyId, @PathVariable long carrierId) {
        return carrierService.getCarrier(companyId, carrierId);
    }

    @PutMapping("/{carrierId}")
    public Long editCarrier(@PathVariable long companyId, @PathVariable long carrierId, @RequestBody CreateCarrierDto createCarrierDto) {
        return carrierService.editCarrier(createCarrierDto, companyId, carrierId);
    }

    @DeleteMapping("/{carrierId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCarrier(@PathVariable long companyId, @PathVariable long carrierId) {
        carrierService.deleteCarrier(companyId, carrierId);
    }

    @GetMapping("/{carrierId}/drivers")
    public Page<DriverDto> getCarriersList(@PathVariable long carrierId,
                                           Pageable pageable) {
        return carrierService.getDrivers(carrierId, pageable);
    }

    @PostMapping("/{carrierId}/drivers")
    public Long getCarriersList(@PathVariable long carrierId,
                                @RequestBody DriverDto driverDto) {
        return carrierService.saveDriver(carrierId, driverDto);
    }

    @PostMapping("/{carrierId}/change-trusted")
    public Long changeCarrierTrustedValue(@PathVariable long carrierId) {
        return carrierService.changeCarrierTrustedValue(carrierId);
    }
}
