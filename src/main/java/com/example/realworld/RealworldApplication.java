package com.example.realworld;

import com.example.realworld.model.Profile;
import com.example.realworld.model.User;
import com.example.realworld.repository.ProfileRepository;
import com.example.realworld.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Realworld API", version = "1.0", description = "API Description"))
public class RealworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealworldApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ProfileRepository profileRepository){
        return args -> {
            String email1 = "email1@email.com";
            String password1 = "password1";
            String hashedPassword1 = bCryptPasswordEncoder().encode(password1);
            User user1 = new User(email1, hashedPassword1);
            userRepository.save(user1);

            String username1 = "username1";
//            Profile profile = new Profile(username1, null, null, user1);
            Profile profile = Profile.builder().user(user1).username(username1).build();
            profileRepository.save(profile);

            String email2 = "email2@email.com";
            String password2 = "password2";
            String hashedPassword2 = bCryptPasswordEncoder().encode(password2);
            User user2 = new User(email2, hashedPassword2);
            userRepository.save(user2);
        };
    }
}
