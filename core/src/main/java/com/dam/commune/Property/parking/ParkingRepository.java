package com.dam.commune.property.parking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.commune.community.Community;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
        List<Parking> findByCommunity(Community community);

        boolean existsByNumAndCommunity(Integer num, Community community);

}
