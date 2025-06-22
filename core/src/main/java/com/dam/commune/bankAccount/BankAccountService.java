package com.dam.commune.bankAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {
    List<BankAccount> findAll();

    BankAccount save(BankAccount bankAccount);

    boolean deleteIfExists(Long id);

    void deleteById(Long id);

    Optional<BankAccount> findById(Long id);

    void update(BankAccount bankAccount, Long id);

}
