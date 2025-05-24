package com.dam.commune.owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    List<Owner> getAll();
    List<OwnerDTO> getAllDTOs();
    Optional<Owner> getById(Long id);
    Owner create(Owner owner);
    Owner update(Long id, Owner owner);
    void delete(Long id);
    boolean existsByDni(String dni);
    boolean deleteIfExists(Long id);
}

