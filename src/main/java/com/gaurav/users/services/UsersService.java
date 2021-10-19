package com.gaurav.users.services;

import com.gaurav.users.domains.User;

import java.util.List;

public abstract class UsersService {

    abstract List<User> getAllUsers();

    abstract Long saveUser(User user);

    abstract User getUserById(Long userId);


}
