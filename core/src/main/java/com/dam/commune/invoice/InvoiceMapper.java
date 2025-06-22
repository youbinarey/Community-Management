package com.dam.commune.invoice;

import com.dam.commune.community.Community;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    public InvoiceDTO toDto(Invoice invoice) {
        if (invoice == null)
            return null;
        return InvoiceDTO.builder()
                .id(invoice.getId())
                .date(invoice.getDate())
                .electricity(invoice.getElectricity())
                .water(invoice.getWater())
                .trash(invoice.getTrash())
                .elevator(invoice.getElevator())
                .maintenance(invoice.getMaintenance())
                .communityId(
                        invoice.getCommunity() != null ? invoice.getCommunity().getId() : null)
                .communityName(
                        invoice.getCommunity() != null ? invoice.getCommunity().getAddress() : null)
                .build();
    }

    public Invoice toEntity(InvoiceDTO dto, Community community) {
        if (dto == null || community == null)
            return null;
        return Invoice.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .electricity(dto.getElectricity())
                .water(dto.getWater())
                .trash(dto.getTrash())
                .elevator(dto.getElevator())
                .maintenance(dto.getMaintenance())
                .community(community)
                .build();
    }
}
