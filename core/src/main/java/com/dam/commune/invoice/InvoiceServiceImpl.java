package com.dam.commune.invoice;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dam.commune.community.Community;
import com.dam.commune.community.CommunityRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CommunityRepository communityRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public InvoiceDTO saveInvoice(InvoiceDTO dto) {
        Community community = communityRepository.findById(dto.getCommunityId())
                .orElseThrow(() -> new EntityNotFoundException("Community not found with id: " + dto.getCommunityId()));
        Invoice invoice = invoiceMapper.toEntity(dto, community);
        Invoice saved = invoiceRepository.save(invoice);
        return invoiceMapper.toDto(saved);
    }

    @Override
    public List<InvoiceDTO> getInvoicesByCommunity(Long communityId) {
        return invoiceRepository.findByCommunityId(communityId)
                .stream()
                .map(invoiceMapper::toDto)
                .toList();
    }

    @Override
    public InvoiceDTO getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toDto)
                .orElse(null);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}

