package com.dam.commune.property.flat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlatDTO {
    private Long id;
    private String cadastralReference;
    private Double squareMeters;
    private Double coefficient;
    private Integer floorNumber;
    private String letter;
    private int roomCount;
    private int bathroomCount;
    private String communityName;
    private String ownerName;
    private String ownerDni;
}
