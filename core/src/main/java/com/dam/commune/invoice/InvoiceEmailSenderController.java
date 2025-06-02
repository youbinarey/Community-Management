package com.dam.commune.invoice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dam.commune.invoice.owner.OwnerInvoiceDTO;
import com.dam.commune.invoice.owner.OwnerInvoiceService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/invoices/emails")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class InvoiceEmailSenderController {

    private final OwnerInvoiceService ownerInvoiceService;
    private final PdfService pdfService;
    private final EmailService emailService;

    @PostMapping("/owner-invoice/{id}/send")
public ResponseEntity<?> sendOwnerInvoiceByEmail(
        @PathVariable Long id,
        @RequestParam String email) {
    try {
        // 1. Recupera el OwnerInvoiceDTO
        OwnerInvoiceDTO ownerInvoiceDTO = ownerInvoiceService.getById(id);

        // 2. Genera el PDF (lo mismo que tu endpoint de descarga, pero sin devolverlo, solo lo usas)
        byte[] pdfBytes = pdfService.generateOwnerReceiptPdf(ownerInvoiceDTO);

        // 3. Envía por email
        emailService.sendOwnerInvoicePdf(
            pdfBytes,
            email,
            "Factura de propietario",
            "Adjuntamos su factura de propietario/a en PDF."
        );

        return ResponseEntity.ok("Email enviado correctamente");
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
    }
}


}
