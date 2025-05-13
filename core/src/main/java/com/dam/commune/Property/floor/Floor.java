package com.dam.commune.property.floor;

import com.dam.commune.property.Property;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Floor extends Property {
    private Integer floorNumber;
    private String letter;
    private int roomCount;
    private int bathroomCount;
}
