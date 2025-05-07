package com.dam.commune.bankAccount;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
        //TODO Auto-generated method stub
    }

    


}
