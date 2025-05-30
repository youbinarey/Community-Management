package com.dam.commune.invoice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoicePdfController {

    private final InvoiceService invoiceService;
    private final PdfService pdfService;

    public InvoicePdfController(InvoiceService invoiceService, PdfService pdfService) {
        this.invoiceService = invoiceService;
        this.pdfService = pdfService;
    }

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
}
