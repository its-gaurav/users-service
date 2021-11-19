package com.gaurav.users.services;

import com.gaurav.users.domains.User;
import com.gaurav.users.entities.UserEntity;
import com.gaurav.users.exceptions.UserNotFoundException;
import com.gaurav.users.mappers.CustomUserMapper;
import com.gaurav.users.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersServiceImpl extends UsersService{
    private static List<User> allUsers = new ArrayList<User>();

    @Autowired
    private UsersRepository usersRepository;

    static{
        allUsers = new ArrayList<User>(
                Arrays.asList(new User("john","12345678"),
                        new User("john","12345678"),
                        new User("john","12345678"),
                        new User("john","12345678")
                        ));
    }
    public List<User> getAllUsers(){
        return usersRepository.findAll().stream().map(CustomUserMapper::map).collect(Collectors.toList());
    }

    public Long saveUser(User user) {
        UserEntity userEntity = CustomUserMapper.map(user);
        usersRepository.save(userEntity);
        return userEntity.getId();
    }

    public User getUserById(Long userId) {
        return usersRepository.findById(userId).map(CustomUserMapper::map)
                .orElseThrow(()-> new UserNotFoundException("Invalid User Id"));
    }
}
