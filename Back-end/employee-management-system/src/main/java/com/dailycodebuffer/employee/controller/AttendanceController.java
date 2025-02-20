package com.dailycodebuffer.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dailycodebuffer.employee.entity.AttendanceEntity;
import com.dailycodebuffer.employee.services.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    public ResponseEntity<String> markAttendance(@RequestBody AttendanceEntity attendance) {
        attendanceService.markAttendance(attendance);
        return ResponseEntity.ok("Attendance marked successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttendanceEntity>> getAllAttendance() {
        List<AttendanceEntity> records = attendanceService.getAllAttendance();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<AttendanceEntity>> getAttendanceByEmployeeId(@PathVariable Long employeeId) {
        List<AttendanceEntity> records = attendanceService.getAttendanceByEmployeeId(employeeId);
        return ResponseEntity.ok(records);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAttendance(@PathVariable Long id, @RequestBody AttendanceEntity updatedAttendance) {
        Optional<AttendanceEntity> existingAttendance = attendanceService.getAttendanceById(id);

        if (existingAttendance.isPresent()) {
            AttendanceEntity attendance = existingAttendance.get();
            attendance.setDate(updatedAttendance.getDate());
            attendance.setStatus(updatedAttendance.getStatus());
            attendanceService.saveAttendance(attendance);
            return ResponseEntity.ok("Attendance updated successfully!");
        } else {
            return ResponseEntity.badRequest().body("Attendance record not found.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance record deleted successfully!");
    }
}
