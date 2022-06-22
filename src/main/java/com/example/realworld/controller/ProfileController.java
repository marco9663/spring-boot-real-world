package com.example.realworld.controller;

import com.example.realworld.dto.ProfileDto;
import com.example.realworld.model.Profile;
import com.example.realworld.model.User;
import com.example.realworld.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profiles/{username}")
    public Map<String, ProfileDto> findProfileByUsername(@PathVariable("username") String username, @AuthenticationPrincipal User user){
        ProfileDto profileDto = this.profileService.findProfileByUsername(username, user);
        Map<String, ProfileDto> response = new HashMap<String, ProfileDto>();
        response.put("profile", profileDto);
        return response;
    }

    @PostMapping("/profiles/{username}/follow")
    public Map<String, ProfileDto> followProfileByUsername(@PathVariable("username") String username, @AuthenticationPrincipal User user){
        ProfileDto profileDto = this.profileService.followProfileByUsername(username, user);
        Map<String, ProfileDto> response = new HashMap<String, ProfileDto>();
        response.put("profile", profileDto);
        return response;
    }
}
