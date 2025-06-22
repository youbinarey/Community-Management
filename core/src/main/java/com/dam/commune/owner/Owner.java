package com.dam.commune.owner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dam.commune.bankAccount.BankAccount;
import com.dam.commune.property.Property;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an Owner entity in the system.
 * <p>
 * An Owner has personal information such as DNI, name, surname, email, phone,
 * and birth date.
 * Each Owner is associated with a unique {@link BankAccount} and can own
 * multiple {@link Property} entities.
 * </p>
 *
 * <p>
 * JPA annotations are used for ORM mapping:
 * <ul>
 * <li>{@code @Entity} marks this class as a JPA entity.</li>
 * <li>{@code @Id} and {@code @GeneratedValue} define the primary key and its
 * generation strategy.</li>
 * <li>{@code @Column} specifies column constraints such as uniqueness and
 * nullability.</li>
 * <li>{@code @OneToOne} and {@code @OneToMany} define relationships with other
 * entities.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Lombok annotations are used to reduce boilerplate:
 * <ul>
 * <li>{@code @Getter}, {@code @Setter} for automatic getter/setter
 * generation.</li>
 * <li>{@code @AllArgsConstructor}, {@code @NoArgsConstructor} for
 * constructors.</li>
 * <li>{@code @Builder} for builder pattern support.</li>
 * </ul>
 * </p>
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dni;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    LocalDate birthDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bank_account_id", unique = true)
    private BankAccount bankAccount;

    @Builder.Default
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Property> properties = new ArrayList<>();

}
