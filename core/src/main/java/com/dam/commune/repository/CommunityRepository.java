package com.dam.commune.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dam.commune.entity.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
   

}
