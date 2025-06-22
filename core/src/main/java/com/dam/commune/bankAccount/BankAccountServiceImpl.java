package com.dam.commune.bankAccount;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing {@link BankAccount} entities.
 * <p>
 * This class provides methods to perform CRUD operations on bank accounts,
 * delegating persistence logic to the {@link BankAccountRepository}.
 * </p>
 *
 * <ul>
 * <li>{@code findAll()} - Retrieves all bank accounts.</li>
 * <li>{@code save(BankAccount)} - Saves or updates a bank account.</li>
 * <li>{@code deleteIfExists(Long)} - Deletes a bank account by ID if it
 * exists.</li>
 * <li>{@code deleteById(Long)} - Deletes a bank account by ID.</li>
 * <li>{@code findById(Long)} - Finds a bank account by ID.</li>
 * <li>{@code update(BankAccount, Long)} - Updates a bank account (method
 * stub).</li>
 * </ul>
 *
 * @see BankAccountService
 * @see BankAccountRepository
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public boolean deleteIfExists(Long id) {
        if (bankAccountRepository.existsById(id)) {
            bankAccountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(Long id) {
        this.bankAccountRepository.deleteById(id);
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return this.bankAccountRepository.findById(id);
    }

    @Override
    public void update(BankAccount bankAccount, Long id) {
        // TODO Auto-generated method stub
    }

}
