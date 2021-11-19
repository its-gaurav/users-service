package com.gaurav.users.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "addresses")
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

    @Column(name="address")
    private String address;

}
