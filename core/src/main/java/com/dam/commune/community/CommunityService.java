package com.dam.commune.community;

import java.util.List;
import java.util.Optional;

public interface CommunityService {
    List<Community> findAll();

    Optional<Community> findById(Long id);

    Community save(Community community);

    boolean deleteIfExists(Long id);

    List<CommunityDTO> findAllDTOs();

    Community saveCommunityFromDTO(CommunityDTO communityDTO);

    CommunityDTO updaCommunityDTO(Long id, CommunityDTO communityDTO);

}
