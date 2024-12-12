package com.taskflow.service4.repository;

import com.taskflow.service4.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug,Integer> {
}
