package com.dailycodebuffer.employee.entity;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String emailId;

    private int leaveBalance = 20; // ✅ New field for leave balance

    // ✅ Constructors
    public EmployeeEntity() {}

    public EmployeeEntity(String firstName, String lastName, String emailId, int leaveBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.leaveBalance = leaveBalance;
    }

    // ✅ Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    // ✅ Method to deduct leave balance
    public boolean deductLeave(int days) {
        if (leaveBalance >= days) {
            leaveBalance -= days;
            return true;
        }
        return false;
    }
}
