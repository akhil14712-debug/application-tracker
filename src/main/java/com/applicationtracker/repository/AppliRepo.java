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

@Repository
public interface AppliRepo extends JpaRepository<Application,Long> {

    @Query("Select a from Application a where lower(a.companyName) like lower(concat('%',:name,'%'))")
    Page<Application>  searchSortPagination(@Param("name") String name,Pageable pageable);
}
