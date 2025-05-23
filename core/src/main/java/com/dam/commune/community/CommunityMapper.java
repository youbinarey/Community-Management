package com.dam.commune.community;

import java.util.List;
import java.util.stream.Collectors;

public class CommunityMapper {

    public static CommunityDTO toDTO(Community community) {
        return CommunityDTO.builder()
                .id(community.getId())
                .address(community.getAddress())
                .postalCode(community.getPostalCode())
                .propertiesCount(
                        community.getProperties() != null ? community.getProperties().size() : 0)
                .build();
    }

    public static List<CommunityDTO> toDTOList(List<Community> communities) {
        return communities.stream()
                .map(CommunityMapper::toDTO)
                .collect(Collectors.toList());
    }


    public static CommunityDTO toDetailedDTO(Community community) {
    return CommunityDTO.builder()
            .id(community.getId())
            .address(community.getAddress())
            .postalCode(community.getPostalCode())
            .propertiesCount(
                    community.getProperties() != null ? community.getProperties().size() : 0)
            .elevator(community.isElevator())
            .numFloors(community.getNumFloors())
            .numparkings(community.getNumparkings())
            .numStorageRooms(community.getNumStorageRooms())
            .reducedMobilityAccess(community.isReducedMobilityAccess())
            .bankAccountNumber(
                    community.getBankAccount() != null
                            ? community.getBankAccount().getAccountNumber()
                            : null)
            .build();
}

}
