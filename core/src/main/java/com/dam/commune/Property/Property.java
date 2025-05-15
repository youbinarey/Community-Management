package com.dam.commune.property;

import com.dam.commune.community.Community;
import com.dam.commune.owner.Owner;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;



@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "property_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "cadastral_reference", nullable = false, unique = true)
    private String cadastralReference;

    @Column(name= "square_meters", nullable = false)
    private Double squareMeters;

  
    @ManyToOne(optional = false)
    @JoinColumn(name = "community_id", nullable = false, updatable = false)
    private Community community;

     @ManyToOne
     @JoinColumn(name = "owner_id")
     private Owner owner;
}
