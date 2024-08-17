package com.group2.securityguardrentalmanagement.repository;

import com.group2.securityguardrentalmanagement.entity.Employee;
import com.group2.securityguardrentalmanagement.entity.EmployeeTraining;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeTrainingRepository extends JpaRepository<EmployeeTraining, Integer> {

    // Phương thức xóa tất cả dựa trên ClassID
    //void deleteByClassTraining_ClassTrainingId(int classId);

    @Query("SELECT et FROM EmployeeTraining et WHERE et.classTraining.classTrainingId = :classTrainingId")
    List<EmployeeTraining> findByClassTrainingId(@Param("classTrainingId")int classTrainingId);

    @Query("SELECT et FROM EmployeeTraining et WHERE et.classTraining.classTrainingId = :classTrainingId AND et.employee.employeeId IN :employeeIds")
    List<EmployeeTraining> findByClassTrainingIdAndEmployeeId(@Param("classTrainingId")int classTrainingId, @Param("employeeIds")List<Integer> employeeIds);

    @Modifying
    @Query("DELETE FROM EmployeeTraining et WHERE et.classTraining.classTrainingId = :classTrainingId")
    void deleteByClassTrainingId(@Param("classTrainingId") int classTrainingId);

}
