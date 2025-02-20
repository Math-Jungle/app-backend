package com.appbackend.mathjungle.Repo;

import com.appbackend.mathjungle.Model.Child;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepo extends JpaRepository<Child, Long> {

}

