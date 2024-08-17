package com.group2.securityguardrentalmanagement.controller;

import com.group2.securityguardrentalmanagement.dto.request.MissionRequest;
import com.group2.securityguardrentalmanagement.dto.response.MissionResponse;
import com.group2.securityguardrentalmanagement.entity.Mission;
import com.group2.securityguardrentalmanagement.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mission")
public class MissionController {
    private MissionService missionService;
    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MissionResponse>> getAllMission(){
        List<MissionResponse> list = missionService.getAllMission();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Mission> addMission(@RequestBody MissionRequest request){
        Mission mission = missionService.createMission(request);
        return new ResponseEntity<>(mission,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mission> updateMission(@PathVariable int id,@RequestBody MissionRequest request){
        Mission mission = missionService.updateMission(id,request);
        return new ResponseEntity<>(mission,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMission(@PathVariable int id){
        missionService.deleteMission(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
