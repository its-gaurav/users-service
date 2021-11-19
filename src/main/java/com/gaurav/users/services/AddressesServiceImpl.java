package com.gaurav.users.services;

import com.gaurav.users.ExceptionConstants;
import com.gaurav.users.domains.Address;
import com.gaurav.users.entities.AddressEntity;
import com.gaurav.users.entities.UserEntity;
import com.gaurav.users.exceptions.AddressNotFoundException;
import com.gaurav.users.exceptions.UserNotFoundException;
import com.gaurav.users.mappers.CustomUserMapper;
import com.gaurav.users.repositories.AddressesRepository;
import com.gaurav.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class AddressesServiceImpl extends AddressesService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AddressesRepository addressesRepository;

    @Override
    public Long addAddress(Address address) {
//        Consumer<String> consumer = c -> c = c.toLowerCase();
//        Consumer<String> consumer2 = c -> c = c.toUpperCase();
//        consumer.andThen(consumer2).accept("Test");
        Supplier<UserNotFoundException> supplier = () -> new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND);
        Predicate<String> predicate = c -> c.startsWith("S");
        UserEntity userEntity = usersRepository.findById(address.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ExceptionConstants.USER_NOT_FOUND));
        AddressEntity addressEntity = CustomUserMapper.map(address, userEntity);
        addressesRepository.save(addressEntity);

        return addressEntity.getId();
    }

    @Override
    public Address getAddressById(Long addressId) {
        return addressesRepository.findById(addressId).map(CustomUserMapper::map)
                .orElseThrow(()-> new AddressNotFoundException(ExceptionConstants.ADDRESS_NOT_FOUND));
    }

    @Override
    public List<Address> getAddressByUserId(Long userId) {
        return addressesRepository.findByUserEntityId(userId).stream().map(CustomUserMapper::map).collect(Collectors.toList());
    }
}
