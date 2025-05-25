package com.dam.commune.property;

import com.dam.commune.property.parking.ParkingRepository;
import com.dam.commune.community.Community;
import com.dam.commune.community.CommunityRepository;
import com.dam.commune.owner.Owner;
import com.dam.commune.owner.OwnerRepository;
import com.dam.commune.property.flat.Flat;
import com.dam.commune.property.flat.FlatDTO;
import com.dam.commune.property.flat.FlatMapper;
import com.dam.commune.property.flat.FlatRepository;
import com.dam.commune.property.parking.Parking;
import com.dam.commune.property.parking.ParkingDTO;
import com.dam.commune.property.parking.ParkingMapper;
import com.dam.commune.property.storageRoom.StorageRoom;
import com.dam.commune.property.storageRoom.StorageRoomDTO;
import com.dam.commune.property.storageRoom.StorageRoomMapper;
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
    private final PropertyServiceImpl propertyServiceImpl;
    private final OwnerRepository ownerRepository;

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
        List<Flat> flats = flatRepository.findAll(); // Obtener todos los flats de la base de datos

        List<FlatDTO> flatDTOs = flats.stream().map(flat -> new FlatDTO(
                flat.getId(),
                flat.getCadastralReference(),
                flat.getSquareMeters(),
                flat.getFloorNumber(),
                flat.getLetter(),
                flat.getRoomCount(),
                flat.getBathroomCount(),
                flat.getCommunity() != null ? flat.getCommunity().getAddress() : null,
                flat.getOwner() != null ? flat.getOwner().getName() : null,
                flat.getOwner() != null ? flat.getOwner().getDni() : null)).collect(Collectors.toList());

        return ResponseEntity.ok(flatDTOs); // Retorna la lista de FlatDTOs con el estado 200 OK
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
        // flat.setOwner(owner);
        // }

        Flat savedFlat = flatRepository.save(flat);
        return ResponseEntity.status(HttpStatus.CREATED).body(FlatMapper.toDTO(savedFlat));
    }

    @GetMapping("parking/community/{communityId}")
    public ResponseEntity<List<ParkingDTO>> getParkingsByCommunity(@PathVariable Long communityId) {
        // Buscar la comunidad por su ID
        Community community = communityRepository.findById(communityId)
                .orElse(null);
        if (community == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Parking> parkings = parkingRepository.findByCommunity(community);

        List<ParkingDTO> parkingDTOs = parkings.stream().map(parking -> new ParkingDTO(
                parking.getId(),
                parking.getCadastralReference(),
                parking.getSquareMeters(),
                parking.getNum(),
                parking.getCommunity() != null ? parking.getCommunity().getAddress() : null,
                parking.getOwner() != null ? parking.getOwner().getName() : null,
                parking.getOwner() != null ? parking.getOwner().getDni() : null)).collect(Collectors.toList());

        return ResponseEntity.ok(parkingDTOs);

    }

    @GetMapping("storageroom/community/{communityId}")
    public ResponseEntity<List<StorageRoomDTO>> getStorageRoomsByCommunity(@PathVariable Long communityId) {
        // Buscar la comunidad por su ID
        Community community = communityRepository.findById(communityId)
                .orElse(null);
        if (community == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<StorageRoom> storageRooms = storageRoomRepository.findByCommunity(community);

        List<StorageRoomDTO> storageRoomDTOs = storageRooms.stream().map(storageRoom -> new StorageRoomDTO(
                storageRoom.getId(),
                storageRoom.getCadastralReference(),
                storageRoom.getSquareMeters(),
                storageRoom.getStorageNumber(),
                storageRoom.getCommunity() != null ? storageRoom.getCommunity().getAddress() : null,
                storageRoom.getOwner() != null ? storageRoom.getOwner().getName() : null,
                storageRoom.getOwner() != null ? storageRoom.getOwner().getDni() : null)).collect(Collectors.toList());

        return ResponseEntity.ok(storageRoomDTOs);
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
                flat.getOwner() != null ? flat.getOwner().getName() : null,
                flat.getOwner() != null ? flat.getOwner().getDni() : null)).collect(Collectors.toList());

        return ResponseEntity.ok(flatDTOs);
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

    // @GetMapping("/parking/dto")
    // public List<Parking> getAllParkingsDTO() {
    // return parkingRepository.findAll().stream()
    // .map(parking -> new Parking(
    // parking.getId(),
    // parking.getNum(),
    // parking.getCommunity() != null ? parking.getCommunity().getAddress() : null,
    // parking.getOwner() != null ? parking.getOwner().getName() : null
    // )).collect(Collectors.toList());
    // }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }

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

    @GetMapping("parking")
    public List<Parking> getAllParkings() {
        return parkingRepository.findAll();
    }

    // -----------------------------
    // CRUD para STORAGE ROOM
    // -----------------------------

    @GetMapping("/storageroom")
    public List<StorageRoom> getAllStorageRooms() {
        return storageRoomRepository.findAll();
    }

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

    // ENDPOINT EDITAR FLAT

    @PutMapping("/{id}")
    public ResponseEntity<FlatDTO> updateFlat(@PathVariable Long id, @RequestBody FlatDTO flatDTO) {
        flatDTO.setId(id); // Aseguramos que el id en path y body coincidan
        Flat updatedFlat = propertyServiceImpl.updateFlat(flatDTO);

        // Convertir a DTO para devolver
        FlatDTO responseDto = new FlatDTO(
                updatedFlat.getId(),
                updatedFlat.getCadastralReference(),
                updatedFlat.getSquareMeters(),
                updatedFlat.getFloorNumber(),
                updatedFlat.getLetter(),
                updatedFlat.getRoomCount(),
                updatedFlat.getBathroomCount(),
                updatedFlat.getCommunity().getAddress(),
                updatedFlat.getOwner() != null ? updatedFlat.getOwner().getName() : null,
                updatedFlat.getOwner() != null ? updatedFlat.getOwner().getDni() : null);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/create-flat")
    public ResponseEntity<FlatDTO> createFlatDTO(@RequestBody FlatDTO flatDTO) {
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

        Owner owner = ownerRepository.findByDni(flatDTO.getOwnerDni())
                .orElse(null); // Cambiar a buscar por DNI
        if (owner != null) {
            flat.setOwner(owner);
        }

        Flat savedFlat = flatRepository.save(flat);
        return ResponseEntity.status(HttpStatus.CREATED).body(FlatMapper.toDTO(savedFlat));
    }

    @PostMapping("/create-parking")
    public ResponseEntity<ParkingDTO> createParkingDTO(@RequestBody ParkingDTO parkingDTO) {
        Parking parking = new Parking();

        parking.setCadastralReference(parkingDTO.getCadastralReference());
        parking.setSquareMeters(parkingDTO.getSquareMeters());
        parking.setNum(parkingDTO.getNum());

        Community community = communityRepository.findByAddress(parkingDTO.getCommunityName());
        if (community != null) {
            parking.setCommunity(community);
        }

        Owner owner = ownerRepository.findByDni(parkingDTO.getOwnerDni())
                .orElse(null);
        if (owner != null) {
            parking.setOwner(owner);
        }

        Parking saveParking = parkingRepository.save(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(ParkingMapper.toDTO(saveParking));
    }

    @PostMapping("/create-storage-room")
    public ResponseEntity<StorageRoomDTO> createStorageRoomDTO(@RequestBody StorageRoomDTO storageRoomDTO) {
        StorageRoom storageRoom = new StorageRoom();

        storageRoom.setCadastralReference(storageRoomDTO.getCadastralReference());
        storageRoom.setSquareMeters(storageRoomDTO.getSquareMeters());
        storageRoom.setStorageNumber(storageRoomDTO.getStorageNumber());

        // Aquí NO debes usar getCommunityName() en StorageRoom, sino en StorageRoomDTO
        Community community = communityRepository.findByAddress(storageRoomDTO.getCommunityName());
        if (community == null) {
            throw new IllegalArgumentException(
                    "Community not found with address: " + storageRoomDTO.getCommunityName());
        }
        storageRoom.setCommunity(community);

        Owner owner = ownerRepository.findByDni(storageRoomDTO.getOwnerDni())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Owner not found with DNI: " + storageRoomDTO.getOwnerDni()));
        storageRoom.setOwner(owner);

        StorageRoom saveStorageRoom = storageRoomRepository.save(storageRoom);
        return ResponseEntity.status(HttpStatus.CREATED).body(StorageRoomMapper.toDTO(saveStorageRoom));
    }

}
