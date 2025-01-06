package com.example.bd_project.services;

import com.example.bd_project.model.Reports;
import com.example.bd_project.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {
    @Autowired
    private ReportsRepository reportRepository;

    public void saveReport(Reports report) {
        reportRepository.save(report);
    }

    public List<Reports> getAllReports() {
        return reportRepository.findAll();
    }
}
