package by.itechart.common.service;

import by.itechart.common.dto.AddressDto;
import by.itechart.common.entity.Address;
import by.itechart.common.repository.AddressRepository;
import by.itechart.common.utils.ObjectMapperUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Long saveAddress(AddressDto addressDto) {
        return addressRepository.save(ObjectMapperUtils.map(addressDto, Address.class)).getId();
    }
}
