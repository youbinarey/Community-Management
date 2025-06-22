package com.dam.commune.community;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a community entity.
 * <p>
 * This class is used to transfer community-related data between different
 * layers of the application.
 * It includes information such as address, postal code, property counts, and
 * accessibility features.
 * </p>
 *
 * <p>
 * Validation annotations are used to ensure required fields are provided.
 * </p>
 * Annotations:
 * <ul>
 * <li>{@code @Data} - Generates getters, setters, and other utility
 * methods.</li>
 * <li>{@code @Builder} - Enables the builder pattern for object creation.</li>
 * <li>{@code @NoArgsConstructor} - Generates a no-argument constructor.</li>
 * <li>{@code @AllArgsConstructor} - Generates a constructor with all fields as
 * parameters.</li>
 * </ul>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDTO {
    private Long id;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotNull(message = "Postal code cannot be null")
    private String postalCode;

    private Integer propertiesCount;

    private boolean elevator;
    private Integer numFlats;
    private Integer numparkings;
    private Integer numStorageRooms;
    private boolean reducedMobilityAccess;

    private String bankAccountNumber;
}
