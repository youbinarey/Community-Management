package com.dam.commune.property.Garage;

import com.dam.commune.property.Property;

import jakarta.persistence.Entity;

@Entity
public class Garage extends Property{
    private int parkingSpots;
}
