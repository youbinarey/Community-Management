package com.dam.commune.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.dam.commune.entity.Community;
import com.dam.commune.repository.CommunityRepository;

@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    public CommunityServiceImpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    @Override
    public Optional<Community> findById(Long id) {
        return communityRepository.findById(id);
    }

    @Override
    public Community save(Community community) {
       return communityRepository.save(community);
    }

    @Override
    public void deleteById(Long id) {
       communityRepository.deleteById(id);
    }
    
}
