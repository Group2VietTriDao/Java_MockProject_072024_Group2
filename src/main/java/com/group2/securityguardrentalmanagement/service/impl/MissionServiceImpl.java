package com.group2.securityguardrentalmanagement.service.impl;

import com.group2.securityguardrentalmanagement.dto.request.MissionRequest;
import com.group2.securityguardrentalmanagement.dto.response.MissionResponse;
import com.group2.securityguardrentalmanagement.entity.Contract;
import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.Mission;
import com.group2.securityguardrentalmanagement.repository.ContractRepository;
import com.group2.securityguardrentalmanagement.repository.EmployeeRepository;
import com.group2.securityguardrentalmanagement.repository.MissionRepository;
import com.group2.securityguardrentalmanagement.service.MissionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MissionServiceImpl implements MissionService {
    private MissionRepository missionRepository;
    private EmployeeRepository employeeRepository;
    private ContractRepository contractRepository;
    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository, EmployeeRepository employeeRepository, ContractRepository contractRepository) {
        this.missionRepository = missionRepository;
        this.employeeRepository = employeeRepository;
        this.contractRepository = contractRepository;
    }

    @Override
    @Transactional
    public Mission createMission(MissionRequest request) {
        // Tạo mới mission
        Mission mission = new Mission();
        mission.setAddress(request.getAddress());
        mission.setDescription(request.getDescription());
        mission.setStartDate(request.getStartDate());
        mission.setEndDate(request.getEndDate());
        mission.setFeedBack(request.getFeedBack());
        mission.setRating(request.getRating());
        mission.setSalary(request.getSalary());
        mission.setStatus(request.getStatus());
        mission.setCreatedDate(LocalDateTime.now());
        mission.setTaskDescription(request.getTaskDescription());
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                    .orElseThrow(()-> new RuntimeException("Not found Employee"));
        Contract contract = contractRepository.findById(request.getContractId())
                        .orElseThrow(()-> new RuntimeException("Not found Contract"));
        mission.setEmployee(employee);
        mission.setContract(contract);

        return missionRepository.save(mission);
    }

    @Override
    @Transactional
    public Mission updateMission(int MissionId, MissionRequest request) {
        Mission mission = missionRepository.findById(MissionId)
                .orElseThrow(()-> new RuntimeException("Not found Mission"));
        mission.setAddress(request.getAddress());
        mission.setDescription(request.getDescription());
        mission.setStartDate(request.getStartDate());
        mission.setEndDate(request.getEndDate());
        mission.setFeedBack(request.getFeedBack());
        mission.setRating(request.getRating());
        mission.setSalary(request.getSalary());
        mission.setStatus(request.getStatus());
        mission.setUpdatedDate(LocalDateTime.now());
        mission.setTaskDescription(request.getTaskDescription());
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(()-> new RuntimeException("Not found Employee"));
        Contract contract = contractRepository.findById(request.getContractId())
                .orElseThrow(()-> new RuntimeException("Not found Contract"));
        mission.setEmployee(employee);
        mission.setContract(contract);

        return missionRepository.save(mission);
    }

    @Override
    @Transactional
    public void deleteMission(int missionId) {
        // Trước hết, lấy Mission dựa trên missionId
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        // Xóa các liên kết với Employee và Contract trước
        mission.setEmployee(null);
        mission.setContract(null);

        // Lưu lại các thay đổi
        missionRepository.save(mission);

        // Xóa Mission
        missionRepository.deleteById(missionId);

    }

    @Override
    public List<MissionResponse> getAllMission() {
        List<Mission> mission = missionRepository.findAll();
        List<MissionResponse> responseList = new ArrayList<>();
        for(Mission addMission : mission){
            MissionResponse response = new MissionResponse();
            response.setAddress(addMission.getAddress());
            response.setTaskDescription(addMission.getTaskDescription());
            response.setStartDate(addMission.getStartDate());
            response.setEndDate(addMission.getEndDate());
            response.setStatus(addMission.getStatus());
            response.setEmployeeName(addMission.getEmployee().getName());
            response.setContractId(addMission.getContract().getContractId());

            responseList.add(response);
        }
        return responseList;
    }
}
