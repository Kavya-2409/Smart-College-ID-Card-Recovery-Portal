<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smartid.model.Student" %>
<%@ page import="com.smartid.model.LostReport" %>
<%@ page import="com.smartid.util.DBConnection" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    Student student = (Student) session.getAttribute("student");
    if (student == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<LostReport> myReports = new ArrayList<>();
    try (Connection conn = DBConnection.getConnection()) {
        if (conn != null) {
            String sql = "SELECT * FROM lost_reports WHERE student_id = ? OR roll_number = ? ORDER BY created_at DESC";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, student.getId());
                pstmt.setString(2, student.getRollNumber());
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        myReports.add(new LostReport(
                            rs.getInt("id"), rs.getInt("student_id"), rs.getString("roll_number"),
                            rs.getString("student_name"), rs.getString("department"), rs.getString("lost_location"),
                            rs.getString("description"), rs.getTimestamp("created_at"), rs.getString("status")
                        ));
                    }
                }
            }
        }
    } catch (Exception ignored) {}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard | Smart Lost ID Recovery Portal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <a href="index.jsp" class="logo"><div class="logo-icon">ID</div>Smart ID Recovery</a>
        <ul class="nav-links">
            <li><a href="index.jsp">Home</a></li>
            <li><a href="reportFound.jsp">Report Found ID</a></li>
            <li><a href="dashboard.jsp" class="active">Dashboard</a></li>
            <li><a href="LogoutServlet" class="nav-btn">Logout (<%= student.getName() %>)</a></li>
        </ul>
    </nav>
    <div class="container">
        <div style="background: linear-gradient(135deg, var(--primary-dark), var(--primary-blue)); color: white; border-radius: var(--radius-lg); padding: 2rem; margin-bottom: 2rem;">
            <h1>Welcome Back, <%= student.getName() %>!</h1>
            <p>Roll No: <strong><%= student.getRollNumber() %></strong> | Dept: <strong><%= student.getDepartment() %></strong></p>
        </div>
        <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(320px, 1fr)); gap: 2rem; margin-bottom: 2rem;">
            <div class="card">
                <h2>+ Report Lost ID Card</h2>
                <form action="LostReportServlet" method="post">
                    <div class="form-group"><label>Student Name *</label><input type="text" name="studentName" class="form-control" value="<%= student.getName() %>" required></div>
                    <div class="form-group"><label>Where did you lose your ID? *</label><input type="text" name="lostLocation" class="form-control" placeholder="e.g. Library, Cafeteria" required></div>
                    <div class="form-group"><label>Additional Details</label><textarea name="description" class="form-control" rows="2" placeholder="e.g. Blue lanyard"></textarea></div>
                    <button type="submit" class="btn btn-danger">Submit Lost Report</button>
                </form>
            </div>
            <div class="card">
                <h2>💡 Recovery Process</h2>
                <p>1. Status starts as <strong>Pending</strong>.<br>2. When located by Admin, status updates to <span class="badge status-found">Found</span>.<br>3. Collect your ID at Security Desk and click <strong>🎉 Mark as Found</strong>.</p>
            </div>
        </div>
        <h2>My Reported Lost ID Status</h2>
        <div class="table-responsive">
            <table>
                <thead><tr><th>Report ID</th><th>Lost Location</th><th>Description</th><th>Date</th><th>Status (Live Sync)</th><th>Action</th></tr></thead>
                <tbody>
                    <% if (myReports.isEmpty()) { %>
                        <tr><td colspan="6" style="text-align:center;">No reports filed yet.</td></tr>
                    <% } else { for (LostReport r : myReports) { %>
                        <tr>
                            <td>#LST-<%= r.getId() %></td>
                            <td><strong><%= r.getLostLocation() %></strong></td>
                            <td><%= r.getDescription() %></td>
                            <td><%= r.getReportDate() %></td>
                            <td><span class="badge status-<%= r.getStatus().toLowerCase() %>"><%= r.getStatus() %></span></td>
                            <td>
                                <% if (!"Returned".equalsIgnoreCase(r.getStatus())) { %>
                                    <form action="LostReportServlet" method="post"><input type="hidden" name="action" value="resolveSelf"><input type="hidden" name="reportId" value="<%= r.getId() %>"><button type="submit" class="btn btn-sm btn-success">🎉 Mark as Found</button></form>
                                <% } else { %><span>Closed</span><% } %>
                            </td>
                        </tr>
                    <% } } %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
