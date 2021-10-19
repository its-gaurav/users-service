package com.gaurav.users.controllers;

import com.gaurav.users.ExceptionConstants;
import com.gaurav.users.domains.Address;
import com.gaurav.users.domains.User;
import com.gaurav.users.exceptions.AddressNotFoundException;
import com.gaurav.users.services.AddressesService;
import com.gaurav.users.services.UsersServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class UsersRestController {

    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private AddressesService addressesService;

    @GetMapping(path = "/users/{userId}")
    public User getUser(@PathVariable("userId") Long userId){
        return usersService.getUserById(userId);
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
        return usersService.getAllUsers();
    }

    @PostMapping(path = "/users")
    public ResponseEntity saveUser(@RequestBody @Valid User user){
        log.info("-------- Saving User {} ----------",user);
         Long savedUserId = usersService.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUserId).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(path = "/users/{userId}/address")
    public ResponseEntity addAddress(@PathVariable("userId") Long userId ,@RequestBody @Valid Address address){
        log.info("-------- Saving Address {} for userId {}----------",address, userId);
        address.setUserId(userId);
        Long savedAddressId = addressesService.addAddress(address);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAddressId).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/users/{userId}/address/{addressId}")
    public Address getAddressByAddressId(@PathVariable("userId") Long userId ,@PathVariable("addressId") Long addressId){
        Address address = addressesService.getAddressById(addressId);
        if(!address.getUserId().equals(userId)){
            throw new AddressNotFoundException(ExceptionConstants.ADDRESS_NOT_FOUND);
        }
        return address;
    }

    @GetMapping(path = "/users/{userId}/address")
    public List<Address> getAddressesByUser(@PathVariable("userId") Long userId){
        List<Address> addresses = addressesService.getAddressByUserId(userId);

        return addresses;
    }
}
