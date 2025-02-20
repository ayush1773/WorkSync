package com.dailycodebuffer.employee.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailycodebuffer.employee.entity.LeaveRequestEntity;
import com.dailycodebuffer.employee.repository.LeaveRequestRepository;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    // Apply for leave
    public LeaveRequestEntity applyLeave(Long employeeId, LeaveRequestEntity leaveRequest) {
        // Here we can just save the leave request and default its status in memory if needed
        leaveRequest.setId(employeeId); // Set employee ID for the request
        // We won't set a status in the entity, but internally we can manage it (e.g., "Pending")
        return leaveRequestRepository.save(leaveRequest);
    }

    // Get leave requests for a specific employee
    public List<LeaveRequestEntity> getLeaveRequests(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    // Approve a leave request (Without setting the status directly in the entity)
    public LeaveRequestEntity approveLeave(Long requestId) {
        Optional<LeaveRequestEntity> leaveRequestOpt = leaveRequestRepository.findById(requestId);
        if (leaveRequestOpt.isPresent()) {
            LeaveRequestEntity leaveRequest = leaveRequestOpt.get();
            // Handle approval internally; no status field to set in the entity
            // You can do further processing like reducing leave balance if needed
            return leaveRequest;
        }
        throw new RuntimeException("Leave request not found");
    }

    // Reject a leave request (Without setting the status directly in the entity)
    public LeaveRequestEntity rejectLeave(Long requestId) {
        Optional<LeaveRequestEntity> leaveRequestOpt = leaveRequestRepository.findById(requestId);
        if (leaveRequestOpt.isPresent()) {
            LeaveRequestEntity leaveRequest = leaveRequestOpt.get();
            // Handle rejection internally; no status field to set in the entity
            // You can add logic here like notifying the employee or something similar
            return leaveRequest;
        }
        throw new RuntimeException("Leave request not found");
    }
}
