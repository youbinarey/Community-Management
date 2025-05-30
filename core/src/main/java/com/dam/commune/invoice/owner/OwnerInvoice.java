package com.dam.commune.invoice.owner;

import com.dam.commune.invoice.Invoice;
import com.dam.commune.owner.Owner;
import com.dam.commune.property.Property;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "owner_invoice", uniqueConstraints = @UniqueConstraint(columnNames = { "invoice_id", "owner_id", "property_id" }))
public class OwnerInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private Double amount; 

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice; 
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner; 

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    
}