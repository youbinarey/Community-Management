package com.dam.commune.invoice;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.dam.commune.community.Community;
import com.dam.commune.community.CommunityRepository;
import com.dam.commune.invoice.owner.OwnerInvoice;
import com.dam.commune.invoice.owner.OwnerInvoiceRepository;
import com.dam.commune.owner.Owner;
import com.dam.commune.property.Property;
import com.dam.commune.property.PropertyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CommunityRepository communityRepository;
    private final InvoiceMapper invoiceMapper;
    private final PropertyRepository propertyRepository;
    private final OwnerInvoiceRepository ownerInvoiceRepository;

@Override
@Transactional
public InvoiceDTO saveInvoice(InvoiceDTO dto) {
    // 1️⃣ Buscar la comunidad por ID
    Community community = communityRepository.findById(dto.getCommunityId())
            .orElseThrow(() -> new RuntimeException("Comunidad no encontrada con id: " + dto.getCommunityId()));

    // 2️⃣ Crear y guardar la factura comunitaria
    Invoice invoice = invoiceMapper.toEntity(dto, community);
    Invoice savedInvoice = invoiceRepository.save(invoice);

    // 3️⃣ Obtener todas las propiedades de la comunidad
    List<Property> properties = propertyRepository.findByCommunity_Id(community.getId());

    // 4️⃣ Agrupar propiedades por propietario
    Map<Owner, List<Property>> propertiesByOwner = properties.stream()
            .filter(p -> p.getOwner() != null)
            .collect(Collectors.groupingBy(Property::getOwner));

    // 5️⃣ Calcular el total de la factura
    double totalFactura = sum(
            dto.getElectricity(),
            dto.getWater(),
            dto.getTrash(),
            dto.getElevator(),
            dto.getMaintenance()
    );

    // 6️⃣ Generar y guardar un OwnerInvoice por cada propiedad del propietario
    for (Map.Entry<Owner, List<Property>> entry : propertiesByOwner.entrySet()) {
        Owner owner = entry.getKey();
        List<Property> ownerProperties = entry.getValue();

        for (Property property : ownerProperties) {
            // Calcula el amount para esta propiedad: totalFactura * coeficiente / 100
            double amount = totalFactura * (property.getCoefficient() / 100.0);

            OwnerInvoice ownerInvoice = OwnerInvoice.builder()
                    .invoice(savedInvoice)
                    .owner(owner)
                    .property(property)
                    .date(savedInvoice.getDate().toString())
                    .amount(amount)
                    .build();

            ownerInvoiceRepository.save(ownerInvoice);
        }
    }

    // 7️⃣ Devolver el DTO del Invoice guardado
    return invoiceMapper.toDto(savedInvoice);
}



// Helper para sumar Double (evita NPE)
private double sum(Double... values) {
    double result = 0;
    for (Double v : values) {
        if (v != null) result += v;
    }
    return result;
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

