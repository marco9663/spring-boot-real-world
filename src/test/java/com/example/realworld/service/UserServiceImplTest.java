package com.example.realworld.service;

import com.example.realworld.model.User;
import com.example.realworld.repository.ProfileRepository;
import com.example.realworld.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @MockBean
    private UserServiceImpl underTest;
    @Mock
    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    String plainTextPassword = "password";
    String email = "email1@email.com";


    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository, profileRepository, authenticationManager, bCryptPasswordEncoder);
    }


    @Test
    @Disabled
    void loadUserByUsername() {
    }

    @Test
    @Disabled
    void findUserByID() {
//        UUID mockID = UUID.fromString("9ca775a1-d2da-46b5-9a5a-99ad62829438");
//
//        underTest.findUserByID(mockID);
//
//        verify(userRepository).findById(mockID);
    }

    @Test
    @Disabled
    void login() {
    }

    @Test
    @Disabled
    void register_success() {

    }

    @Test
    @Disabled
    void update() {
    }
}