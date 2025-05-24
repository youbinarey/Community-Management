package com.dam.commune.property;

import java.util.List;


public interface PropertyService {
       List<Property> getAllProperties();
        boolean existsByCadastralReference(String ref);
        void deleteProperty(Long id);
        
       

}
