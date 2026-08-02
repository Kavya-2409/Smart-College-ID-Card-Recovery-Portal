package com.smartid.servlet;
import com.smartid.util.DBConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String rollNumber = request.getParameter("rollNumber");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        if (rollNumber == null || email == null || newPassword == null || confirmPassword == null ||
            rollNumber.trim().isEmpty() || email.trim().isEmpty() || newPassword.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required!");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
            return;
        }
        rollNumber = rollNumber.trim().toUpperCase();
        email = email.trim();
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "New Password and Confirm Password do not match!");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
            return;
        }
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                request.setAttribute("errorMessage", "Database Connection Error!");
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
                return;
            }
            // 1. Verify Roll Number and Registered Email Match
            String checkSql = "SELECT email FROM students WHERE roll_number = ?";
            checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, rollNumber);
            rs = checkStmt.executeQuery();
            if (!rs.next()) {
                request.setAttribute("errorMessage", "Roll Number '" + rollNumber + "' is not registered in the system!");
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
                return;
            }
            String registeredEmail = rs.getString("email");
            if (!registeredEmail.equalsIgnoreCase(email)) {
                request.setAttribute("errorMessage", "Security Alert: Email '" + email + "' does NOT match the registered email for Roll Number '" + rollNumber + "'!");
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
                return;
            }
            // 2. Update Password
            String updateSql = "UPDATE students SET password = ? WHERE roll_number = ? AND email = ?";
            updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, newPassword.trim());
            updateStmt.setString(2, rollNumber);
            updateStmt.setString(3, registeredEmail);
            int result = updateStmt.executeUpdate();
            if (result > 0) {
                request.setAttribute("successMessage", "Password changed successfully! You can now log in with your new password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to reset password. Please try again.");
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database Error: " + e.getMessage());
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            try { if (checkStmt != null) checkStmt.close(); } catch (SQLException ignored) {}
            try { if (updateStmt != null) updateStmt.close(); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }
    }
}
