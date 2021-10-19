package com.gaurav.users.repositories;

import com.gaurav.users.entities.AddressEntity;
import com.gaurav.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressesRepository extends JpaRepository<AddressEntity,Long> {

    Optional<AddressEntity> findById(Long id);

    List<AddressEntity> findByUserEntityId(Long userId);
}
