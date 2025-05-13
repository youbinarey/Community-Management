package com.dam.commune.property;

import com.dam.commune.property.Garage.Garage;
import com.dam.commune.property.Garage.GarageRepository;
import com.dam.commune.property.StorageRoom.StorageRoom;
import com.dam.commune.property.StorageRoom.StorageRoomRepository;
import com.dam.commune.property.floor.Floor;
import com.dam.commune.property.floor.FloorRepository;
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
    private final GarageRepository garageRepository;
    private final StorageRoomRepository storageRoomRepository;

    // 🔹 Obtener todos los inmuebles
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
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
    // CRUD para GARAGE
    // -----------------------------

    @PostMapping("/garage")
    public ResponseEntity<Garage> createGarage(@RequestBody Garage garage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(garageRepository.save(garage));
    }

    @GetMapping("/garage/{id}")
    public ResponseEntity<Garage> getGarage(@PathVariable Long id) {
        return garageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/garage/{id}")
    public ResponseEntity<Garage> updateGarage(@PathVariable Long id, @RequestBody Garage updatedGarage) {
        return garageRepository.findById(id)
                .map(existing -> {
                    updatedGarage.setId(id);
                    return ResponseEntity.ok(garageRepository.save(updatedGarage));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/garage/{id}")
    public ResponseEntity<Void> deleteGarage(@PathVariable Long id) {
        if (garageRepository.existsById(id)) {
            garageRepository.deleteById(id);
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
