package com.dam.commune.property.Garage;

import com.dam.commune.property.Property;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Garage extends Property{
    private int parkingSpots;
}
