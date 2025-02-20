package com.dailycodebuffer.employee.services;

import com.dailycodebuffer.employee.entity.AttendanceEntity;
import com.dailycodebuffer.employee.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Method to mark attendance
    public void markAttendance(AttendanceEntity attendance) {
        attendanceRepository.save(attendance);
    }

    // Method to fetch all attendance records
    public List<AttendanceEntity> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    // Method to fetch attendance records by employeeId
    public List<AttendanceEntity> getAttendanceByEmployeeId(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    // Method to fetch a single attendance record by ID
    public Optional<AttendanceEntity> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    // Method to update attendance (called from controller)
    public void saveAttendance(AttendanceEntity attendance) {
        attendanceRepository.save(attendance);
    }

    // Method to delete attendance record
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}
