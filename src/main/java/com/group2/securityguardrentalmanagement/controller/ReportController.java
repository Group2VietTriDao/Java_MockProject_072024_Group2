package com.group2.securityguardrentalmanagement.controller;

import com.group2.securityguardrentalmanagement.entity.Report;
import com.group2.securityguardrentalmanagement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    private ReportService reportService;
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Report>> searchReport(@RequestParam("title") String title){
        List<Report> report = reportService.searchReport(title);
        if (report.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(report);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Report>> getAllReport(){
        List<Report> report = reportService.getAllReport();
        if (report.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(report);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable int id){
        Report report = reportService.getById(id);
        if (report == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(report);
        }
    }

    @PostMapping("/add/{userId}/{contractId}")
    public ResponseEntity<Report> createReport(@RequestBody Report report, @PathVariable int userId, @PathVariable int contractId){
        Report reportAdd = reportService.createReport(report, userId, contractId);
        return new ResponseEntity<>(reportAdd, HttpStatus.CREATED);
    }

    @PutMapping("/update/{reportId}/{contractId}")
    public ResponseEntity<Report> updateReport(@PathVariable int reportId, @PathVariable int contractId, @RequestBody Report reportRequest) {
        Report updatedReport = reportService.updateReport(reportId, reportRequest, contractId);
        return new ResponseEntity<>(updatedReport,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reportId}")
    public ResponseEntity<Void> deleteReport(@PathVariable int reportId) {
        Report report = reportService.getById(reportId);
        if (report == null) {
            return ResponseEntity.noContent().build();
        } else {
            reportService.deleteReport(reportId);
            return ResponseEntity.notFound().build();
        }
    }


}
