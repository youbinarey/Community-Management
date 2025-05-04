package com.dam.commune.service;

import java.util.List;
import java.util.Optional;

import com.dam.commune.entity.Community;

public interface CommunityService {
    List<Community> findAll();

    Optional<Community> findById(Long id);

    Community save(Community community);

    boolean deleteIfExists(Long id);

    void deleteById(Long id);

}
