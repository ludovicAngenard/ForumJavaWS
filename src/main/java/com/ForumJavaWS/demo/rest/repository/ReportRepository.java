package com.ForumJavaWS.demo.rest.repository;

import com.ForumJavaWS.demo.rest.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    public Report findById(Long id);
}
