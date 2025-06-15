package com.dam.commune.property.flat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.commune.community.Community;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {

    List<Flat> findByCommunity(Community community);

    boolean existsByCadastralReference(String cadastralReference);

    boolean existsByLetterAndFloorNumberAndCommunityAndIdNot(String letter, Integer floorNumber, Community community, Long id);
    boolean existsByLetterAndFloorNumberAndCommunity(String letter, Integer floorNumber, Community community);

    


    
    
}
