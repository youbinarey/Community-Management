package com.dam.commune.community;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean deleteIfExists(Long id) {
        if(communityRepository.existsById(id)) {
            communityRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
