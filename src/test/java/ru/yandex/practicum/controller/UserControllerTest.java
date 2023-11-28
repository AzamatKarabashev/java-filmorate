package ru.yandex.practicum.controller;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.yandex.practicum.exception.CustomValidationException;
import ru.yandex.practicum.model.User;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private UserController userController;

    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        userController = new UserController();
        user1 = new User(1, "", "", "", LocalDate.of(2222, 10, 10));
        user2 = new User(0, "mail@mail.com", "Nagibator228", "Robert",
                LocalDate.of(1989, 9, 13));
    }

    @Test
    void whenGetUsers_thenGetListOfUsers() {
        userController.addUser(user2);
        User newUser = user2;

        assertEquals(newUser, userController.getUsers().get(0));
    }

    @Test
    void givenUser_whenAddUserMethod_thenFillMap() {
        userController.addUser(user2);

        assertEquals(1, userController.getUsers().size());
    }

    @Test
    void givenUser_whenUpdateUserMethod_thenFillMap() {
        userController.addUser(user2);
        User newUser = new User();
        newUser.setId(1);
        newUser.setEmail("mmm@mmm.mm");
        newUser.setLogin("Rocky");
        newUser.setName("Stanislav");
        newUser.setBirthday(LocalDate.of(1995, 5, 7));

        userController.updateUser(newUser);

        assertEquals(newUser, userController.getUsers().get(0));
    }

    @Test
    void givenWrongUser_whenPostRequest_thenThrowException() {
        assertThrows(CustomValidationException.class, () -> userController.addUser(user1));
    }

    @Test
    void givenWrongUser_whenPutRequest_thenThrowException() {
        assertThrows(CustomValidationException.class, () -> userController.updateUser(user1));
    }
}