package com.appbackend.mathjungle.Repo;

import com.appbackend.mathjungle.Model.DashBoard;
import com.appbackend.mathjungle.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashBoardRepo extends JpaRepository<DashBoard, Integer> {

    DashBoard findByUserEmail(String email);
}
