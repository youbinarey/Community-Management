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

/**
 * Controller for managing properties, including flats (apartments), parkings, and storage rooms.
 * Provides CRUD operations and endpoints for retrieving and manipulating property-related data.
 *
 * <p>Main responsibilities:</p>
 * <ul>
 *   <li>CRUD operations for Flat, Parking, and StorageRoom entities.</li>
 *   <li>Endpoints for retrieving properties by community.</li>
 *   <li>DTO mapping for API responses.</li>
 *   <li>Validation to prevent duplicate entries within a community.</li>
 *   <li>Handles associations with Community and Owner entities.</li>
 * </ul>
 *
 *
 * <p>Injected dependencies:</p>
 * <ul>
 *   <li>{@link PropertyService}</li>
 *   <li>{@link FlatRepository}</li>
 *   <li>{@link CommunityRepository}</li>
 *   <li>{@link ParkingRepository}</li>
 *   <li>{@link StorageRoomRepository}</li>
 *   <li>{@link PropertyServiceImpl}</li>
 *   <li>{@link OwnerRepository}</li>
 * </ul>
 *
 * <p>Example endpoints:</p>
 * <ul>
 *   <li>GET /properties - List all properties</li>
 *   <li>POST /properties/flat - Create a new flat</li>
 *   <li>GET /properties/flat/community/{communityId} - List flats by community</li>
 *   <li>POST /properties/create-flat - Create a flat from DTO</li>
 *   <li>POST /properties/create-parking - Create a parking from DTO</li>
 *   <li>POST /properties/create-storage-room - Create a storage room from DTO</li>
 *   <li>GET /properties/parking/community/{communityId} - List parkings by community</li>
 *   <li>GET /properties/storageroom/community/{communityId} - List storage rooms by community</li>
 * </ul>
 *
 * <p>All endpoints return appropriate HTTP status codes for success and error cases.</p>

 */
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
            if (flatRepository.existsByLetterAndFloorNumberAndCommunity(
                    flatDTO.getLetter(),
                    flatDTO.getFloorNumber(),
                    community)) {
                throw new IllegalArgumentException("Ya existe un piso con esa letra y planta en esta comunidad.");
            }

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
                parking.getCoefficient(),
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
                storageRoom.getCoefficient(),
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
                flat.getCoefficient(),
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

    @GetMapping("/parking")
    public ResponseEntity<List<ParkingDTO>> getAllParkings() {
        List<ParkingDTO> parkings = parkingRepository.findAll().stream()
                .map(ParkingMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(parkings);
    }

    @GetMapping("/flat")
    public ResponseEntity<List<FlatDTO>> getAllFlats() {
        List<FlatDTO> flats = flatRepository.findAll().stream()
                .map(FlatMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(flats);
    }

    @GetMapping("/storage-room")
    public ResponseEntity<List<StorageRoomDTO>> getAllStoragesRooms() {
        List<StorageRoomDTO> storagesRooms = storageRoomRepository.findAll().stream()
                .map(StorageRoomMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(storagesRooms);
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

    @PutMapping("/flat/{id}")
    public ResponseEntity<?> updateFlat(@PathVariable Long id, @RequestBody FlatDTO flatDTO) {
        flatDTO.setId(id);
        try {
            Flat updatedFlat = propertyServiceImpl.updateFlat(flatDTO);
            return ResponseEntity.ok(FlatMapper.toDTO(updatedFlat));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create-flat")
    public ResponseEntity<FlatDTO> createFlatDTO(@RequestBody FlatDTO flatDTO) {
        // 1. Busca la comunidad por nombre (o id)
        Community community = communityRepository.findByAddress(flatDTO.getCommunityName());
        if (community == null) {
            return ResponseEntity.badRequest().build();
        }

        // 2. Valida que no exista ya un piso igual
        boolean exists = flatRepository.existsByLetterAndFloorNumberAndCommunity(
                flatDTO.getLetter(),
                flatDTO.getFloorNumber(),
                community);
        if (exists) {
            throw new IllegalArgumentException("Ya existe un piso con esa letra y planta en esta comunidad.");
        }

        // 3. Mapear DTO a entidad
        Flat flat = new Flat();
        flat.setCadastralReference(flatDTO.getCadastralReference());
        flat.setSquareMeters(flatDTO.getSquareMeters());
        flat.setFloorNumber(flatDTO.getFloorNumber());
        flat.setLetter(flatDTO.getLetter());
        flat.setRoomCount(flatDTO.getRoomCount());
        flat.setBathroomCount(flatDTO.getBathroomCount());
        flat.setCoefficient(flatDTO.getCoefficient());
        flat.setCommunity(community);

        // 4. Si el owner es opcional:
        if (flatDTO.getOwnerDni() != null && !flatDTO.getOwnerDni().trim().isEmpty()) {
            Owner owner = ownerRepository.findByDni(flatDTO.getOwnerDni())
                    .orElseThrow(
                            () -> new IllegalArgumentException("Owner not found with DNI: " + flatDTO.getOwnerDni()));
            flat.setOwner(owner);
        }

        // 5. Guarda y devuelve DTO
        Flat savedFlat = flatRepository.save(flat);
        return ResponseEntity.status(HttpStatus.CREATED).body(FlatMapper.toDTO(savedFlat));
    }

    @PostMapping("/create-parking")
    public ResponseEntity<ParkingDTO> createParkingDTO(@RequestBody ParkingDTO parkingDTO) {
        Parking parking = propertyServiceImpl.createParking(parkingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ParkingMapper.toDTO(parking));
    }

    @PostMapping("/create-storage-room")
    public ResponseEntity<StorageRoomDTO> createStorageRoomDTO(@RequestBody StorageRoomDTO storageRoomDTO) {
        StorageRoom storageRoom = propertyServiceImpl.createStorageRoom(storageRoomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(StorageRoomMapper.toDTO(storageRoom));
    }

}
