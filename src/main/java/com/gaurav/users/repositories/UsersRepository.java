package com.gaurav.users.repositories;

import com.gaurav.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findById(Long id);
}
