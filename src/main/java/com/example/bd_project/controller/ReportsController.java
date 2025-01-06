/*package com.example.bd_project.controller;

import com.example.bd_project.dto.ReportsDto;
import com.example.bd_project.model.Reports;
import com.example.bd_project.model.User;
import com.example.bd_project.repository.ReportsRepository;
import com.example.bd_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    @Autowired
    private ReportsRepository reportsRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<Reports> getAllReports() {
        return reportsRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Reports> createReports(@RequestBody ReportsDto reportsDto) {
        User user = userRepository.findByName(reportsDto.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Reports Reports = new Reports(reportsDto, user);
        reportsRepository.save(Reports);
        return ResponseEntity.ok(Reports);
    }

    @GetMapping("/{id}")
    public Reports getReportsById(@PathVariable Long id) {
        return reportsRepository.findById(id).orElseThrow(() -> new RuntimeException("Reports not found"));
    }

    @PutMapping("/{id}")
    public Reports updateReports(@PathVariable Long id, @RequestBody ReportsDto reportsDto) {
        User user = userRepository.findByName(reportsDto.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        reportsRepository.findById(id).orElseThrow(() -> new RuntimeException("Reports not found"));
        Reports reports = new Reports(reportsDto, user);
        return reportsRepository.save(reports);
    }

    @DeleteMapping("/{id}")
    public String deleteReports(@PathVariable Long id) {
        reportsRepository.deleteById(id);
        return "Reports deleted";
    }
}
*/