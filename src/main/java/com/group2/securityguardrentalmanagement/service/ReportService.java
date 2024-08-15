package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.entity.Report;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService {
    public Report createReport(Report report, int userId, int ContractID);
    public Report updateReport(int id, Report report, int contractId);
    public void deleteReport(int id);
    public List<Report> getAllReport();
    public Report getById(int id);
    public List<Report> searchReport(String title);
}
