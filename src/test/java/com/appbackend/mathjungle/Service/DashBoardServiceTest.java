package com.appbackend.mathjungle.Service;

import com.appbackend.mathjungle.Model.DashBoard;
import com.appbackend.mathjungle.Repo.DashBoardRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DashBoardServiceTest {

    @Mock
    private DashBoardRepo dashBoardRepo;

    @InjectMocks
    private DashBoardService dashBoardService;

    @Test
    void testSaveData() {
        // Arrange
        DashBoard dashBoardData = new DashBoard();
        dashBoardData.setLevelName("Level 1");
        dashBoardData.setScore(100);

        // Act
        dashBoardService.saveData(dashBoardData);

        // Assert
        // Verify dashBoardRepo.save() is called exactly once with the same DashBoard instance
        verify(dashBoardRepo, times(1)).save(dashBoardData);
    }

    @Test
    void testGetDashBoardData() {
        // Arrange
        String email = "test@example.com";
        DashBoard dashBoard = new DashBoard();
        dashBoard.setId(1);
        dashBoard.setLevelName("Level 1");
        dashBoard.setScore(100);

        when(dashBoardRepo.findByUserEmail(email)).thenReturn(dashBoard);

        // Act
        DashBoard result = dashBoardService.getDashBoardData(email);

        // Assert
        verify(dashBoardRepo, times(1)).findByUserEmail(email);
        assertNotNull(result, "Returned DashBoard should not be null");
        assertEquals(1, result.getId());
        assertEquals("Level 1", result.getLevelName());
        assertEquals(100, result.getScore());
    }
}
