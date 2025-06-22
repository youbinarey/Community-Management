package com.dam.commune.bankAccount;

import java.math.BigDecimal;

import com.dam.commune.community.Community;
import com.dam.commune.owner.Owner;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a bank account entity associated with either a Community or an
 * Owner.
 * Stores essential bank account information such as account number, bank name,
 * and balance.
 * 
 * <p>
 * Each BankAccount is uniquely identified by its {@code id} and
 * {@code accountNumber}.
 * It maintains a one-to-one relationship with either a {@link Community} or an
 * {@link Owner}.
 * </p>
 * 
 * <ul>
 * <li>{@code id} - Primary key, auto-generated.</li>
 * <li>{@code accountNumber} - Unique identifier for the bank account.</li>
 * <li>{@code bankName} - Name of the bank where the account is held.</li>
 * <li>{@code balance} - Current balance of the bank account.</li>
 * <li>{@code community} - The community associated with this bank account (if
 * any).</li>
 * <li>{@code owner} - The owner associated with this bank account (if
 * any).</li>
 * </ul>
 * 
 * <p>
 * This entity uses Lombok annotations for boilerplate code generation and JPA
 * annotations for ORM mapping.
 * </p>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "account_number", unique = true)
    private String accountNumber;

    @Column(nullable = false, name = "bank_name")
    private String bankName;

    @Column(nullable = false)
    private BigDecimal balance;

    @OneToOne(mappedBy = "bankAccount")
    @JsonIgnore
    private Community community;

    @OneToOne(mappedBy = "bankAccount")
    @JsonIgnore
    private Owner owner;

}
