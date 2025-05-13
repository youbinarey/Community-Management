package com.dam.commune.property;

import java.util.List;
import org.springframework.stereotype.Service;



@Service
public class PropertyServiceImpl implements PropertyService{
    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
   

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public boolean existsByCadastralReference(String ref) {
        return propertyRepository.existsByCadastralReference(ref);
    }
 
}
