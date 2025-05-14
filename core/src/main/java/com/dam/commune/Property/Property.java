package com.dam.commune.property;

import com.dam.commune.community.Community;
import com.dam.commune.owner.Owner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  
    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

     @ManyToOne
     @JoinColumn(name = "owner_id")
     private Owner owner;
}
