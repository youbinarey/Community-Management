package com.dam.commune.microservices.pdfservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoicePdfDTO {
    private String address;
    private String date;
    private Double electricity;
    private Double water;
    private Double trash;
    private Double elevator;
    private Double maintenance;
}
