package com.dam.commune.property;

import java.util.List;
import org.springframework.stereotype.Service;

import com.dam.commune.owner.Owner;
import com.dam.commune.owner.OwnerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    
    
    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public boolean existsByCadastralReference(String ref) {
        return propertyRepository.existsByCadastralReference(ref);
    }

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Property with ID " + id + " not found"));

        Owner owner = property.getOwner();

        // Eliminar la propiedad
        propertyRepository.delete(property);

        // Si no quedan más propiedades para ese owner, eliminar también el owner
        if (owner != null && !propertyRepository.existsByOwnerId(owner.getId())) {
            ownerRepository.delete(owner);
        }

    }
}
