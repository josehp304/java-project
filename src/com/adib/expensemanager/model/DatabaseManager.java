package com.adib.expensemanager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/expense_manager_db";
    private static final String USER = "root"; // Default XAMPP user
    private static final String PASS = "";     // Default XAMPP pass

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // --- C.R.U.D. Operations ---

    // Create
    public boolean addExpense(Expense expense) {
        String sql = "INSERT INTO expenses (description, amount, category, expense_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, expense.getDescription());
            pstmt.setBigDecimal(2, expense.getAmount());
            pstmt.setString(3, expense.getCategory());
            pstmt.setDate(4, java.sql.Date.valueOf(expense.getDate()));
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM expenses ORDER BY expense_date DESC";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Expense expense = new Expense(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getBigDecimal("amount"),
                    rs.getString("category"),
                    rs.getDate("expense_date").toLocalDate()
                );
                expenses.add(expense);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }
    
    // Update
    public boolean updateExpense(Expense expense) {
        String sql = "UPDATE expenses SET description = ?, amount = ?, category = ?, expense_date = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, expense.getDescription());
            pstmt.setBigDecimal(2, expense.getAmount());
            pstmt.setString(3, expense.getCategory());
            pstmt.setDate(4, java.sql.Date.valueOf(expense.getDate()));
            pstmt.setInt(5, expense.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Delete
    public boolean deleteExpense(int expenseId) {
        String sql = "DELETE FROM expenses WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, expenseId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get expense by ID
    public Expense getExpenseById(int expenseId) {
        String sql = "SELECT * FROM expenses WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, expenseId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Expense(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getBigDecimal("amount"),
                    rs.getString("category"),
                    rs.getDate("expense_date").toLocalDate()
                );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Initialize database and create table if it doesn't exist
    public void initializeDatabase() {
        String createTableSql = """
            CREATE TABLE IF NOT EXISTS expenses (
                id INT AUTO_INCREMENT PRIMARY KEY,
                description VARCHAR(255) NOT NULL,
                amount DECIMAL(10,2) NOT NULL,
                category VARCHAR(100) NOT NULL,
                expense_date DATE NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(createTableSql)) {
            
            pstmt.execute();
            System.out.println("Database initialized successfully!");
            
        } catch (SQLException e) {
            System.err.println("Failed to initialize database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}