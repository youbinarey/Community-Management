package com.dam.commune.property.parking;

import com.dam.commune.community.Community;
import com.dam.commune.owner.Owner;

public class ParkingMapper {
    public static ParkingDTO toDTO(Parking parking) {
        if (parking == null) {
            return null;
        }
        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setId(parking.getId());
        parkingDTO.setCadastralReference(parking.getCadastralReference());
        parkingDTO.setSquareMeters(parking.getSquareMeters());
        parkingDTO.setCoefficient(parking.getCoefficient());
        parkingDTO.setNum(parking.getNum());
        
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
