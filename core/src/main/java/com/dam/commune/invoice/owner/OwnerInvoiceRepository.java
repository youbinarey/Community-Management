package com.dam.commune.invoice.owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerInvoiceRepository extends JpaRepository<OwnerInvoice, Long> {

    List<OwnerInvoice> findByOwnerId(Long ownerId);

    List<OwnerInvoice> findByInvoiceId(Long invoiceId);

    List<OwnerInvoice> findByPropertyCommunityId(Long communityId);

    List<OwnerInvoice> findByInvoiceIdAndOwnerId(Long invoiceId, Long ownerId);

}
