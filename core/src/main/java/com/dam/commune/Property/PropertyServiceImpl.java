package com.dam.commune.property;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;



@Service
public class PropertyServiceImpl implements PropertyService{
    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
    @Override
    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Optional<Property> findById(Long id) {
       return propertyRepository.findById(id);
    }

    @Override
    public Property save(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public Property update(Long id, Property property) {
        return propertyRepository.findById(id)
                .map(existingProperty -> {
                    existingProperty.setReferenciaCatastral(property.getReferenciaCatastral());
                    existingProperty.setMetros2(property.getMetros2());
                    return propertyRepository.save(existingProperty);
                })
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }

    @Override
    public boolean deleteIfExists(Long id) {
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
