package com.appbackend.mathjungle.Mapper;

import com.appbackend.mathjungle.DTO.HomePageDTO;
import com.appbackend.mathjungle.Model.Users;

public class HomePageMapper {

    public static HomePageDTO getData(Users user){
        if (user == null) {
            return null;
        }
        HomePageDTO dto = new HomePageDTO();
        dto.setUserID(user.getUserID());

        if (user.getChildProfile() != null) {
            dto.setChildName(user.getChildProfile().getChildName());
            dto.setChildAge(user.getChildProfile().getAge());
            dto.setAvatarId(user.getChildProfile().getAvatarId());

        }
        return dto;
    }
}
