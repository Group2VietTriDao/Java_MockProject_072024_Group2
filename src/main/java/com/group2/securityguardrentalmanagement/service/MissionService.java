package com.group2.securityguardrentalmanagement.service;

import com.group2.securityguardrentalmanagement.dto.request.MissionRequest;
import com.group2.securityguardrentalmanagement.dto.response.MissionResponse;
import com.group2.securityguardrentalmanagement.entity.Mission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MissionService {
    public Mission createMission(MissionRequest request);
    public Mission updateMission(int MissionId, MissionRequest request);
    public void deleteMission(int MissionId);
    public List<MissionResponse> getAllMission();
}

