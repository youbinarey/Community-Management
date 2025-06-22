package com.dam.commune.invoice;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO saveInvoice(InvoiceDTO dto);

    List<InvoiceDTO> getInvoicesByCommunity(Long communityId);

    InvoiceDTO getInvoiceById(Long id);

    void deleteInvoice(Long id);

    List<InvoiceDTO> getAllInvoices();

}
