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

/**
 * Represents an invoice associated with a specific owner and property.
 * <p>
 * This entity links an {@link Invoice} to an {@link Owner} and a
 * {@link Property},
 * storing the amount and date for the invoice. Each combination of invoice,
 * owner,
 * and property is unique.
 * </p>
 *
 * <p>
 * Fields:
 * <ul>
 * <li><b>id</b>: Unique identifier for the OwnerInvoice.</li>
 * <li><b>date</b>: The date of the invoice (format as string).</li>
 * <li><b>amount</b>: The amount billed in this invoice.</li>
 * <li><b>invoice</b>: The related {@link Invoice} entity.</li>
 * <li><b>owner</b>: The related {@link Owner} entity.</li>
 * <li><b>property</b>: The related {@link Property} entity.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Database Constraints:
 * <ul>
 * <li>Unique constraint on the combination of invoice, owner, and
 * property.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Annotations:
 * <ul>
 * <li>{@code @Entity}: Marks this class as a JPA entity.</li>
 * <li>{@code @Table}: Specifies the table and unique constraints.</li>
 * <li>Lombok annotations for boilerplate code generation.</li>
 * </ul>
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "owner_invoice", uniqueConstraints = @UniqueConstraint(columnNames = { "invoice_id", "owner_id",
        "property_id" }))
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