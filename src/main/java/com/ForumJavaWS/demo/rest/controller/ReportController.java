package com.ForumJavaWS.demo.rest.controller;

import java.util.List;

import com.ForumJavaWS.demo.rest.entity.Report;
import com.ForumJavaWS.demo.rest.repository.ReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ReportController {
    @Autowired
    private ReportRepository reportRepository;

    // Fonction permettant de récuperer un signalement
    @ResponseBody
    @GetMapping("/report/{reportId}")
    public Report getReportById(final @PathVariable("reportId") Long reportId) {
        Report report = reportRepository.findById(reportId);
        return report;
    }

    // Fonction permettant de signaler tout les signalements
    @ResponseBody
    @GetMapping("/report")
    public List<Report> getReports() {
        return reportRepository.findAll();
    }


}
