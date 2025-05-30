package com.dam.commune.property;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    boolean existsByCadastralReference(String cadastralReference);
    boolean existsByOwnerId(Long ownerId);
    List<Property> findByOwnerIdAndCommunityId(Long id, Long id2);
    List<Property> findByCommunity_Id(Long communityId);


}
