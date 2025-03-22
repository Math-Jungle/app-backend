package com.appbackend.mathjungle.Service;

import com.appbackend.mathjungle.Model.Users;
import com.appbackend.mathjungle.Repo.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JWTService jwtService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserService userService;

    private Users testUser;

    @BeforeEach
    void setUp() {
        testUser = new Users();
        testUser.setUserID(1);
        testUser.setEmail("test@example.com");
        testUser.setPassword("password123");
    }

    @Test

    void testRegisterUser() {
        // Act
        userService.registerUser(testUser);

        // Assert
        verify(userRepo, times(1)).save(any(Users.class));
        // Verify password is encoded (not the original)
        assertNotEquals("password123", testUser.getPassword());
    }

    @Test
    void testCheckUserDetails_UserExists() {
        // Arrange
        when(userRepo.findByEmail("test@example.com")).thenReturn(testUser);

        // Act
        boolean result = userService.checkUserDetails(testUser);

        // Assert
        assertTrue(result);
        verify(userRepo, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testCheckUserDetails_UserDoesNotExist() {
        // Arrange
        when(userRepo.findByEmail("test@example.com")).thenReturn(null);

        // Act
        boolean result = userService.checkUserDetails(testUser);

        // Assert
        assertFalse(result);
        verify(userRepo, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testVerify_Authenticated() {
        // Arrange
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(jwtService.generateToken(anyString())).thenReturn("test-jwt-token");

        // Act
        String result = userService.verify(testUser);

        // Assert
        assertEquals("test-jwt-token", result);
        verify(authManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).generateToken(testUser.getEmail());
    }

    @Test
    void testVerify_NotAuthenticated() {
        // Arrange
        when(authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(false);

        // Act
        String result = userService.verify(testUser);

        // Assert
        assertEquals("Failed", result);
        verify(authManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, never()).generateToken(anyString());
    }

    @Test
    void testGetUserDetailsByUsername() {
        // Arrange
        when(userRepo.findByEmail("test@example.com")).thenReturn(testUser);

        // Act
        Users result = userService.getUserDetailsByUsername("test@example.com");

        // Assert
        assertNotNull(result);
        assertEquals(testUser.getUserID(), result.getUserID());
        assertEquals(testUser.getEmail(), result.getEmail());
        verify(userRepo, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testDeleteUser_UserExists() {
        // Arrange
        when(userRepo.existsById(1)).thenReturn(true);

        // Act
        boolean result = userService.deleteUser(1);

        // Assert
        assertTrue(result);
        verify(userRepo, times(1)).deleteById(1);
    }

    @Test
    void testDeleteUser_UserDoesNotExist() {
        // Arrange
        when(userRepo.existsById(1)).thenReturn(false);

        // Act
        boolean result = userService.deleteUser(1);

        // Assert
        assertFalse(result);
        verify(userRepo, never()).deleteById(anyInt());
    }
}