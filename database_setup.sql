-- Expense Manager Database Setup Script
-- Run this script in your XAMPP phpMyAdmin or MySQL command line

-- Create database (if it doesn't exist)
CREATE DATABASE IF NOT EXISTS expense_manager_db;
USE expense_manager_db;

-- Create expenses table
CREATE TABLE IF NOT EXISTS expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    category VARCHAR(100) NOT NULL,
    expense_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert some sample data (optional)
INSERT INTO expenses (description, amount, category, expense_date) VALUES
('Coffee at Starbucks', 5.50, 'Food & Dining', '2024-01-15'),
('Gas for car', 45.00, 'Transportation', '2024-01-14'),
('Groceries at Walmart', 78.32, 'Food & Dining', '2024-01-13'),
('Movie tickets', 24.00, 'Entertainment', '2024-01-12'),
('Electricity bill', 85.50, 'Bills & Utilities', '2024-01-11'),
('Lunch with friends', 32.75, 'Food & Dining', '2024-01-10'),
('Uber ride', 12.50, 'Transportation', '2024-01-09'),
('Amazon purchase', 67.99, 'Shopping', '2024-01-08'),
('Doctor visit', 150.00, 'Healthcare', '2024-01-07'),
('Gift for mom', 45.00, 'Gifts & Donations', '2024-01-06');

-- Show the created table structure
DESCRIBE expenses;

-- Show sample data
SELECT * FROM expenses ORDER BY expense_date DESC;
