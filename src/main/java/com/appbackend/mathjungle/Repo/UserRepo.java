package com.appbackend.mathjungle.Repo;

import com.appbackend.mathjungle.Model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
    Users findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Users u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);
}
