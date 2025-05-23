package com.dam.commune.community;

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
    private String address;
    private String postalCode;
    private int propertiesCount;

    private boolean elevator;
    private int numFloors;
    private int numparkings;
    private int numStorageRooms;
    private boolean reducedMobilityAccess;

    private String bankAccountNumber;
}
