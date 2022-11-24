package com.mydigipay.todo.services;

import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.repositories.UserRepository;
import com.mydigipay.todo.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureDataMongo
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setup() {
        UserDocument user1 = new UserDocument("id", "user", "password");
        userRepository.save(user1);
        UserDocument user2 = new UserDocument("id2", "user2", "password2");
        userRepository.save(user2);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("user", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @Test
    void doesSaveUser() {
        UserDocument user = new UserDocument();
        user.setUsername("rana");
        user.setPassword("1234");
        userService.save(user);

        Optional<UserDocument> savedUser = userRepository.findByUsername("rana");


        assertTrue(savedUser.isPresent());
        assertTrue(passwordEncoder.matches("1234", savedUser.get().getPassword()));

    }

    @Test
    void doesSaveUserWithDuplicateUsername() {
        UserDocument user = new UserDocument();
        user.setUsername("user");
        user.setPassword("1234");

        RuntimeException runtimeException =
                assertThrows(RuntimeException.class, () -> userService.save(user));
        assertTrue(runtimeException.getMessage().contains("user name is not unique"));
    }

    @Test
    void doesSaveUserWithNullPassword() {
        UserDocument user = new UserDocument();
        user.setUsername("rana");

        RuntimeException runtimeException =
                assertThrows(RuntimeException.class, () -> userService.save(user));
        assertTrue(runtimeException.getMessage().contains("password cannot be null or empty"));
    }

    @Test
    void doesSaveUserWithEmptyPassword() {
        UserDocument user = userRepository.findByUsername("user").get();
        user.setUsername("rana");
        user.setPassword("");

        RuntimeException runtimeException =
                assertThrows(RuntimeException.class, () -> userService.save(user));
        assertTrue(runtimeException.getMessage().contains("password cannot be null or empty"));
    }
    @Test
    void doesUpdateUserCorrectly() {
        UserDocument user = new UserDocument();
        user.setUsername("rana");
        user.setPassword("1234");
        user.setId("id");

        userService.update(user);
        assertEquals("rana",userRepository.findById("id").get().getUsername());
        assertTrue(passwordEncoder
                .matches("1234", userRepository.findById("id").get().getPassword()));
    }

    @Test
    void doesUpdateUserWithNullPassword() {
        UserDocument user = new UserDocument();
        user.setUsername("rana");
        user.setId("id");

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.update(user));
        assertTrue(ex.getMessage().contains("password cannot be null or empty"));
    }

    @Test
    void canUpdateAnotherUser() {
        UserDocument user = userRepository.findByUsername("user2").get();
        user.setPassword("1111");

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.update(user));
        assertTrue(ex.getMessage().contains("only user can update username or password"));
    }


    @Test
    void doesFindAllCorrectly() {
        List<UserDocument> userDocuments = userService.find();
        assertIterableEquals(userRepository.findAll(),userDocuments);
    }

    @Test
    void doesFindByIdCorrectly() {
        UserDocument foundUser = userService.findById("id");
        assertNotNull(foundUser);
        assertTrue(foundUser.getUsername().equals("user"));
    }
    @Test
    void doesFindByIncorrectId() {
        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> userService.findById("userid"));
        assertTrue(ex.getMessage().contains("user not found"));
    }

    @Test
    void doesFindByUsernameCorrectly() {
        UserDocument foundUser = userService.findByUsername("user");
        assertNotNull(foundUser);
        assertTrue(foundUser.getId().equals("id"));
    }


    @Test
    void doesFindByIncorrectUsername() {
        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> userService.findByUsername("username"));
        assertTrue(ex.getMessage().contains("user not found"));
    }


    @Test
    void doesDeleteByIdCorrectly() {
        UserDocument foundUser = userService.findById("id");
        assertNotNull(foundUser);
        userService.deleteById("id");
        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.findById("id"));
        assertTrue(ex.getMessage().contains("user not found"));
    }


    @AfterEach
    void deleteData() {
        userRepository.deleteAll();
    }
}
