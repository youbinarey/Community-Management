package com.dam.commune.property.parking;

import com.dam.commune.community.Community;
import com.dam.commune.owner.Owner;

public class ParkingMapper {
    public static ParkingDTO toDTO(Parking parking) {
        if (parking == null) {
            return null;
        }
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setNum(parking.getNum());
        parkingDTO.setCommunityName(parking.getCommunity() != null ? parking.getCommunity().getAddress() : null);
        
         Community community = parking.getCommunity();
        if(community != null) {
            parkingDTO.setCommunityName(community.getAddress());
        }

        Owner owner = parking.getOwner();
        if(owner != null) {
            parkingDTO.setOwnerName(owner.getName());
        }
        return parkingDTO;

    }

    
}
