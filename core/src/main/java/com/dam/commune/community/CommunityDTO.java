package com.dam.commune.community;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDTO {
    private Long id;
    
    @NotEmpty(message = "Address cannot be empty")
    private String address;
    @NotEmpty(message = "Address cannot be empty")
    private String postalCode;
    
    private int propertiesCount;

    private boolean elevator;
    private int numFloors;
    private int numparkings;
    private int numStorageRooms;
    private boolean reducedMobilityAccess;

    private String bankAccountNumber;
}
