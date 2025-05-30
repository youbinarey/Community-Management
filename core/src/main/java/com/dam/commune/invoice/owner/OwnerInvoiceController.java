package com.dam.commune.invoice.owner;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/owner-invoices")
@CrossOrigin(origins = "http://localhost:4200")

public class OwnerInvoiceController {

      private final OwnerInvoiceService ownerInvoiceService;

    // Obtener un recibo por ID
    @GetMapping("/{id}")
    public OwnerInvoiceDTO getById(@PathVariable Long id) {
        return ownerInvoiceService.getById(id);
    }

    // Obtener todos los recibos de un propietario
    @GetMapping("/owner/{ownerId}")
    public List<OwnerInvoiceDTO> getByOwnerId(@PathVariable Long ownerId) {
        return ownerInvoiceService.getByOwnerId(ownerId);
    }

    // Obtener todos los recibos por factura
    @GetMapping("/invoice/{invoiceId}")
    public List<OwnerInvoiceDTO> getByInvoiceId(@PathVariable Long invoiceId) {
        return ownerInvoiceService.getByInvoiceId(invoiceId);
    }

    // Eliminar un recibo por ID
    @DeleteMapping("/{id}")
    public void deleteOwnerInvoice(@PathVariable Long id) {
        ownerInvoiceService.deleteOwnerInvoice(id);
    }

    // OPCIONAL: Si implementas buscar por comunidad
    // @GetMapping("/community/{communityId}")
    // public List<OwnerInvoiceDTO> getByCommunityId(@PathVariable Long communityId) {
    //     return ownerInvoiceService.getByCommunityId(communityId);
    // }

    // OPCIONAL: Si implementas buscar por invoice + owner
    // @GetMapping("/invoice/{invoiceId}/owner/{ownerId}")
    // public List<OwnerInvoiceDTO> getByInvoiceAndOwner(@PathVariable Long invoiceId, @PathVariable Long ownerId) {
    //     return ownerInvoiceService.getByInvoiceIdAndOwnerId(invoiceId, ownerId);
    // }

}
