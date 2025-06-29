package com.dam.commune.microservices.pdfservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dam.commune.invoice.owner.OwnerInvoiceDTO;

@Service
public class PdfService {

    private final RestTemplate restTemplate = new RestTemplate();
    //private final String PDF_MICROSERVICE_URL = "http://localhost:5000";
    private final String PDF_MICROSERVICE_URL = "http://pdfgenerator:8000";

    public byte[] generatePdf(InvoicePdfDTO pdfDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<InvoicePdfDTO> requestEntity = new HttpEntity<>(pdfDto, headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                PDF_MICROSERVICE_URL + "/generate-community-receipt",
                HttpMethod.POST,
                requestEntity,
                byte[].class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new RuntimeException("No se pudo generar el PDF");
        }
    }

    public byte[] generateOwnerReceiptPdf(OwnerInvoiceDTO pdfDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OwnerInvoiceDTO> requestEntity = new HttpEntity<>(pdfDto, headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                PDF_MICROSERVICE_URL + "/generate-owner-receipt",
                HttpMethod.POST,
                requestEntity,
                byte[].class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new RuntimeException("No se pudo generar el PDF");
        }
    }

}
