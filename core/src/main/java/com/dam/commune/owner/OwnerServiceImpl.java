package com.dam.commune.owner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dam.commune.property.Property;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * Service implementation for managing Owner entities.
 * Provides methods for CRUD operations, DTO mapping, and property retrieval.
 * 
 * <p>
 * This class interacts with the {@link OwnerRepository} to perform persistence
 * operations,
 * and uses {@link OwnerMapper} for mapping between entities and DTOs.
 * </p>
 * 
 * <ul>
 * <li>{@code getAll()} - Retrieves all owners.</li>
 * <li>{@code getById(Long)} - Retrieves an owner by ID.</li>
 * <li>{@code create(Owner)} - Creates a new owner.</li>
 * <li>{@code update(Long, Owner)} - Updates an existing owner.</li>
 * <li>{@code delete(Long)} - Deletes an owner by ID.</li>
 * <li>{@code existsByDni(String)} - Checks if an owner exists by DNI.</li>
 * <li>{@code deleteIfExists(Long)} - Deletes an owner if it exists.</li>
 * <li>{@code getAllDTOs()} - Retrieves all owners as DTOs.</li>
 * <li>{@code getPropertiesByOwnerId(Long)} - Retrieves properties for a given
 * owner.</li>
 * <li>{@code updateOwnerDTO(Long, OwnerDTO)} - Updates an owner using a
 * DTO.</li>
 * <li>{@code getOwnerDTOById(Long)} - Retrieves an owner as a DTO by ID.</li>
 * </ul>
 * 
 * @see OwnerService
 * @see OwnerRepository
 * @see OwnerMapper
 */

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Optional<Owner> getById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Long id, Owner owner) {
        return ownerRepository.findById(id)
                .map(existingOwner -> {
                    existingOwner.setName(owner.getName());
                    existingOwner.setDni(owner.getDni());
                    existingOwner.setPhone(owner.getPhone());
                    existingOwner.setEmail(owner.getEmail());
                    return ownerRepository.save(existingOwner);
                })
                .orElseThrow(() -> new EntityNotFoundException("Owner with " + id + "not found"));
    }

    @Override
    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public boolean existsByDni(String dni) {
        return ownerRepository.existsByDni(dni);
    }

    @Override
    public boolean deleteIfExists(Long id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<OwnerDTO> getAllDTOs() {
        return ownerRepository.findAll().stream()
                .map(OwnerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Property> getPropertiesByOwnerId(Long ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new EntityNotFoundException("Owner with id " + ownerId + " not found"));
        return owner.getProperties();
    }

    @Override
    public OwnerDTO updateOwnerDTO(Long id, OwnerDTO ownerDTO) {
        Owner existiOwner = ownerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Owner with id " + id + " not found"));
        OwnerMapper.updateEntityFromDTO(ownerDTO, existiOwner);
        Owner updatedOwner = ownerRepository.save(existiOwner);
        return OwnerMapper.toDTO(updatedOwner);
    }

    @Override
    public Optional<OwnerDTO> getOwnerDTOById(Long id) {
        return ownerRepository.findById(id)
                .map(OwnerMapper::toDTO);
    }

}
