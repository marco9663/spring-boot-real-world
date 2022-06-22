package com.example.realworld.repository;

import com.example.realworld.model.Profile;
import com.example.realworld.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUsername(String username);

    @Query("select case when p is null then 'False' else 'True' end as isFollowing from Profile p where p.id = ?1 and p.followers = ?2")
    Boolean isFollowingThisProfile(UUID profileId, UUID followedBy);
}
