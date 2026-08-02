-- Create Database
CREATE DATABASE IF NOT EXISTS smart_id_db;
USE smart_id_db;

-- 1. Students Table
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    roll_number VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Admin Table
CREATE TABLE IF NOT EXISTS admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- 3. Lost ID Reports Table
CREATE TABLE IF NOT EXISTS lost_reports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    roll_number VARCHAR(50) NOT NULL,
    student_name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    lost_location VARCHAR(150) NOT NULL,
    description TEXT,
    report_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Pending',
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE SET NULL
);

-- 4. Found ID Reports Table
CREATE TABLE IF NOT EXISTS found_reports (
    id INT AUTO_INCREMENT PRIMARY KEY,
    finder_name VARCHAR(100) NOT NULL,
    roll_number VARCHAR(50),
    student_name VARCHAR(100),
    department VARCHAR(100),
    found_location VARCHAR(150) NOT NULL,
    description TEXT,
    found_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'Pending'
);

-- Seed Initial Data
INSERT INTO admin (username, password) VALUES ('admin', 'admin123') ON DUPLICATE KEY UPDATE username=username;
