package com.dam.commune.owner;

import com.dam.commune.bankAccount.BankAccount;

public class OwnerMapper {

    // Convertir Owner a OwnerDTO (de entidad a DTO)
    public static OwnerDTO toDTO(Owner owner) {
        if (owner == null) {
            return null;
        }

        OwnerDTO ownerDTO = new OwnerDTO();

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
}
