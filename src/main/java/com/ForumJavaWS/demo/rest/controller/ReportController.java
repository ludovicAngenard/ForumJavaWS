package com.ForumJavaWS.demo.rest.controller;

import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Report;
import com.ForumJavaWS.demo.rest.repository.ReportRepository;
import com.ForumJavaWS.demo.rest.security.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ReportController {
    @Autowired
    private ReportRepository reportRepository;

    @ResponseBody
    @GetMapping("/report/{reportId}")
    public Report getReportById(final @PathVariable("reportId") Long reportId){
        Report report = reportRepository.findById(reportId);
        return report;
    }

    @ResponseBody
    @GetMapping("/report")
    public List<Report> getReports(){
        return reportRepository.findAll();
    }

    @PostMapping("/report")
    public Report addreport(@RequestBody Report report) throws Exception{
        System.out.println("report : " + report.getPost());
        if (report.getPost() != null){
            report.setUser(UserDetailsServiceImpl.getCurrentUser());
            return reportRepository.save(report);
        } else {
            throw new Exception("Le post que vous signalez n'existe pas.");
        }
    }
}
