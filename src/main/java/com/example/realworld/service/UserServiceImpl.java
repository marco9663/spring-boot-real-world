package com.example.realworld.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.realworld.converter.UserConverter;
import com.example.realworld.dto.UserDto;
import com.example.realworld.dto.UserResDto;
import com.example.realworld.dto.UserUpdateDto;
import com.example.realworld.exception.ApiRequestException;
import com.example.realworld.model.Profile;
import com.example.realworld.model.User;
import com.example.realworld.repository.ProfileRepository;
import com.example.realworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", email);
        }
        return user;
    }

    public User findUserByID(UUID id){
        User user = userRepository.findById(id);
        if(user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        return user;
    }


    @Override
    public UserDto login(String email, String password) {
        try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e){
            throw new ApiRequestException(e.getMessage());
        }
        User user = userRepository.findByEmail(email);
        String token = signAccessToken(user);
        UserDto userDto = new UserDto(
                user.getEmail(),
                token,
                user.getProfile().getUsername(),
                user.getProfile().getBio(),
                user.getProfile().getImage()
        );
        return userDto;
    }

    @Override
    public UserResDto register(String username, String email, String password) {
        try {
            User user = new User(email, bCryptPasswordEncoder.encode(password));
            user = userRepository.save(user);

//            Profile profile = new Profile(username, null, null, user);
            Profile profile = Profile.builder().username(username).user(user).build();
            profileRepository.save(profile);

            String token = signAccessToken(user);
            UserDto dto = new UserDto(
                    user.getEmail(),
                    token,
                    profile.getUsername(),
                    profile.getBio(),
                    profile.getImage()
            );
            return new UserResDto(dto);
        } catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public UserResDto update(UUID userID, UserUpdateDto dto){
        User user = userRepository.findById(userID);
        if(dto.getEmail() != null){
            user.setEmail(dto.getEmail());
        }
        if(dto.getBio() != null){
            user.getProfile().setBio(dto.getBio());
        }
        if(dto.getImage() != null){
            user.getProfile().setImage(dto.getImage());
        }
        if(dto.getPassword() != null){
            user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        }
        if(dto.getUsername() != null){
            user.getProfile().setUsername(dto.getUsername());
        }
        user = userRepository.save(user);
        String token = signAccessToken(user);
        return new UserResDto(new UserDto(user, token));
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    public String signAccessToken(User user){
        log.info(user.getEmail());
        log.info(user.getId().toString());
        String token = JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *1000)) // 60 minutes
                .withClaim("email", user.getEmail())
                .withClaim("uid", user.getId().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuer("real-world-spring-boot")
                .sign(algorithm);
        return token;
    }

}
