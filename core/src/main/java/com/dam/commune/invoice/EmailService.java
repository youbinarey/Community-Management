package com.dam.commune.invoice;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



@Service
public class EmailService {

    private final RestTemplate restTemplate= new RestTemplate();    
    private final String EMAIL_MICROSERVICE_URL = "http://localhost:8000/send-invoice-email/";


    public void sendOwnerInvoicePdf(byte[] pdfBytes, String recipientEmail, String subject, String body) {
        ByteArrayResource pdfResource = new ByteArrayResource(pdfBytes) {
            @Override
            public String getFilename() {
                return "owner-invoice.pdf";
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
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al enviar email: " + response.getBody());
        }

    }

    
}
