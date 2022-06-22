package com.example.realworld.controller;


import com.auth0.jwt.interfaces.Claim;
import com.example.realworld.dto.*;
import com.example.realworld.exception.ApiRequestException;
import com.example.realworld.model.User;
import com.example.realworld.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> ping(){
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("/user")
    public ResponseEntity<UserResDto> currentUser(@RequestHeader Map<String, String> header, @AuthenticationPrincipal User user){
        System.out.println(header);
        UserResDto userResDto = new UserResDto(
                new UserDto(
                        user,
                        header.get("authorization").replace("Bearer ", "")
                )
        );
        return ResponseEntity.ok().body(userResDto);
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserResDto> login(@RequestBody UserLoginDtoWrapper dto){
        try{
            UserDto userDto = userService.login(dto.getUser().getEmail(), dto.getUser().getPassword());
            return ResponseEntity.ok().body(new UserResDto(userDto));
        } catch (AuthenticationException e ){
            throw new ApiRequestException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<UserResDto> register(@RequestBody UserRegisterDtoWrapper dto){
        try{
            UserResDto userResDto = userService.register(dto.getUser().getUsername(), dto.getUser().getEmail(), dto.getUser().getPassword());
            return ResponseEntity.ok().body(userResDto);
        } catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PutMapping("/user")
    public ResponseEntity<?> update(@RequestBody UserUpdateDtoWrapper dto, @AuthenticationPrincipal User user) {
        UserResDto updatedUser = userService.update(user.getId(), dto.getUser());
        System.out.println(user.getEmail());
        return ResponseEntity.ok().body(updatedUser);
    }
}
