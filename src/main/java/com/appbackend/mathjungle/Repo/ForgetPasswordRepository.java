package com.appbackend.mathjungle.Repo;

import com.appbackend.mathjungle.Model.ForgetPassword;
import com.appbackend.mathjungle.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword, Integer> {

    @Query("select fp from ForgetPassword fp where fp.otp=?1 and fp.user=?2")
    Optional<ForgetPassword> findByOtpAndUser(Integer otp, Users user);
}
