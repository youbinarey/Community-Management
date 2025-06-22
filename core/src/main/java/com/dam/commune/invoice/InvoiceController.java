package com.dam.commune.invoice;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * REST controller for managing invoices.
 * <p>
 * Provides endpoints to create, retrieve, and delete invoices associated with
 * communities.
 * </p>
 * 
 * <ul>
 * <li>{@code POST /invoices/create-community-invoice} - Create a new invoice
 * for a community.</li>
 * <li>{@code GET /invoices/dto/community/{communityId}} - Retrieve all invoices
 * for a specific community.</li>
 * <li>{@code GET /invoices/dto/invoice/{id}} - Retrieve a specific invoice by
 * its ID.</li>
 * <li>{@code DELETE /invoices/dto/community/{id}} - Delete an invoice by its
 * ID.</li>
 * <li>{@code GET /invoices/invoices} - Retrieve all invoices.</li>
 * </ul>
 * 
 * <p>
 * Cross-origin requests are allowed from {@code http://localhost:4200}.
 * </p>
 */
@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/create-community-invoice")
    public ResponseEntity<InvoiceDTO> createInvoice(@RequestBody InvoiceDTO dto) {
        InvoiceDTO savedInvoice = invoiceService.saveInvoice(dto);
        return ResponseEntity.ok(savedInvoice);
    }

    @GetMapping("/dto/community/{communityId}")
    public ResponseEntity<List<InvoiceDTO>> getInvoicesByCommunity(@PathVariable Long communityId) {
        return ResponseEntity.ok(invoiceService.getInvoicesByCommunity(communityId));
    }

    @GetMapping("/dto/invoice/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        InvoiceDTO dto = invoiceService.getInvoiceById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/dto/community/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("invoices")
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        if (invoices.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(invoices);
        }
    }
}
