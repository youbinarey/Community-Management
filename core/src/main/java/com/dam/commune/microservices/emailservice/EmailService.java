package com.dam.commune.microservices.emailservice;

import java.time.LocalDate;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Service for sending emails with PDF invoice attachments via an external email microservice.
 * <p>
 * This service uses a {@link RestTemplate} to send HTTP POST requests to the configured
 * email microservice endpoint. It constructs a multipart/form-data request containing
 * the recipient's email, subject, body, and the PDF file as an attachment.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     emailService.sendOwnerInvoicePdf(pdfBytes, "recipient@example.com", "Invoice Subject", "Email body text");
 * </pre>
 * </p>
 *
 * <p>
 * If the email microservice responds with a non-2xx status code, a {@link RuntimeException} is thrown.
 * </p>
 */
@Service
public class EmailService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String EMAIL_MICROSERVICE_URL = "http://emailsender:8000/send-invoice-email/";

    public void sendOwnerInvoicePdf(byte[] pdfBytes, String recipientEmail, String subject, String body) {
        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes) {
            @Override
            public String getFilename() {
                return "Factura-comunidad-" + LocalDate.now().toString() + ".pdf";
            }
        };

        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("recipient", recipientEmail);
        bodyMap.add("subject", subject);
        bodyMap.add("body", body);
        bodyMap.add("file", pdfResource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                EMAIL_MICROSERVICE_URL,
                requestEntity,
                String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al enviar email: " + response.getBody());
        }

    }

}
