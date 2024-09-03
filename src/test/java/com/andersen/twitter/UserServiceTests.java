package com.andersen.twitter;

import com.andersen.twitter.entities.User;
import com.andersen.twitter.repositories.UserRepository;
import com.andersen.twitter.service.TweetService;
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
    private UserRepository userRepository;

    @Mock
    private TweetService tweetService;

    @InjectMocks
    private UserService userService;

    private User user;

    private User invalidUser;

    @BeforeEach()
    public void setup() {
        user = new User("TwitterAdmin", "Twitter Admin", "dog.png");
        invalidUser = new User(null, "New Admin", "roll.png");
    }

    @Test
    public void CreateUser_ValidUser_UserDaoCreateCalled() {
        when(userRepository.save(user)).thenReturn(user);
        User newuser = userService.createUser(user);
        assertEquals(user, newuser);
    }

    @Test
    public void CreateUser_NullUserName_NullPointerExceptionThrown() {
        when(userRepository.save(invalidUser)).thenThrow(new NullPointerException());
       assertThrows(NullPointerException.class, () -> {
            userService.createUser(invalidUser);
        });
    }

    @Test
    public void CreateUser_NullUser_NullPointerExceptionThrown() {
        when(userRepository.save(null)).thenThrow(new NullPointerException());
        assertThrows(NullPointerException.class, () -> {
            userService.createUser(null);
        });
    }

    @Test
    public void FindUser_ValidUsername_NotNullUserReturned() {
        when(userRepository.findByUsername("TwitterAdmin")).thenReturn(user);
        User findUser = userService.findUser("TwitterAdmin");
        assertEquals(user, findUser);
    }

    @Test
    public void FindUser_InvalidUsername_NullUserReturned() {
        User findUser = userService.findUser("randomusername");
        assertNull(findUser);
    }

    @Test
    public void FindUser_NullUsername_NullUserReturned() {
        User findUser = userService.findUser(null);
        assertNull(findUser);
    }

    @Test
    public void DeleteUser_ValidUser_UserRepositoryDeleteCalled() {
        userService.deleteUser(user);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void DeleteUser_NullUser_UserRepositoryDeleteCalled() {
        userService.deleteUser(invalidUser);
        verify(userRepository, times(1)).delete(invalidUser);
    }

    @Test
    public void DeleteUser_NullUserNullRepository_UserRepositoryDeleteCalled() {
        userService.deleteUser(null);
        verify(userRepository, times(1)).delete(null);
    }

    @Test
    public void UpdateAvatar_ValidAvatarAllowedFieldTrue_UserRepositoryUpdateAvatarCalled() throws Exception {
        ReflectionTestUtils.setField(userService, "allowed", true);
        doNothing().when(userRepository).updateAvatar("TwitterAdmin", "avatar.jpeg");
        userService.updateAvatar("TwitterAdmin", "avatar.jpeg", 1);
        verify(userRepository, times(1)).updateAvatar("TwitterAdmin", "avatar.jpeg");
    }

    @Test
    public void UpdateAvatar_ValidAvatarAllowedFieldFalse_ThrowsException() {
        ReflectionTestUtils.setField(userService, "allowed", false);
        Exception exception = assertThrows(Exception.class, () -> {
            userService.updateAvatar("TwitterAdmin", "avatar.jpeg", 1);

        });

        String expectedMessage = "property.update-user-and-tweet = false";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void UpdateAvatar_InvalidUserAllowedFieldFalse_ThrowsException() {
        ReflectionTestUtils.setField(userService, "allowed", false);
        Exception exception = assertThrows(Exception.class, () -> {
            userService.updateAvatar("TAdmin", "avatar.jpeg", 0);
        });

        String expectedMessage = "property.update-user-and-tweet = false";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}