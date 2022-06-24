package com.example.realworld.service;

import com.example.realworld.dto.ProfileDto;
import com.example.realworld.model.Profile;
import com.example.realworld.model.User;
import com.example.realworld.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ProfileService {
    private ProfileRepository profileRepository;

    public ProfileDto findProfileByUsername(String username){
        Profile profile = this.profileRepository.findByUsername(username);
        Boolean isFollowingThisProfile = false;
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUsername(profile.getUsername());
        profileDto.setBio(profile.getBio());
        profileDto.setImage(profile.getImage());
        profileDto.setFollowing(isFollowingThisProfile);
        return profileDto;
    }

    public ProfileDto findProfileByUsername(String username, User currentUser){
        Profile profile = this.profileRepository.findByUsername(username);
        System.out.println(profile);
        Boolean isFollowingThisProfile = false;
        if(currentUser != null) {
            isFollowingThisProfile = this.profileRepository.isFollowingThisProfile(profile.getId(), currentUser.getProfile().getId());
        }
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUsername(profile.getUsername());
        profileDto.setBio(profile.getBio());
        profileDto.setImage(profile.getImage());
        profileDto.setFollowing(isFollowingThisProfile);
        return profileDto;
    }

    public ProfileDto followProfileByUsername(String username, User currentUser){
        Profile profile = this.profileRepository.findByUsername(username);
        profile.getFollowings().add(currentUser.getProfile());
        this.profileRepository.save(profile);
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUsername(profile.getUsername());
        profileDto.setBio(profile.getBio());
        profileDto.setImage(profile.getImage());
        profileDto.setFollowing(true);
        return profileDto;
    }

//    public ProfileDto
}
