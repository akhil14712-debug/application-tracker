package com.applicationtracker.repository;

import com.applicationtracker.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppliRepo extends JpaRepository<Application,Long> {
}
