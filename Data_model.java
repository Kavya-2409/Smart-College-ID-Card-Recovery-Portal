2. Java Data Models
com.smartid.model.Student.java
java


package com.smartid.model;
import java.io.Serializable;
import java.sql.Timestamp;
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String rollNumber;
    private String name;
    private String email;
    private String password;
    private String department;
    private String phone;
    private Timestamp createdAt;
    public Student() {}
    public Student(int id, String rollNumber, String name, String email, String password, String department, String phone, Timestamp createdAt) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
        this.phone = phone;
        this.createdAt = createdAt;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
com.smartid.model.LostReport.java
java


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
