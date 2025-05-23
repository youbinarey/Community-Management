package com.dam.commune.community;

import java.util.List;
import java.util.stream.Collectors;

import com.dam.commune.bankAccount.BankAccount;

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
            .numFlats(community.getNumFlats())
            .numparkings(community.getNumparkings())
            .numStorageRooms(community.getNumStorageRooms())
            .reducedMobilityAccess(community.isReducedMobilityAccess())
            .bankAccountNumber(
                    community.getBankAccount() != null
                            ? community.getBankAccount().getAccountNumber()
                            : null)
            .build();
}


// Método para transformar el DTO en una entidad Community
    public static Community transformDTOToEntity(CommunityDTO communityDTO) {
        // Crear un nuevo objeto BankAccount si se proporciona el número de cuenta
        BankAccount bankAccount = null;
        if (communityDTO.getBankAccountNumber() != null && !communityDTO.getBankAccountNumber().isEmpty()) {
            bankAccount = new BankAccount();
            bankAccount.setAccountNumber(communityDTO.getBankAccountNumber());
        }

        // Crear y devolver la entidad Community usando los datos del DTO
        return Community.builder()
                .address(communityDTO.getAddress())
                .postalCode(communityDTO.getPostalCode())
                .elevator(communityDTO.isElevator())
                .numFlats(communityDTO.getNumFlats())
                .numparkings(communityDTO.getNumparkings())
                .numStorageRooms(communityDTO.getNumStorageRooms())
                .reducedMobilityAccess(communityDTO.isReducedMobilityAccess())
                .bankAccount(bankAccount)  // Asocia el objeto BankAccount, si está presente
                .build();
    }
}
