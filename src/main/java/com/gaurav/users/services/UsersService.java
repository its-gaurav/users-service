package com.gaurav.users.services;

import com.gaurav.users.domains.User;

import java.util.List;

public abstract class UsersService {

    public abstract List<User> getAllUsers();

    public abstract Long saveUser(User user);

    public abstract User getUserById(Long userId);


}
