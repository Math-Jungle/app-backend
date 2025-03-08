package com.appbackend.mathjungle.Service;

import com.appbackend.mathjungle.Model.DashBoard;
import com.appbackend.mathjungle.Repo.DashBoardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashBoardService {

    @Autowired
    DashBoardRepo dashBoardRepo;

    public void saveData(DashBoard dashBoardData) {
        dashBoardRepo.save(dashBoardData);
    }

    public DashBoard getDashBoardData(String email) {

        return dashBoardRepo.findByEmail(email);

    }
}
