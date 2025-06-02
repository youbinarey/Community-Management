package com.dam.commune.invoice.owner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OwnerInvoiceDTO {
    private Long id;
    private String date;
    private Double amount;
    private Long invoiceId;
    private String ownerName;
    private String ownerSurname;
    private String ownerBankAccount;
    // private List<Properties> properties;
    private Double coefficient;
    private String communityName;
    private String email;
}
