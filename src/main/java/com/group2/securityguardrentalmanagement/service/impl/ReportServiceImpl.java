package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.entity.Contract;
import com.group2.securityguardrentalmanagement.entity.Report;
import com.group2.securityguardrentalmanagement.entity.UserEntity;
import com.group2.securityguardrentalmanagement.exception.AppException;
import com.group2.securityguardrentalmanagement.exception.ErrorCode;
import com.group2.securityguardrentalmanagement.repository.ContractRepository;
import com.group2.securityguardrentalmanagement.repository.ReportRepository;
import com.group2.securityguardrentalmanagement.repository.UserRepository;
import com.group2.securityguardrentalmanagement.service.ReportService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class ReportServiceImpl implements ReportService {
    private ReportRepository reportRepository;
    private ContractRepository contractRepository;
    private UserRepository userRepository;
    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, ContractRepository contractRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.contractRepository = contractRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Report createReport(Report report, int userId, int contractId) {
        UserEntity userEntity = userRepository.findById(userId)
                        .orElseThrow();

        Contract contract = contractRepository.findById(contractId)
                        .orElseThrow();
        report.setContract(contract);
        report.setUserEntity(userEntity);
        report.setCreatedDate(LocalDateTime.now());
        return reportRepository.save(report);
    }

    @Override
    @Transactional
    public Report updateReport(int id, Report report, int contractId) {
        Report existReport = reportRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        if(existReport != null){
            Contract contract = contractRepository.findById(contractId)
                            .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
            existReport.setTitle(report.getTitle());
            existReport.setInformation(report.getInformation());
            existReport.setFileLinkReport(report.getFileLinkReport());
            existReport.setStatus(report.getStatus());
            existReport.setUpdatedDate(LocalDateTime.now());
            existReport.setContract(contract);
        }else{
            return null;
        }
        return existReport;
    }

    @Override
    @Transactional
    public void deleteReport(int id) {
        Report existReport = reportRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        if(existReport != null){
            reportRepository.deleteById(id);
        }
    }

    @Override
    public List<Report> getAllReport() {
        return reportRepository.findAll();
    }

    @Override
    public Report getById(int id) {
        return reportRepository.findById(id).orElse(null);
    }

    @Override
    public List<Report> searchReport(String title) {
        List<Report> reports = reportRepository.findByTitleContainingIgnoreCase(title);
        return reports;
    }
}
