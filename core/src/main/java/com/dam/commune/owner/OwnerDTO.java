package com.dam.commune.owner;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Data Transfer Object (DTO) representing an Owner.
 * <p>
 * This class encapsulates the basic information about an owner,
 * including personal details, contact information, and property count.
 * </p>
 * Includes Lombok annotations for automatic generation of getters, setters,
 * constructors, and other boilerplate code.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {
    private Long id;
    private String dni;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String bankAccountNumber;
    private Integer propertiesCount;
}
