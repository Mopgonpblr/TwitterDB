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
import org.mockito.Mockito;
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

    @BeforeEach()
    public void setup() {
        user = new User( "TwitterAdmin", "Twitter Admin", "dog.png");
    }

    @Test
    public void CreateUser_ValidUser_UserDaoCreateCalled() {
        when(userRepository.save(user)).thenReturn(user);
        User newuser = userService.createUser(user);
        assertEquals(user, newuser);
    }

    @Test
    public void CreateUser_NullUser_NullPointerExceptionThrown() {
        when(userRepository.save(null)).thenThrow(new NullPointerException());
        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.createUser(null);
        });
        String expectedException = "java.lang.NullPointerException";
        String actualException = exception.getClass().getName();

        assertEquals(expectedException,actualException);
    }

    @Test
    public void CreateUser_NullUserNullRepository_NullPointerExceptionThrown() {
        userService.setUserRepository(null);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.createUser(null);
        });
        String expectedException = "java.lang.NullPointerException";
        String actualException = exception.getClass().getName();

        assertEquals(expectedException,actualException);
    }

    @Test
    public void FindUser_ValidUsername_NotNullUserReturned() {
        when(userRepository.findByUsername("TwitterAdmin")).thenReturn(user);
        assertEquals(user, userService.findUser("TwitterAdmin"));
    }

    @Test
    public void FindUser_InvalidUsername_NullUserReturned() {
        when(userRepository.findByUsername("randomusername")).thenReturn(null);
        assertNull(userService.findUser("randomusername"));
    }

    @Test
    public void FindUser_InvalidUsernameNullRepository_ThrowsNullPointerException() {
        userService.setUserRepository(null);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.findUser("randomusername");
        });
        String expectedException = "java.lang.NullPointerException";
        String actualException = exception.getClass().getName();

        assertEquals(expectedException,actualException);
    }

    @Test
    public void DeleteUser_ValidUser_UserDaoDeleteCalled() {
        doNothing().when(userRepository).delete(user);
        userService.deleteUser(user);
        verify(userRepository,times(1)).delete(user);
    }

    @Test
    public void DeleteUser_NullUser_UserDaoDeleteCalled() {
        doNothing().when(userRepository).delete(null);
        userService.deleteUser(null);
        verify(userRepository,times(1)).delete(null);
    }

    @Test
    public void DeleteUser_NullUserNullRepository_ThrowsNullPointerException() {
        userService.setUserRepository(null);
        Exception exception = assertThrows(NullPointerException.class, () -> {
            userService.deleteUser(null);
        });
        String expectedException = "java.lang.NullPointerException";
        String actualException = exception.getClass().getName();

        assertEquals(expectedException,actualException);
    }

    @Test
    public void UpdateAvatar_ValidAvatarAllowedFieldTrue_UserDaoUpdateAvatarCalled() throws Exception {
        ReflectionTestUtils.setField(userService, "allowed", true);
        doNothing().when(userRepository).updateAvatar("TwitterAdmin","avatar.jpeg");
        userService.updateAvatar("TwitterAdmin","avatar.jpeg", 1);
        verify(userRepository,times(1)).updateAvatar("TwitterAdmin","avatar.jpeg");
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
    public void UpdateAvatar_InvalidUserAllowedFieldFalse_ThrowsException(){
        ReflectionTestUtils.setField(userService, "allowed", false);
        Exception exception = assertThrows(Exception.class, () -> {
            userService.updateAvatar("TAdmin","avatar.jpeg", 0);
        });

        String expectedMessage = "property.update-user-and-tweet = false";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}