package com.gaurav.users.domains;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Address {

    private Long id;

    private Long userId;
    @NotNull @NotBlank
    private String address;

}
