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
import com.dam.commune.property.parking.Parking;
import com.dam.commune.property.parking.ParkingDTO;
import com.dam.commune.property.parking.ParkingRepository;
import com.dam.commune.property.storageRoom.StorageRoom;
import com.dam.commune.property.storageRoom.StorageRoomDTO;
import com.dam.commune.property.storageRoom.StorageRoomRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final FlatRepository flatRepository;
    private final ParkingRepository parkingRepository;
    private final StorageRoomRepository storageRoomRepository;
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
        existingFlat.setCoefficient(flatDTO.getCoefficient());

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

    @Transactional
public Parking updateParking(ParkingDTO parkingDTO) {
    Parking existingParking = parkingRepository.findById(parkingDTO.getId())
            .orElseThrow(() -> new IllegalArgumentException("Parking not found with id: " + parkingDTO.getId()));

    existingParking.setCadastralReference(parkingDTO.getCadastralReference());
    existingParking.setSquareMeters(parkingDTO.getSquareMeters());
    existingParking.setNum(parkingDTO.getNum());
    existingParking.setCoefficient(parkingDTO.getCoefficient());

    if (parkingDTO.getCommunityName() != null &&
        !parkingDTO.getCommunityName().equals(existingParking.getCommunity().getAddress())) {
        Community community = communityRepository.findByAddress(parkingDTO.getCommunityName());
        if (community == null) {
            throw new IllegalArgumentException("Community not found with address: " + parkingDTO.getCommunityName());
        }
        existingParking.setCommunity(community);
    }

    if (parkingDTO.getOwnerDni() != null) {
        Owner owner = ownerRepository.findByDni(parkingDTO.getOwnerDni())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with DNI: " + parkingDTO.getOwnerDni()));
        existingParking.setOwner(owner);
    }

    return parkingRepository.save(existingParking);
}


    @Transactional
    public StorageRoom updateStorageRoom(StorageRoomDTO storageRoomDTO) {
        StorageRoom existingStorageRoom = storageRoomRepository.findById(storageRoomDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Flat not found with id: " + storageRoomDTO.getId()));

        existingStorageRoom.setCadastralReference(storageRoomDTO.getCadastralReference());
        existingStorageRoom.setSquareMeters(storageRoomDTO.getSquareMeters());
        existingStorageRoom.setStorageNumber(storageRoomDTO.getStorageNumber());
        existingStorageRoom.setCoefficient(storageRoomDTO.getCoefficient());
        

        if (storageRoomDTO.getCommunityName() != null &&
            !storageRoomDTO.getCommunityName().equals(existingStorageRoom.getCommunity().getAddress())) {
            Community community = communityRepository.findByAddress(storageRoomDTO.getCommunityName());
            if (community == null) {
                throw new IllegalArgumentException("Community not found with address: " + storageRoomDTO.getCommunityName());
            }
            existingStorageRoom.setCommunity(community);
        }

        if (storageRoomDTO.getOwnerDni() != null) { 
            Owner owner = ownerRepository.findByDni(storageRoomDTO.getOwnerDni())
                    .orElseThrow(() -> new IllegalArgumentException("Owner not found with DNI: " + storageRoomDTO.getOwnerDni()));
            existingStorageRoom.setOwner(owner);
        }

        return storageRoomRepository.save(existingStorageRoom);
    }

    

}
