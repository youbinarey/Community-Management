package com.dam.commune.invoice.owner;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OwnerInvoiceMapper {

    @Mapping(target = "id", source = "ownerInvoice.id")
    @Mapping(target = "date", source = "ownerInvoice.date")
    @Mapping(target = "amount", source = "ownerInvoice.amount")
    @Mapping(target = "invoiceId", source = "invoice.id")
    @Mapping(target = "ownerName", source = "owner.name")
    @Mapping(target = "ownerSurname", source = "owner.surname")
    @Mapping(target = "ownerBankAccount", source = "owner.bankAccount.accountNumber")
    @Mapping(target = "coefficient", source = "property.coefficient")
    @Mapping(target = "communityName", source = "invoice.community.address")
    @Mapping(target = "email", source = "owner.email")
    OwnerInvoiceDTO toDto(OwnerInvoice ownerInvoice);

}
