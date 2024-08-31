package com.andersen.twitter;

import com.andersen.twitter.constants.AutoTweets;
import com.andersen.twitter.dao.UserDao;
import com.andersen.twitter.entities.Tweet;
import com.andersen.twitter.entities.User;
import com.andersen.twitter.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTests {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    private User user;

    private Tweet tweet;

    @BeforeEach
    public void setup() {
        user = new User( "TwitterAdmin", "Twitter Admin", "dog.png");
        tweet = new Tweet(1, "TwitterAdmin", AutoTweets.AVATAR_UPDATED);
    }

    @Test
    public void CreateUser_ValidUser_UserDaoCreateCalled() {
        doNothing().when(userDao).create(user);
        userService.createUser(user);
        verify(userDao,times(1)).create(user);
    }

    @Test
    public void CreateUser_NullUser_UserDaoCreateCalled() {
        doNothing().when(userDao).create(null);
        userService.createUser(null);
        verify(userDao,times(1)).create(null);
    }

    @Test
    public void CreateUser_NullUserNullDao_UserDaoCreateCalled() {
        userService.createUser(null);
        verify(userDao,times(1)).create(null);
    }

    @Test
    public void FindUser_ValidUsername_NotNullUserReturned() {
        when(userDao.getUser("TwitterAdmin")).thenReturn(user);
        assertEquals(user, userService.findUser("TwitterAdmin"));
    }

    @Test
    public void FindUser_InvalidUsername_NullUserReturned() {
        when(userDao.getUser("randomusername")).thenReturn(null);
        assertNull(userService.findUser("randomusername"));
    }

    @Test
    public void FindUser_InvalidUsernameNullDao_NullUserReturned() {
        assertNull(userService.findUser("randomusername"));
    }

    @Test
    public void DeleteUser_ValidUser_UserDaoDeleteCalled() {
        doNothing().when(userDao).delete(user);
        userService.deleteUser(user);
        verify(userDao,times(1)).delete(user);
    }

    @Test
    public void DeleteUser_NullUser_UserDaoDeleteCalled() {
        doNothing().when(userDao).delete(null);
        userService.deleteUser(null);
        verify(userDao,times(1)).delete(null);
    }

    @Test
    public void DeleteUser_NullUserNullDao_UserDaoDeleteCalled() {
        userService.deleteUser(null);
        verify(userDao,times(1)).delete(null);
    }

    @Test
    public void UpdateAvatar_ValidAvatarAllowedFieldTrue_UserDaoUpdateAvatarCalled() throws Exception {
        ReflectionTestUtils.setField(userService, "allowed", true);
        doNothing().when(userDao).updateAvatar("TwitterAdmin","avatar.jpeg", tweet);
        userService.updateAvatar("TwitterAdmin","avatar.jpeg", 1);
        verify(userDao,times(1)).updateAvatar("TwitterAdmin","avatar.jpeg", tweet);
    }

    @Test
    public void UpdateAvatar_ValidAvatarAllowedFieldFalse_ThrowsException(){
        ReflectionTestUtils.setField(userService, "allowed", false);
        Exception exception = assertThrows(Exception.class, () -> {
            userService.updateAvatar("TwitterAdmin","avatar.jpeg", 1);

        });

        String expectedMessage = "property.update-user-and-tweet = false";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void UpdateAvatar_InvalidAvatarAllowedFieldFalse_ThrowsException(){
        ReflectionTestUtils.setField(userService, "allowed", false);
        Exception exception = assertThrows(Exception.class, () -> {
            userService.updateAvatar("TAdmin","avatar.jpeg", 0);

        });

        String expectedMessage = "property.update-user-and-tweet = false";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}