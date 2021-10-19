package com.gaurav.users.mappers;

import com.gaurav.users.domains.Address;
import com.gaurav.users.domains.User;
import com.gaurav.users.entities.AddressEntity;
import com.gaurav.users.entities.UserEntity;

public class CustomMapper {


    public static UserEntity map(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(user.getName());
        userEntity.setContactNumber(user.getContactNumber());
        userEntity.setEmailId(user.getEmailId());

        return userEntity;
    }

    public static User map(UserEntity userEntity) {
        User user = new User(userEntity.getId(), userEntity.getName(),userEntity.getEmailId(), userEntity.getContactNumber());

        return user;
    }

    public static Address map(AddressEntity addressEntity) {
        Address address = new Address();
        address.setId(addressEntity.getId());
        address.setUserId(addressEntity.getUserEntity().getId());
        address.setAddress(addressEntity.getAddress());

        return address;
    }

    public static AddressEntity map(Address address, UserEntity userEntity) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setUserEntity(userEntity);
        addressEntity.setAddress(address.getAddress());

        return addressEntity;
    }
}
