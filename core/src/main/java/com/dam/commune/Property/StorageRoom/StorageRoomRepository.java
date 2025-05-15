package com.dam.commune.property.storageRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRoomRepository extends JpaRepository<StorageRoom, Long> {
  
}
