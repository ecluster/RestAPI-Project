package com.example.restapiproject;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivityTest {

    @Test
    public void emptyUserField() {
        String username = "";
        assertEquals(true, MainActivity.isUsernameEmpty(username));
    }

    @Test
    public void filledUserField() {
        String username = "edward";
        assertEquals(false, MainActivity.isUsernameEmpty(username));
    }

    @Test
    public void emptyPasswordField() {
        String password = "";
        assertEquals(true, MainActivity.isPasswordEmpty(password));
    }

    @Test
    public void filledPasswordField() {
        String password = "password";
        assertEquals(false, MainActivity.isPasswordEmpty(password));
    }

    @Test
    public void hasInput() {
        String username = "edward";
        String password = "password";
        assertEquals(true, MainActivity.hasInput(username, password));
    }

    @Test
    public void hasNoInput() {
        String username = "";
        String password = "";
        assertEquals(false, MainActivity.hasInput(username, password));
    }

    @Test
    public void missingUsernameInput() {
        String username = "";
        String password = "password";
        assertEquals(false, MainActivity.hasInput(username, password));
    }

    @Test
    public void missingPasswordInput() {
        String username = "username";
        String password = "";
        assertEquals(false, MainActivity.hasInput(username, password));
    }

    @Test
    public void matchingPassword() {
        String password = "password";
        List<User> users = new ArrayList<>();
        users.add(new User("edward", "password", "1"));
        assertEquals(true, MainActivity.matchPassword(password, users));
    }

    @Test
    public void noMatchingPassword() {
        String password = "123";
        List<User> users = new ArrayList<>();
        users.add(new User("edward", "password", "1"));
        assertEquals(false, MainActivity.matchPassword(password, users));
    }

    @Test
    public void userExists() {
        String user1 = "edward";
        List<User> users = new ArrayList<>();
        users.add(new User("edward", "password", "1"));
        assertEquals(true, MainActivity.userExist(user1, users));
    }

    @Test
    public void userDoesNotExists() {
        String user1 = "edward";
        List<User> users = new ArrayList<>();
        users.add(new User("person", "password", "1"));
        assertEquals(false, MainActivity.userExist(user1, users));
    }

    @Test
    public void getUser() {
        User u1 = new User("edward", "password", "1");
        List<User> users = new ArrayList<>();
        users.add(u1);
        assertEquals(u1, MainActivity.getUser("edward", users));
    }

    @Test
    public void canGetUser() {
        User u1 = new User("person", "password", "1");
        List<User> users = new ArrayList<>();
        users.add(u1);
        assertEquals(null, MainActivity.getUser("edward", users));
    }


}
