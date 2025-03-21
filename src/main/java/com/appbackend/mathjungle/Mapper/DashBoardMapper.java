package com.appbackend.mathjungle.Mapper;

import com.appbackend.mathjungle.DTO.DashBoardDTO;
import com.appbackend.mathjungle.Model.DashBoard;

public class DashBoardMapper {

    public static DashBoardDTO toDTO(DashBoard dashBoard) {
        if (dashBoard == null) {
            return null;
        }
        DashBoardDTO dto = new DashBoardDTO();
        dto.setId(dashBoard.getId());
        dto.setLevelName(dashBoard.getLevelName());
        dto.setScore(dashBoard.getScore());
        dto.setReactionTimes(dashBoard.getReactionTimes());
        dto.setTimeStamp(dashBoard.getTimeStamp());

        // Flatten the user info if it exists
        if (dashBoard.getUser() != null) {
            dto.setUserId(dashBoard.getUser().getUserID());
        }

        return dto;
    }
}
