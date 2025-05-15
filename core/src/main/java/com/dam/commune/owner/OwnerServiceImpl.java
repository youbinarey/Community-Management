package com.dam.commune.owner;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;


    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Optional<Owner> getById(Long id) {
       return ownerRepository.findById(id);
    }

    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner update(Long id, Owner owner) {
        return ownerRepository.findById(id)
                .map(existingOwner -> {
                    existingOwner.setName(owner.getName());
                    existingOwner.setDni(owner.getDni());
                    existingOwner.setPhone(owner.getPhone());
                    existingOwner.setEmail(owner.getEmail());
                    return ownerRepository.save(existingOwner);
                })
                .orElseThrow(() -> new EntityNotFoundException("Owner with " + id  + "not found"));
    }

    @Override
    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public boolean existsByDni(String dni) {
        return ownerRepository.existsByDni(dni);
    }

    @Override
public boolean deleteIfExists(Long id) {
    if (ownerRepository.existsById(id)) {
        ownerRepository.deleteById(id);
        return true;
    }
    return false;
}


    

}
