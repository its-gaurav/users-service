package com.gaurav.users.services;

import com.gaurav.users.domains.Address;

import java.util.List;

public abstract class AddressesService {

    public abstract Long addAddress(Address address);

    public abstract Address getAddressById(Long addressId);

    public abstract List<Address> getAddressByUserId(Long userId);
}
