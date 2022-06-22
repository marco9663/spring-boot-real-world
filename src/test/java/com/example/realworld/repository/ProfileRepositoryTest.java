package com.example.realworld.repository;

import com.example.realworld.model.Profile;
import com.example.realworld.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
public class ProfileRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    User followerUser = new User("followerUser@email.com", "password");
    User userToBeFollowed = new User("userToBeFollower@email.com", "password");
    User otherUser = new User("other@email.com", "password");
    Profile followerProfile = Profile.builder()
            .user(followerUser)
            .username("follower")
            .bio("no bio")
            .image("no image").build();
    Profile profileToBeFollowed = Profile.builder()
            .user(followerUser)
            .username("to be follower")
            .bio("no bio")
            .image("no image").build();
    Profile otherProfile = Profile.builder()
            .user(otherUser)
            .username("other username")
            .bio("other bio")
            .image("no image").build();

    @BeforeEach
    public void setUp(){
        userRepository.save(followerUser);
        userRepository.save(userToBeFollowed);
        userRepository.save(otherUser);
        profileRepository.save(followerProfile);
        profileRepository.save(profileToBeFollowed);
        profileRepository.save(otherProfile);

        Set<Profile> profiles = followerProfile.getFollowings();
        if(profiles == null){
            profiles = new HashSet<>();
        }
        profiles.add(profileToBeFollowed);
        followerProfile.setFollowings(profiles);
    }

    @Test
    public void testIsFollowingThisProfile(){
        Profile profile = profileRepository.findByUsername(followerProfile.getUsername());
        System.out.println(profile.getFollowings());
        profile = profileRepository.findByUsername(profileToBeFollowed.getUsername());
        System.out.println(profile.getFollowings());
    }

    @Disabled
    @Test
    public void testIsNotFollowingThisProfile(){

    }
}
