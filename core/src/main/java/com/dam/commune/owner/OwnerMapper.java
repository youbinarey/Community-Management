package com.dam.commune.owner;

import java.math.BigDecimal;

import com.dam.commune.bankAccount.BankAccount;

public class OwnerMapper {
    // Convertir Owner a OwnerDTO (de entidad a DTO)
    public static OwnerDTO toDTO(Owner owner) {
        if (owner == null) {
            return null;
        }

        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setId(owner.getId());
        ownerDTO.setDni(owner.getDni());
        ownerDTO.setName(owner.getName());
        ownerDTO.setSurname(owner.getSurname());
        ownerDTO.setEmail(owner.getEmail());
        ownerDTO.setPhone(owner.getPhone());
        ownerDTO.setBirthDate(owner.getBirthDate());
        BankAccount bankAccount = owner.getBankAccount();
        if (bankAccount != null) {
            ownerDTO.setBankAccountNumber(bankAccount.getAccountNumber());
        }
        ownerDTO.setPropertiesCount(owner.getProperties().size());
        return ownerDTO;
           
          
    }

    public static void updateEntityFromDTO(OwnerDTO dto, Owner entity){
        entity.setDni(dto.getDni());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setBirthDate(dto.getBirthDate());

        if(dto.getBankAccountNumber() != null && !dto.getBankAccountNumber().isEmpty()){
            BankAccount bankAccount = entity.getBankAccount();
            if(bankAccount != null){
                bankAccount.setAccountNumber(dto.getBankAccountNumber());
            } else {
                bankAccount = new BankAccount();
                bankAccount.setAccountNumber(dto.getBankAccountNumber());
                bankAccount.setBalance(BigDecimal.ZERO);
                bankAccount.setBankName("Default Bank");
                entity.setBankAccount(bankAccount);
            }
            }else {
                entity.setBankAccount(null);
            }
        }
    }

