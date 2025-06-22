package com.dam.commune.owner;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsByDni(String dni);

    Optional<Owner> findByDni(String dni);

}
