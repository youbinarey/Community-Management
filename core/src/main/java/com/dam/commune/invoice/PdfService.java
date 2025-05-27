package com.dam.commune.invoice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PdfService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String PDF_MICROSERVICE_URL = "http://localhost:5000/generate-pdf";

    public byte[] generatePdf(InvoicePdfDTO pdfDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<InvoicePdfDTO> requestEntity = new HttpEntity<>(pdfDto, headers);

        ResponseEntity<byte[]> response = restTemplate.exchange(
                PDF_MICROSERVICE_URL,
                HttpMethod.POST,
                requestEntity,
                byte[].class
        );

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new RuntimeException("No se pudo generar el PDF");
        }
    }
}
