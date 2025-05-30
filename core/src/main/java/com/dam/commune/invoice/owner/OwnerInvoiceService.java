package com.dam.commune.invoice.owner;

import java.util.List;

public interface OwnerInvoiceService {
    OwnerInvoiceDTO getById(Long id);
    List<OwnerInvoiceDTO> getByOwnerId(Long ownerId);
    List<OwnerInvoiceDTO> getByInvoiceId(Long invoiceId);
    // OwnerInvoiceDTO saveOwnerInvoice(OwnerInvoiceDTO dto);
    void deleteOwnerInvoice(Long id);
}
