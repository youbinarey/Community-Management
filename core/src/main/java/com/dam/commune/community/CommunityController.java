package com.dam.commune.community;

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

import jakarta.validation.Valid;

/**
 * REST controller for managing Community entities.
 * <p>
 * Provides endpoints for CRUD operations on communities, including
 * creation, retrieval, update, and deletion. Supports both entity and DTO-based
 * operations, allowing for flexible data transfer and validation.
 * </p>
 * <ul>
 * <li>GET /communities - Retrieve all communities.</li>
 * <li>GET /communities/{id} - Retrieve a community by its ID.</li>
 * <li>POST /communities - Create a new community from an entity.</li>
 * <li>POST /communities/dto - Create a new community from a DTO.</li>
 * <li>PUT /communities/{id} - Update an existing community by its ID.</li>
 * <li>DELETE /communities/{id} - Delete a community by its ID.</li>
 * <li>GET /communities/dto - Retrieve all communities as DTOs.</li>
 * <li>GET /communities/dto/{id}/detail - Retrieve detailed DTO for a community
 * by its ID.</li>
 * <li>PUT /communities/dto/{id} - Update a community using a DTO.</li>
 * </ul>
 */
@RestController
@RequestMapping("/communities")
@CrossOrigin(origins = "http://localhost:4200")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping
    public ResponseEntity<List<Community>> getAll() {
        return ResponseEntity.ok(communityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> getById(@PathVariable Long id) {
        return communityService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Community> create(@RequestBody Community community) {
        return ResponseEntity.ok(communityService.save(community));
    }

    @PostMapping("/dto")
    public ResponseEntity<?> createFromDTO(@RequestBody @Valid CommunityDTO communityDTO) {

        Community community = communityService.saveCommunityFromDTO(communityDTO);
        return ResponseEntity.ok(community);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Community> update(@PathVariable Long id, @RequestBody Community updated) {
        return communityService.findById(id)
                .map(existing -> {
                    updated.setId(existing.getId());
                    return ResponseEntity.ok(communityService.save(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!communityService.deleteIfExists(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dto")
    public List<CommunityDTO> getAllCommunityDTOs() {
        return communityService.findAllDTOs();
    }

    @GetMapping("/dto/{id}/detail")
    public ResponseEntity<CommunityDTO> getDetailedDTO(@PathVariable Long id) {
        return communityService.findById(id)
                .map(CommunityMapper::toDetailedDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Endopint to update a community using a DTO.
     * 
     */
    @PutMapping("/dto/{id}")
    public ResponseEntity<CommunityDTO> updateFromDTO(@PathVariable Long id,
            @RequestBody @Valid CommunityDTO communityDTO) {
        CommunityDTO updatedDTO = communityService.updaCommunityDTO(id, communityDTO);
        return ResponseEntity.ok(updatedDTO);

    }

}
