package com.ForumJavaWS.demo.rest.repository;

import com.ForumJavaWS.demo.rest.entity.ReportReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportReasonRepository extends JpaRepository<ReportReason, Integer> {
}
