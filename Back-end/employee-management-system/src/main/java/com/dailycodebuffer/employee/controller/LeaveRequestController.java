package com.dailycodebuffer.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodebuffer.employee.entity.LeaveRequestEntity;
import com.dailycodebuffer.employee.services.LeaveRequestService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/leave-request")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    // Endpoint to get all leave requests for an employee
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<LeaveRequestEntity>> getLeaveRequests(@PathVariable Long employeeId) {
        try {
            List<LeaveRequestEntity> leaveRequests = leaveRequestService.getLeaveRequests(employeeId);
            return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/leave-request/{employeeId}")
    public ResponseEntity<LeaveRequestEntity> applyLeave(
            @PathVariable Long employeeId,
            @RequestBody LeaveRequestEntity leaveRequest) {
        System.out.println("Received leave request: " + leaveRequest);
        try {
            LeaveRequestEntity newLeaveRequest = leaveRequestService.applyLeave(employeeId, leaveRequest);
            return new ResponseEntity<>(newLeaveRequest, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // Endpoint to approve a leave request
    @PatchMapping("/approve/{requestId}")
    public ResponseEntity<LeaveRequestEntity> approveLeave(@PathVariable Long requestId) {
        try {
            LeaveRequestEntity approvedLeaveRequest = leaveRequestService.approveLeave(requestId);
            return new ResponseEntity<>(approvedLeaveRequest, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to reject a leave request
    @PatchMapping("/reject/{requestId}")
    public ResponseEntity<LeaveRequestEntity> rejectLeave(@PathVariable Long requestId) {
        try {
            LeaveRequestEntity rejectedLeaveRequest = leaveRequestService.rejectLeave(requestId);
            return new ResponseEntity<>(rejectedLeaveRequest, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
