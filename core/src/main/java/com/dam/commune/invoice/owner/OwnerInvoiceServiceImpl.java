package com.dam.commune.invoice.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerInvoiceServiceImpl implements OwnerInvoiceService {

    private final OwnerInvoiceRepository repository;
    private final OwnerInvoiceMapper mapper;

    @Override
    public OwnerInvoiceDTO getById(Long id) {
        OwnerInvoice entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("OwnerInvoice not found: " + id));
        return mapper.toDto(entity);
    }

    @Override
    public List<OwnerInvoiceDTO> getByOwnerId(Long ownerId) {
        return repository.findByOwnerId(ownerId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OwnerInvoiceDTO> getByInvoiceId(Long invoiceId) {
        return repository.findByInvoiceId(invoiceId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // @Override
    // public OwnerInvoiceDTO saveOwnerInvoice(OwnerInvoiceDTO dto) {
    //     OwnerInvoice entity = mapper.toEntity(dto);
    //     OwnerInvoice saved = repository.save(entity);
    //     return mapper.toDto(saved);
    // }

    @Override
    public void deleteOwnerInvoice(Long id) {
        repository.deleteById(id);
    }
}
