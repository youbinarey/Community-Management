package com.dam.commune.property;

import java.util.List;
import org.springframework.stereotype.Service;

import com.dam.commune.community.Community;
import com.dam.commune.community.CommunityRepository;
import com.dam.commune.owner.Owner;
import com.dam.commune.owner.OwnerRepository;
import com.dam.commune.property.flat.Flat;
import com.dam.commune.property.flat.FlatDTO;
import com.dam.commune.property.flat.FlatRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final FlatRepository flatRepository;
    private final CommunityRepository communityRepository;
    
    
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

     @Transactional
    public Flat updateFlat(FlatDTO flatDTO) {
        Flat existingFlat = flatRepository.findById(flatDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Flat not found with id: " + flatDTO.getId()));

        existingFlat.setCadastralReference(flatDTO.getCadastralReference());
        existingFlat.setSquareMeters(flatDTO.getSquareMeters());
        existingFlat.setFloorNumber(flatDTO.getFloorNumber());
        existingFlat.setLetter(flatDTO.getLetter());
        existingFlat.setRoomCount(flatDTO.getRoomCount());
        existingFlat.setBathroomCount(flatDTO.getBathroomCount());

        if (flatDTO.getCommunityName() != null &&
            !flatDTO.getCommunityName().equals(existingFlat.getCommunity().getAddress())) {
            Community community = communityRepository.findByAddress(flatDTO.getCommunityName());
            if (community == null) {
                throw new IllegalArgumentException("Community not found with address: " + flatDTO.getCommunityName());
            }
            existingFlat.setCommunity(community);
        }

        if (flatDTO.getOwnerDni() != null) {  // Cambiar a ownerDni
            Owner owner = ownerRepository.findByDni(flatDTO.getOwnerDni())
                    .orElseThrow(() -> new IllegalArgumentException("Owner not found with DNI: " + flatDTO.getOwnerDni()));
            existingFlat.setOwner(owner);
        }

        return flatRepository.save(existingFlat);
    }

}
