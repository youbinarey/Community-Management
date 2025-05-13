package com.dam.commune.property;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
   boolean existsByReferenciaCatastral(String referenciaCatastral);
    
}
