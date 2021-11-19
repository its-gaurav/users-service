package com.gaurav.users.entities;

import com.gaurav.users.domains.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity(name = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email_id")
    private String emailId;

    @NotNull
    @Column(name = "contact_number")
    private String contactNumber;

    @OneToMany(targetEntity = AddressEntity.class, mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<AddressEntity> addresses;

    @Column(name = "is_prime")
    private boolean isPrime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id) && name.equals(that.name) && Objects.equals(addresses, that.addresses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, addresses);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
