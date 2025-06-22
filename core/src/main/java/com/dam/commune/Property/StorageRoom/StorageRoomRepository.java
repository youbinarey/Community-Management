package com.dam.commune.property.storageRoom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.commune.community.Community;

@Repository
public interface StorageRoomRepository extends JpaRepository<StorageRoom, Long> {
    List<StorageRoom> findByCommunity(Community community);

    boolean existsByStorageNumberAndCommunity(Integer storageNumber, Community community);

}
