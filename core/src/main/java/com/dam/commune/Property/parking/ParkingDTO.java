package com.dam.commune.property.parking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDTO {
    private int num;
    private String communityName;
    private String ownerName;
}
