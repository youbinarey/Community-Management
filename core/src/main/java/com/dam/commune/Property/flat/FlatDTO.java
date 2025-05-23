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
    private String cadastastralReference;
    private Double squareMeters;
    private Integer floorNumber;
    private String letter;
    private int roomCount;
    private int bathroomCount;
    private String comunityName;
    private String ownerName;
}
