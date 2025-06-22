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

/**
 * REST controller for handling email sending of owner invoices.
 * <p>
 * Exposes endpoints to generate and send invoice PDFs to specified email addresses.
 * </p>
 *
 * <p>
 * Endpoints:
 * <ul>
 *   <li><b>POST /invoices/emails/owner-invoice/{id}/send</b> - Generates a PDF for the specified owner invoice and sends it to the provided email address.</li>
 * </ul>
 * </p>
 * <p>
 * Dependencies:
 * <ul>
 *   <li>{@link OwnerInvoiceService} - Service for retrieving owner invoice data.</li>
 *   <li>{@link PdfService} - Service for generating PDF documents.</li>
 *   <li>{@link EmailService} - Service for sending emails with attachments.</li>
 * </ul>
 * </p>
 */
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

            // 2. Genera el PDF
            byte[] pdfBytes = pdfService.generateOwnerReceiptPdf(ownerInvoiceDTO);

            // 3. Envía por email
            emailService.sendOwnerInvoicePdf(
                    pdfBytes,
                    email,
                    "Factura Comunidad",
                    "Adjuntamos su factura de comunidad en PDF.");
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
