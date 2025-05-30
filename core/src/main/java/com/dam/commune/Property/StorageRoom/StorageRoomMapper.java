package com.dam.commune.property.storageRoom;

import com.dam.commune.community.Community;
import com.dam.commune.owner.Owner;

public class StorageRoomMapper {

    public static StorageRoomDTO toDTO(StorageRoom storageRoom) {
        if (storageRoom == null) {
            return null;
        }
        StorageRoomDTO storageRoomDTO = new StorageRoomDTO();
        storageRoomDTO.setId(storageRoom.getId());
        storageRoomDTO.setCadastralReference(storageRoom.getCadastralReference());
        storageRoomDTO.setSquareMeters(storageRoom.getSquareMeters());
        storageRoomDTO.setCoefficient(storageRoom.getCoefficient());
        storageRoomDTO.setStorageNumber(storageRoom.getStorageNumber());
        
         Community community = storageRoom.getCommunity();
        if(community != null) {
            storageRoomDTO.setCommunityName(community.getAddress());
        }

        Owner owner = storageRoom.getOwner();
        if(owner != null) {
            storageRoomDTO.setOwnerName(owner.getName());
        }
        
        return storageRoomDTO;
    }

}
