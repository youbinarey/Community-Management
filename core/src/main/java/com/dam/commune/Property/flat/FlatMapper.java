package com.dam.commune.property.flat;

import com.dam.commune.community.Community;
import com.dam.commune.owner.Owner;

public class FlatMapper {

    public static FlatDTO toDTO(Flat flat) {
        if (flat == null) {
            return null;
        }
        FlatDTO flatDTO = new FlatDTO();
        flatDTO.setId(flat.getId());
        flatDTO.setCadastralReference(flat.getCadastralReference());
        flatDTO.setSquareMeters(flat.getSquareMeters());
        flatDTO.setFloorNumber(flat.getFloorNumber());
        flatDTO.setLetter(flat.getLetter());
        flatDTO.setRoomCount(flat.getRoomCount());
        flatDTO.setBathroomCount(flat.getBathroomCount());
        
        Community community = flat.getCommunity();
        if(community != null) {
            flatDTO.setCommunityName(community.getAddress());
        }

        Owner owner = flat.getOwner();
        if(owner != null) {
            flatDTO.setOwnerName(owner.getName());
        }
        return flatDTO;
    }

}
