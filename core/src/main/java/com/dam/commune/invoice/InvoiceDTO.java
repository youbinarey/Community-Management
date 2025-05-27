package com.dam.commune.invoice;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDTO {
    private Long id;
    private LocalDate date;
    private Double electricity;
    private Double water;
    private Double trash;
    private Double elevator;
    private Double maintenance;
    private Long communityId;
    private String communityName;
}
