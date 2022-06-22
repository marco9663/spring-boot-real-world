package com.example.realworld;

import com.example.realworld.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
@DataJpaTest
class RealworldApplicationTests {

//    @Autowired
//    private UserController userController;

    @Test
    void contextLoads() throws Exception {
//        assertThat(userController).isNotNull();
    }

}
