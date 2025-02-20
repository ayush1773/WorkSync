package com.dailycodebuffer.employee.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodebuffer.employee.entity.LeaveRequestEntity;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequestEntity, Long> {

    // Find all leave requests for a specific employee
    List<LeaveRequestEntity> findByEmployeeId(Long employeeId);

    // Optionally, find leave requests within a certain date range
    List<LeaveRequestEntity> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
}
