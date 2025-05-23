package com.dam.commune.community;

import java.util.ArrayList;
import java.util.List;

import com.dam.commune.bankAccount.BankAccount;
import com.dam.commune.property.Property;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "communities")
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
    
    @Column(name = "postal_code")
    private String postalCode;

    private boolean elevator;

    @Column(name = "num_flats", nullable = false)
    private int numFlats;

    @Column(name = "num_parkings", nullable = false)
    private int numparkings;

    @Column(name = "num_storage_rooms", nullable = false)
    private int numStorageRooms;
   

    @Column(name = "reduced_mobility_access")
    private boolean reducedMobilityAccess;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_account_id", unique = true)
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Property> properties = new ArrayList<>();

 
}
    