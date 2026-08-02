package com.smartid.model;
import java.io.Serializable;
import java.sql.Timestamp;
public class LostReport implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int studentId;
    private String rollNumber;
    private String studentName;
    private String department;
    private String lostLocation;
    private String description;
    private Timestamp reportDate;
    private String status; // 'Pending', 'Found', 'Returned'
    public LostReport() {}
    public LostReport(int id, int studentId, String rollNumber, String studentName, String department, 
                      String lostLocation, String description, Timestamp reportDate, String status) {
        this.id = id;
        this.studentId = studentId;
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.department = department;
        this.lostLocation = lostLocation;
        this.description = description;
        this.reportDate = reportDate;
        this.status = status;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getLostLocation() { return lostLocation; }
    public void setLostLocation(String lostLocation) { this.lostLocation = lostLocation; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Timestamp getReportDate() { return reportDate; }
    public void setReportDate(Timestamp reportDate) { this.reportDate = reportDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
