package com.dam.commune.community;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.dam.commune.bankAccount.BankAccount;

/**
 * Utility class for mapping between Community entities and CommunityDTO
 * objects.
 * <p>
 * Provides static methods to:
 * <ul>
 * <li>Convert a Community entity to a CommunityDTO (basic and detailed
 * views).</li>
 * <li>Convert a list of Community entities to a list of CommunityDTOs.</li>
 * <li>Transform a CommunityDTO into a Community entity.</li>
 * <li>Update an existing Community entity with values from a CommunityDTO.</li>
 * </ul>
 * <p>
 * Handles null checks and manages the association with BankAccount entities
 * where applicable.
 */
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
                .bankAccount(bankAccount) // Asocia el objeto BankAccount, si está presente
                .build();
    }

    /**
     * UpdateEntityDTO
     * 
     */
    public static void updateEntityFromDTO(CommunityDTO dto, Community entity) {
        entity.setAddress(dto.getAddress());
        entity.setPostalCode(dto.getPostalCode());
        entity.setElevator(dto.isElevator());
        entity.setNumFlats(dto.getNumFlats());
        entity.setNumparkings(dto.getNumparkings());
        entity.setNumStorageRooms(dto.getNumStorageRooms());
        entity.setReducedMobilityAccess(dto.isReducedMobilityAccess());

        if (dto.getBankAccountNumber() != null && !dto.getBankAccountNumber().isEmpty()) {
            BankAccount bankAccount = entity.getBankAccount();
            if (bankAccount != null) {
                bankAccount.setAccountNumber(dto.getBankAccountNumber());
            } else {
                bankAccount = new BankAccount();
                bankAccount.setAccountNumber(dto.getBankAccountNumber());
                bankAccount.setBalance(BigDecimal.ZERO);
                bankAccount.setBankName("Default Name");
                entity.setBankAccount(bankAccount);
            }

        } else {
            entity.setBankAccount(null);

        }
    }

}
