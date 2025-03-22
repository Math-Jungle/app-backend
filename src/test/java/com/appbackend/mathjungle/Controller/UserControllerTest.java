//package com.appbackend.mathjungle;
//
//import com.appbackend.mathjungle.Controller.UserController;
//import com.appbackend.mathjungle.DTO.HomePageDTO;
//import com.appbackend.mathjungle.Mapper.HomePageMapper;
//import com.appbackend.mathjungle.Model.Users;
//import com.appbackend.mathjungle.Service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockedStatic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.security.Principal;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private Users testUser;
//    private HomePageDTO testHomePageDTO;
//
//    @BeforeEach
//    void setUp() {
//        testUser = new Users();
//        testUser.setUserID(1);
//        testUser.setEmail("test@example.com");
//        testUser.setPassword("password123");
//
//        testHomePageDTO = new HomePageDTO();
//        testHomePageDTO.setUserID(1);
//        testHomePageDTO.setChildName("Nipuna");
//        testHomePageDTO.setAvatarId("avatar1");
//        testHomePageDTO.setChildAge(12);
//        // Set up the DTO with necessary fields
//    }
//
//    @Test
//    void testLoginSuccess() throws Exception {
//        // Arrange
//        when(userService.verify(any(Users.class))).thenReturn("test-jwt-token");
//
//        // Act & Assert
//        mockMvc.perform(post("/user/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(testUser)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("test-jwt-token"));
//
//        verify(userService, times(1)).verify(any(Users.class));
//    }
//
//    @Test
//    void testLoginFailure() throws Exception {
//        // Arrange
//        when(userService.verify(any(Users.class))).thenReturn("Failed");
//
//        // Act & Assert
//        mockMvc.perform(post("/user/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(testUser)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Failed"));
//
//        verify(userService, times(1)).verify(any(Users.class));
//    }
//
//    @Test
//    @WithMockUser(username = "test@example.com")
//    void testGetUserDetailsSuccess() throws Exception {
//        // Arrange
//        when(userService.getUserDetailsByUsername("test@example.com")).thenReturn(testUser);
//
//        try (MockedStatic<HomePageMapper> mockedMapper = mockStatic(HomePageMapper.class)) {
//            mockedMapper.when(() -> HomePageMapper.getData(any(Users.class))).thenReturn(testHomePageDTO);
//
//            // Act & Assert
//            mockMvc.perform(get("/user/details"))
//                    .andExpect(status().isOk())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//
//            verify(userService, times(1)).getUserDetailsByUsername("test@example.com");
//        }
//    }
//
//    @Test
//    void testGetUserDetailsUnauthorized() throws Exception {
//        // Act & Assert - no Principal
//        mockMvc.perform(get("/user/details"))
//                .andExpect(status().isUnauthorized());
//
//        verify(userService, never()).getUserDetailsByUsername(anyString());
//    }
//
//    @Test
//    @WithMockUser(username = "test@example.com")
//    void testGetUserDetailsUserNotFound() throws Exception {
//        // Arrange
//        when(userService.getUserDetailsByUsername("test@example.com")).thenReturn(null);
//
//        // Act & Assert
//        mockMvc.perform(get("/user/details"))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("User not found"));
//
//        verify(userService, times(1)).getUserDetailsByUsername("test@example.com");
//    }
//
//    @Test
//    @WithMockUser(username = "test@example.com")
//    void testDeleteUserSuccess() throws Exception {
//        // Arrange
//        when(userService.deleteUser(1)).thenReturn(true);
//        when(userService.getUserDetailsByUsername("test@example.com")).thenReturn(testUser);
//
//        // Act & Assert
//        mockMvc.perform(get("/user/delete/1"))
//                .andExpect(status().isOk());
//
//        verify(userService, times(1)).deleteUser(1);
//        verify(userService, times(1)).getUserDetailsByUsername("test@example.com");
//    }
//
//    @Test
//    void testDeleteUserUnauthorized() throws Exception {
//        // Act & Assert - no Principal
//        mockMvc.perform(get("/user/delete/1"))
//                .andExpect(status().isUnauthorized());
//
//        verify(userService, never()).deleteUser(anyInt());
//    }
//
//    @Test
//    @WithMockUser
//    void testVerifyJWTAuthorized() throws Exception {
//        // Act & Assert
//        mockMvc.perform(get("/user/verifyJWT"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testVerifyJWTUnauthorized() throws Exception {
//        // Act & Assert
//        mockMvc.perform(get("/user/verifyJWT"))
//                .andExpect(status().isUnauthorized());
//    }
//}