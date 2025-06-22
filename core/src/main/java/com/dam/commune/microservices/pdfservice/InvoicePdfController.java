package com.dam.commune.microservices.pdfservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dam.commune.invoice.InvoiceDTO;
import com.dam.commune.invoice.InvoiceService;
import com.dam.commune.invoice.owner.OwnerInvoiceDTO;
import com.dam.commune.invoice.owner.OwnerInvoiceService;

import lombok.RequiredArgsConstructor;

/**
 * Controller for handling PDF generation and download for invoices and owner
 * receipts.
 * <p>
 * Exposes REST endpoints to:
 * <ul>
 * <li>Download a PDF representation of an invoice by its ID.</li>
 * <li>Download a PDF receipt for an owner invoice by its ID.</li>
 * </ul>
 * <p>
 * This controller delegates invoice retrieval and PDF generation to the
 * corresponding services.
 * The generated PDFs are returned as downloadable attachments.
 * <p>
 * Cross-origin requests are allowed from http://localhost:4200.
 *
 * Dependencies:
 * <ul>
 * <li>{@link InvoiceService} for retrieving invoice data.</li>
 * <li>{@link PdfService} for generating PDF files via a Python
 * microservice.</li>
 * <li>{@link OwnerInvoiceService} for retrieving owner invoice data.</li>
 * </ul>
 *
 * Endpoints:
 * <ul>
 * <li><b>GET /invoices/{id}/pdf</b>: Download invoice PDF.</li>
 * <li><b>GET /invoices/owner-invoice/{id}/pdf</b>: Download owner receipt
 * PDF.</li>
 * </ul>
 */
@RestController
@RequestMapping("/invoices")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor

public class InvoicePdfController {

    private final InvoiceService invoiceService;
    private final PdfService pdfService;
    private final OwnerInvoiceService ownerInvoiceService;

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> descargarPdf(@PathVariable Long id) {
        // 1. Obtén la factura de la base de datos
        InvoiceDTO invoiceDTO = invoiceService.getInvoiceById(id);
        // 2. Mapea al DTO para PDF
        InvoicePdfDTO pdfDto = InvoicePdfMapper.from(invoiceDTO);
        // 3. Genera el PDF llamando al microservicio Python
        byte[] pdfBytes = pdfService.generatePdf(pdfDto);

        // 4. Devuelve el PDF
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=recibo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @GetMapping("/owner-invoice/{id}/pdf")
    public ResponseEntity<byte[]> descargarOwnerReceiptPdf(@PathVariable Long id) {
        // 1. Obtener el OwnerInvoiceDTO a partir del id
        OwnerInvoiceDTO ownerInvoiceDTO = ownerInvoiceService.getById(id);
        if (ownerInvoiceDTO == null) {
            return ResponseEntity.notFound().build();
        }

        // 2. (Opcional) Mapea o ajusta los datos al formato que necesite el
        // microservicio
        // (Si necesitas mapearlo, usa un mapper; si ya está listo, omite este paso)

        // 3. Genera el PDF llamando al microservicio Python
        byte[] pdfBytes = pdfService.generateOwnerReceiptPdf(ownerInvoiceDTO);

        // 4. Devuelve el PDF como archivo adjunto
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=owner-receipt.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}
