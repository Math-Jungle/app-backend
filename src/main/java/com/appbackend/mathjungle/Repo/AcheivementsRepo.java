package com.appbackend.mathjungle.Repo;

import com.appbackend.mathjungle.Model.Acheivements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcheivementsRepo extends JpaRepository<Integer, Acheivements> {
}
