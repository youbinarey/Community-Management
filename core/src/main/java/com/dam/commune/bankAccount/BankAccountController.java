
package com.dam.commune.bankAccount;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for managing {@link BankAccount} entities.
 * <p>
 * Provides endpoints to perform CRUD operations on bank accounts.
 * </p>
 *
 * <ul>
 * <li>{@code GET /bank-accounts} - Retrieve all bank accounts.</li>
 * <li>{@code GET /bank-accounts/{id}} - Retrieve a bank account by its ID.</li>
 * <li>{@code POST /bank-accounts} - Create a new bank account.</li>
 * <li>{@code DELETE /bank-accounts/{id}} - Delete a bank account by its
 * ID.</li>
 * </ul>
 * 
 */
@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAll() {
        return ResponseEntity.ok(bankAccountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getById(@PathVariable Long id) {
        return bankAccountService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BankAccount> create(@RequestBody BankAccount bankAccount) {
        return ResponseEntity.ok(bankAccountService.save(bankAccount));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!bankAccountService.deleteIfExists(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
