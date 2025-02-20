package com.dailycodebuffer.employee.repository;

import com.dailycodebuffer.employee.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    // Find attendance by employee ID
    List<AttendanceEntity> findByEmployeeId(Long employeeId);

    // You already get basic functionality from JpaRepository for CRUD operations
}
