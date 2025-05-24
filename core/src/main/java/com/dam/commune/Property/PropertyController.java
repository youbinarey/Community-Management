package com.dam.commune.property;

import com.dam.commune.property.parking.ParkingRepository;
import com.dam.commune.community.Community;
import com.dam.commune.community.CommunityRepository; 
import com.dam.commune.property.flat.Flat;
import com.dam.commune.property.flat.FlatDTO;
import com.dam.commune.property.flat.FlatMapper;
import com.dam.commune.property.flat.FlatRepository;
import com.dam.commune.property.parking.Parking;
import com.dam.commune.property.storageRoom.StorageRoom;
import com.dam.commune.property.storageRoom.StorageRoomRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {

    private final PropertyService propertyService;
    private final FlatRepository flatRepository;
    private final CommunityRepository communityRepository;
    private final ParkingRepository parkingRepository;
    private final StorageRoomRepository storageRoomRepository;

    // 🔹 Obtener todos los inmuebles
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
    try {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build(); // 204
    } catch (EntityNotFoundException ex) {
        return ResponseEntity.notFound().build(); // 404
    }
}


    // -----------------------------
    // CRUD para flat (Apartment)
    // -----------------------------

    // GET para obtener todos los flats
@GetMapping("/flat")
public ResponseEntity<List<FlatDTO>> getAllFlats() {
    List<Flat> flats = flatRepository.findAll();  // Obtener todos los flats de la base de datos

    // Convertir cada Flat a un FlatDTO
    List<FlatDTO> flatDTOs = flats.stream().map(flat -> new FlatDTO(
            flat.getId(),
            flat.getCadastralReference(),
            flat.getSquareMeters(),
            flat.getFloorNumber(),
            flat.getLetter(),
            flat.getRoomCount(),
            flat.getBathroomCount(),
            flat.getCommunity() != null ? flat.getCommunity().getAddress() : null,
            flat.getOwner() != null ? flat.getOwner().getName() : null
    )).collect(Collectors.toList());

    return ResponseEntity.ok(flatDTOs);  // Retorna la lista de FlatDTOs con el estado 200 OK
}


    // Crear un nuevo Flat
@PostMapping("/flat")
public ResponseEntity<FlatDTO> createFlat(@RequestBody FlatDTO flatDTO) {
    Flat flat = new Flat(); // Crear un objeto Flat vacío

    // Mapear el DTO a la entidad Flat
    flat.setCadastralReference(flatDTO.getCadastralReference());
    flat.setSquareMeters(flatDTO.getSquareMeters());
    flat.setFloorNumber(flatDTO.getFloorNumber());
    flat.setLetter(flatDTO.getLetter());
    flat.setRoomCount(flatDTO.getRoomCount());
    flat.setBathroomCount(flatDTO.getBathroomCount());


    Community community = communityRepository.findByAddress(flatDTO.getCommunityName());
    if (community != null) {
        flat.setCommunity(community);
    }

    // TODO 
    // Owner owner = ownerRepository.findByName(flatDTO.getOwnerName());
    // if (owner != null) {
    //     flat.setOwner(owner);
    // }

  
    Flat savedFlat = flatRepository.save(flat);
    return ResponseEntity.status(HttpStatus.CREATED).body(FlatMapper.toDTO(savedFlat));
}

@GetMapping("/flat/community/{communityId}")
public ResponseEntity<List<FlatDTO>> getFlatsByCommunity(@PathVariable Long communityId) {
    // Buscar la comunidad por su ID
    Community community = communityRepository.findById(communityId)
            .orElse(null); // Si no se encuentra, retornamos un valor nulo

    if (community == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Comunidad no encontrada
    }

    // Obtener los flats que pertenecen a esta comunidad
    List<Flat> flats = flatRepository.findByCommunity(community);

    // Mapear los Flats a FlatDTO
    List<FlatDTO> flatDTOs = flats.stream().map(flat -> new FlatDTO(
            flat.getId(),
            flat.getCadastralReference(),
            flat.getSquareMeters(),
            flat.getFloorNumber(),
            flat.getLetter(),
            flat.getRoomCount(),
            flat.getBathroomCount(),
            flat.getCommunity() != null ? flat.getCommunity().getAddress() : null,
            flat.getOwner() != null ? flat.getOwner().getName() : null
    )).collect(Collectors.toList());

    return ResponseEntity.ok(flatDTOs);  // Retorna la lista de FlatDTOs con el estado 200 OK
}


    @GetMapping("/flat/{id}")
    public ResponseEntity<FlatDTO> getFlat(@PathVariable Long id) {
    return flatRepository.findById(id)
            .map(flat -> ResponseEntity.ok(FlatMapper.toDTO(flat)))
            .orElse(ResponseEntity.notFound().build());
}

    @PutMapping("/flat/{id}")
    public ResponseEntity<Flat> updateflat(@PathVariable Long id, @RequestBody Flat updatedflat) {
        return flatRepository.findById(id)
                .map(existing -> {
                    updatedflat.setId(id);
                    return ResponseEntity.ok(flatRepository.save(updatedflat));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/flat/{id}")
    public ResponseEntity<Void> deleteflat(@PathVariable Long id) {
        if (flatRepository.existsById(id)) {
            flatRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // -----------------------------
    // CRUD para parking
    // -----------------------------

    @PostMapping("/parking")
    public ResponseEntity<Parking> createparking(@RequestBody Parking parking) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingRepository.save(parking));
    }

    @GetMapping("/parking/{id}")
    public ResponseEntity<Parking> getparking(@PathVariable Long id) {
        return parkingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/parking/{id}")
    public ResponseEntity<Parking> updateparking(@PathVariable Long id, @RequestBody Parking updatedparking) {
        return parkingRepository.findById(id)
                .map(existing -> {
                    updatedparking.setId(id);
                    return ResponseEntity.ok(parkingRepository.save(updatedparking));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/parking/{id}")
    public ResponseEntity<Void> deleteparking(@PathVariable Long id) {
        if (parkingRepository.existsById(id)) {
            parkingRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // -----------------------------
    // CRUD para STORAGE ROOM
    // -----------------------------

    @PostMapping("/storageroom")
    public ResponseEntity<StorageRoom> createStorageRoom(@RequestBody StorageRoom storageRoom) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storageRoomRepository.save(storageRoom));
    }

    @GetMapping("/storageroom/{id}")
    public ResponseEntity<StorageRoom> getStorageRoom(@PathVariable Long id) {
        return storageRoomRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/storageroom/{id}")
    public ResponseEntity<StorageRoom> updateStorageRoom(@PathVariable Long id, @RequestBody StorageRoom updatedRoom) {
        return storageRoomRepository.findById(id)
                .map(existing -> {
                    updatedRoom.setId(id);
                    return ResponseEntity.ok(storageRoomRepository.save(updatedRoom));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/storageroom/{id}")
    public ResponseEntity<Void> deleteStorageRoom(@PathVariable Long id) {
        if (storageRoomRepository.existsById(id)) {
            storageRoomRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
