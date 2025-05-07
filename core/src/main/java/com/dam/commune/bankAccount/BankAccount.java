package com.dam.commune.bankAccount;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = false, name = "account_number")
    private String accountNumber;

    @Column(nullable = false, name = "bank_name")
    private String bankName;

    @Column(nullable = false)
    private BigDecimal balance;

}
