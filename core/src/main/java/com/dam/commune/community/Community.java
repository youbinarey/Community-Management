package com.dam.commune.community;

import java.util.ArrayList;
import java.util.List;

import com.dam.commune.bankAccount.BankAccount;
import com.dam.commune.invoice.Invoice;
import com.dam.commune.property.Property;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Community entity, mapped to the "communities" table in the
 * database.
 * Each community has a unique combination of postal code and address.
 * 
 * Fields:
 * <ul>
 * <li>id - Unique identifier for the community (primary key).</li>
 * <li>address - The address of the community.</li>
 * <li>postalCode - The postal code of the community (cannot be null).</li>
 * <li>elevator - Indicates if the community has an elevator.</li>
 * <li>numFlats - Number of flats in the community (cannot be null).</li>
 * <li>numparkings - Number of parking spaces in the community (cannot be
 * null).</li>
 * <li>numStorageRooms - Number of storage rooms in the community (cannot be
 * null).</li>
 * <li>reducedMobilityAccess - Indicates if the community has access for reduced
 * mobility.</li>
 * <li>bankAccount - The associated bank account for the community (one-to-one
 * relationship).</li>
 * <li>properties - List of properties belonging to the community (one-to-many
 * relationship).</li>
 * <li>invoices - List of invoices associated with the community (one-to-many
 * relationship).</li>
 * </ul>
 */

@Entity
@Table(name = "communities", uniqueConstraints = @UniqueConstraint(columnNames = { "postal_code", "address" }))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    private boolean elevator;

    @Column(name = "num_flats", nullable = false)
    private Integer numFlats;

    @Column(name = "num_parkings", nullable = false)
    private Integer numparkings;

    @Column(name = "num_storage_rooms", nullable = false)
    private Integer numStorageRooms;

    @Column(name = "reduced_mobility_access")
    private boolean reducedMobilityAccess;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_account_id", unique = true)
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Property> properties = new ArrayList<>();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Invoice> invoices = new ArrayList<>();

}
