package com.dam.commune.property;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "cadastral_reference", nullable = false, unique = true)
    private String cadastralReference;

    @Column(name= "square_meters", nullable = false)
    private Double squareMeters;

    // La relación con Community 
    // @ManyToOne
    // private Community community;

    // @ManyToOne
    // private Owner owner;
}
