package com.dam.commune.community;

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
    

    @Column(name = "reduced_mobility_access")
    private boolean reducedMobilityAccess;

    @OneToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<Property> properties;

 
}
    