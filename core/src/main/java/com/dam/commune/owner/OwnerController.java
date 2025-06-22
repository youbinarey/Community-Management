package com.dam.commune.owner;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dam.commune.property.Property;

import lombok.RequiredArgsConstructor;

/**
 * REST controller for managing Owner entities.
 * <p>
 * Provides endpoints for CRUD operations on owners, as well as retrieving owner
 * DTOs and properties.
 * </p>
 * <ul>
 * <li>GET /owner - Retrieve all owners.</li>
 * <li>GET /owner/dto - Retrieve all owners as DTOs.</li>
 * <li>GET /owner/dto/{id} - Retrieve a specific owner as DTO by ID.</li>
 * <li>GET /owner/{id} - Retrieve a specific owner by ID.</li>
 * <li>POST /owner - Create a new owner. Returns 409 if DNI already exists.</li>
 * <li>PUT /owner/{id} - Update an existing owner by ID.</li>
 * <li>PUT /owner/dto/{id} - Update an existing owner DTO by ID.</li>
 * <li>DELETE /owner/{id} - Delete an owner by ID.</li>
 * <li>GET /owner/{ownerId}/properties - Retrieve properties associated with a
 * specific owner.</li>
 * </ul>
 */
@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping
    public List<Owner> getAll() {
        return ownerService.getAll();
    }

    @GetMapping("/dto")
    public List<OwnerDTO> getAllDTOs() {
        return ownerService.getAllDTOs();
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getById(@PathVariable Long id) {
        return ownerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Owner> create(@RequestBody Owner owner) {
        if (ownerService.existsByDni(owner.getDni())) {
            return ResponseEntity.status(409).build(); // Conflict
        }
        return ResponseEntity.status(201).body(ownerService.create(owner));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> update(@PathVariable Long id, @RequestBody Owner owner) {
        if (!ownerService.getById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ownerService.update(id, owner));
    }

    @PutMapping("/dto/{id}")
    public ResponseEntity<OwnerDTO> updateOwnerDTO(@PathVariable Long id, @RequestBody OwnerDTO ownerDTO) {
        return ResponseEntity.ok(ownerService.updateOwnerDTO(id, ownerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ownerService.deleteIfExists(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{ownerId}/properties")
    public List<Property> getPropertiesByOwnerId(@PathVariable Long ownerId) {
        return ownerService.getPropertiesByOwnerId(ownerId);
    }

}
