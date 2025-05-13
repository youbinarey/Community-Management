package com.dam.commune.property;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
        List<Property> findAll();
        Optional<Property> findById(Long id);
        Property save(Property property);
        Property update(Long id, Property property);
        boolean deleteIfExists(Long id);
}
