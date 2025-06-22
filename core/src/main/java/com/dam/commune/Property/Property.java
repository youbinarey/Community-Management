package com.dam.commune.property;

import com.dam.commune.community.Community;
import com.dam.commune.owner.Owner;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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

/**
 * Abstract base class representing a property within a community.
 * <p>
 * This class is mapped as an entity using JPA with joined inheritance strategy.
 * Each property has a unique cadastral reference, a size in square meters,
 * a coefficient, and is associated with a community and an owner.
 * </p>
 * Annotations:
 * <ul>
 * <li>{@code @Entity}: Marks this class as a JPA entity.</li>
 * <li>{@code @Inheritance(strategy = InheritanceType.JOINED)}: Uses joined
 * table inheritance.</li>
 * <li>{@code @DiscriminatorColumn}: Discriminator column for inheritance.</li>
 * <li>Lombok annotations for builder, getters, setters, and constructors.</li>
 * </ul>
 * </p>
 */
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

    @Column(name = "cadastral_reference", nullable = false, unique = true)
    private String cadastralReference;

    @Column(name = "square_meters", nullable = false)
    private Double squareMeters;

    private Double coefficient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "community_id", nullable = false, updatable = false)
    @JsonIgnore
    private Community community;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Owner owner;

}
