package com.applicationtracker.repository;

import com.applicationtracker.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppliRepo extends JpaRepository<Application,Long> {

    List<Application> findByUserId(Long userId);

    Optional<Application> findByAppIdAndUserId(Long appId,Long userId);

    @Query("Select a from Application a where lower(a.companyName) like lower(concat('%',:name,'%')) and a.user.id = :userId")
    Page<Application>  searchSortPagination(@Param("name") String name, @Param("userId") Long userId , Pageable pageable);


    Integer countByStatusAndUserId(String status,Long userId);
}
