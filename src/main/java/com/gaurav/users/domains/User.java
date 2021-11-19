package com.gaurav.users.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    @NotNull
    private String name;

    private String emailId;
    @NotNull
    private String contactNumber;

    private List<Address> addresses;

    private boolean isPrime = false;

    public User(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public User(Long id, String name, String emailId, String contactNumber) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.contactNumber = contactNumber;
    }
}
