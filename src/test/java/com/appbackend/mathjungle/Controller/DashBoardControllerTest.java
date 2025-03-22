//package com.appbackend.mathjungle.Controller;
//
//import com.appbackend.mathjungle.DTO.DashBoardDTO;
//import com.appbackend.mathjungle.Mapper.DashBoardMapper;
//import com.appbackend.mathjungle.Model.DashBoard;
//import com.appbackend.mathjungle.Service.DashBoardService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.security.Principal;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(DashBoardController.class)
//class DashBoardControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private DashBoardService dashBoardService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void testSaveData_Unauthorized() throws Exception {
//        // Principal is null => Controller should return 401
//        mockMvc.perform(post("/dashboard/save-userdata")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        // No principal set, so it's effectively null
//                        .content("{}"))
//                .andExpect(status().isUnauthorized())
//                .andExpect(content().string("Invalid JWT token"));
//
//        // Service should never be called if unauthorized
//        verify(dashBoardService, never()).saveData(any(DashBoard.class));
//    }
//
//    @Test
//    void testSaveData_Created() throws Exception {
//        // Arrange
//        DashBoard dashBoard = new DashBoard();
//        dashBoard.setLevelName("Level 1");
//        dashBoard.setScore(100);
//
//        // JSON content for request body
//        String jsonContent = objectMapper.writeValueAsString(dashBoard);
//
//        // Principal with a non-null name
//        Principal mockPrincipal = () -> "test@example.com";
//
//        // Act & Assert
//        mockMvc.perform(post("/dashboard/save-userdata")
//                        .principal(mockPrincipal)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonContent))
//                .andExpect(status().isCreated());
//
//        // Verify the service call
//        verify(dashBoardService, times(1)).saveData(any(DashBoard.class));
//    }
//
//    @Test
//    void testGetData_Unauthorized() throws Exception {
//        // No principal => should return 401
//        mockMvc.perform(get("/dashboard/get-userdata"))
//                .andExpect(status().isUnauthorized())
//                .andExpect(content().string("Invalid JWT token"));
//
//        verify(dashBoardService, never()).getDashBoardData(anyString());
//    }
//
//    @Test
//    void testGetData_ReturnsNull() throws Exception {
//        // If the service returns null => controller returns 200 with a body of "null"
//
//        Principal mockPrincipal = () -> "test@example.com";
//        when(dashBoardService.getDashBoardData("test@example.com")).thenReturn(null);
//
//        mockMvc.perform(get("/dashboard/get-userdata")
//                        .principal(mockPrincipal))
//                .andExpect(status().isOk())
//                .andExpect(content().string("null"));
//
//        verify(dashBoardService, times(1)).getDashBoardData("test@example.com");
//    }
//
//    @Test
//    void testGetData_ReturnsDashboard() throws Exception {
//        // If the service returns a DashBoard => controller returns 200 with JSON containing DashBoardDTO fields
//
//        Principal mockPrincipal = () -> "test@example.com";
//
//        DashBoard dashBoard = new DashBoard();
//        dashBoard.setId(1);
//        dashBoard.setLevelName("Level 2");
//        dashBoard.setScore(200);
//
//        when(dashBoardService.getDashBoardData("test@example.com")).thenReturn(dashBoard);
//
//        mockMvc.perform(get("/dashboard/get-userdata")
//                        .principal(mockPrincipal))
//                .andExpect(status().isOk())
//                // We can check the JSON fields using jsonPath
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.levelName").value("Level 2"))
//                .andExpect(jsonPath("$.score").value(200));
//
//        verify(dashBoardService, times(1)).getDashBoardData("test@example.com");
//    }
//}
