package com.dam.commune.microservices.pdfservice;

import java.time.format.DateTimeFormatter;

import com.dam.commune.invoice.InvoiceDTO;

public class InvoicePdfMapper {
    public static InvoicePdfDTO from(InvoiceDTO dto) {
        InvoicePdfDTO pdf = new InvoicePdfDTO();
        pdf.setAddress(dto.getCommunityName());
        pdf.setDate(dto.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        pdf.setElectricity(dto.getElectricity());
        pdf.setWater(dto.getWater());
        pdf.setTrash(dto.getTrash());
        pdf.setElevator(dto.getElevator());
        pdf.setMaintenance(dto.getMaintenance());
        return pdf;
    }
}
