package com.gaurav.users.services;

import com.gaurav.users.ExceptionConstants;
import com.gaurav.users.domains.Address;
import com.gaurav.users.entities.AddressEntity;
import com.gaurav.users.entities.UserEntity;
import com.gaurav.users.exceptions.AddressNotFoundException;
import com.gaurav.users.exceptions.UserNotFoundException;
import com.gaurav.users.mappers.CustomMapper;
import com.gaurav.users.repositories.AddressesRepository;
import com.gaurav.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressesServiceImpl extends AddressesService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AddressesRepository addressesRepository;

    @Override
    public Long addAddress(Address address) {
        UserEntity userEntity = usersRepository.findById(address.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND));
        AddressEntity addressEntity = CustomMapper.map(address, userEntity);
        addressesRepository.save(addressEntity);

        return addressEntity.getId();
    }

    @Override
    public Address getAddressById(Long addressId) {
        return addressesRepository.findById(addressId).map(CustomMapper::map)
                .orElseThrow(()-> new AddressNotFoundException(ExceptionConstants.ADDRESS_NOT_FOUND));
    }

    @Override
    public List<Address> getAddressByUserId(Long userId) {
        return addressesRepository.findByUserEntityId(userId).stream().map(CustomMapper::map).collect(Collectors.toList());
    }
}
