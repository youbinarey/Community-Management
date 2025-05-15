package com.dam.commune.property;

import com.dam.commune.property.floor.Floor;
import com.dam.commune.property.floor.FloorRepository;
import com.dam.commune.property.parking.ParkingRepository;
import com.dam.commune.property.parking.Parking;
import com.dam.commune.property.storageRoom.StorageRoom;
import com.dam.commune.property.storageRoom.StorageRoomRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;
    private final FloorRepository floorRepository;
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
    // CRUD para FLOOR (Apartment)
    // -----------------------------

    @PostMapping("/floor")
    public ResponseEntity<Floor> createFloor(@RequestBody Floor floor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(floorRepository.save(floor));
    }

    @GetMapping("/floor/{id}")
    public ResponseEntity<Floor> getFloor(@PathVariable Long id) {
        return floorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/floor/{id}")
    public ResponseEntity<Floor> updateFloor(@PathVariable Long id, @RequestBody Floor updatedFloor) {
        return floorRepository.findById(id)
                .map(existing -> {
                    updatedFloor.setId(id);
                    return ResponseEntity.ok(floorRepository.save(updatedFloor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/floor/{id}")
    public ResponseEntity<Void> deleteFloor(@PathVariable Long id) {
        if (floorRepository.existsById(id)) {
            floorRepository.deleteById(id);
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
