package com.applicationtracker.repository;

import com.applicationtracker.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepo extends JpaRepository<Resume,Long> {

    Optional<Resume> findByUserId(Long userId);
}
