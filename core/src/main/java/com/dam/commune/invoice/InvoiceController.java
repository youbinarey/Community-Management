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


import com.dam.commune.invoice.owner.OwnerInvoiceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class InvoiceController {

    private final InvoiceService invoiceService;
    private final OwnerInvoiceService ownerInvoiceService;

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
}
