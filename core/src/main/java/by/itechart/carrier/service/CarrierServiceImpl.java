package by.itechart.carrier.service;

import by.itechart.carrier.dto.CarrierDto;
import by.itechart.carrier.dto.CarrierFilter;
import by.itechart.carrier.dto.CarrierListDto;
import by.itechart.carrier.dto.CreateCarrierDto;
import by.itechart.carrier.entity.Carrier;
import by.itechart.carrier.repository.CarrierRepository;
import by.itechart.common.repository.AddressRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import by.itechart.company.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarrierServiceImpl implements CarrierService {

    private final CarrierRepository carrierRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public CarrierServiceImpl(CarrierRepository carrierRepository, AddressRepository addressRepository) {
        this.carrierRepository = carrierRepository;
        this.addressRepository = addressRepository;

    }

    @Transactional(readOnly = true)
    @Override
    public Page<CarrierListDto> getCarriers(CarrierFilter filter, Long companyId, Pageable pageable) {
        Page<Carrier> result = carrierRepository.findAll(CarrierPredicates.findFilter(filter, companyId), pageable);
        List<CarrierListDto> carrierListDto = ObjectMapperUtils.mapAll(result.getContent(), CarrierListDto.class);
        return new PageImpl<CarrierListDto>(carrierListDto, pageable, result.getTotalElements());
    }

    @Transactional
    @Override
    public Long saveCarrier(CreateCarrierDto createCarrierDto, Long companyId) {
        Carrier carrier = new Carrier();
        ObjectMapperUtils.map(createCarrierDto, carrier);
        Long addressId = addressRepository.save(carrier.getAddress()).getId();
        carrier.getAddress().setId(addressId);
        carrier.setCompany(new Company(companyId));
        return carrierRepository.save(carrier).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public CarrierDto getCarrier(Long companyId, Long carrierId) {
        Carrier carrier = carrierRepository.findOne(CarrierPredicates.findCompanyIdAndId(companyId, carrierId)).orElse(null);
        return ObjectMapperUtils.map(carrier, CarrierDto.class);
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
}
