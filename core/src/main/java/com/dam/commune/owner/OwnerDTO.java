package com.dam.commune.owner;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {
    private String dni;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String bankAccountNumber;
    private int propertiesCount;
}
