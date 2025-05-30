package com.dam.commune.property.flat;

import com.dam.commune.property.Property;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"letter","floor_number"})
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("FLAT")
public class Flat extends Property {
    private Integer floorNumber;
    private String letter;
    private int roomCount;
    private int bathroomCount;
}
