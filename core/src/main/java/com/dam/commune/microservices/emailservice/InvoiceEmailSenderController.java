package com.dam.commune.microservices.emailservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dam.commune.invoice.owner.OwnerInvoiceDTO;
import com.dam.commune.invoice.owner.OwnerInvoiceService;
import com.dam.commune.microservices.pdfservice.PdfService;


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
            Map<String, String> response = new HashMap<>();
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
            "Adjuntamos su factura de propietario en PDF."
        );
        response.put("status", "ok");
        response.put("message", "Email enviado correctamente");
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        response.put("status", "error");
        response.put("message", "Error: " + e.getMessage());
        return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
    }
}


}
