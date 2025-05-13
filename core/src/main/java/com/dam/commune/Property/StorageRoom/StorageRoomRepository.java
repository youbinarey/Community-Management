package com.dam.commune.property.StorageRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRoomRepository extends JpaRepository<StorageRoom, Long> {
  
}
