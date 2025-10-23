package com.adib.expensemanager.test;

import com.adib.expensemanager.model.DatabaseManager;
import com.adib.expensemanager.model.Expense;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Simple test class to verify database connectivity and basic operations.
 * Run this to test your database setup before running the main application.
 */
public class DatabaseTest {
    
    public static void main(String[] args) {
        System.out.println("Testing Expense Manager Database Connection...");
        
        DatabaseManager dbManager = new DatabaseManager();
        
        try {
            // Test 1: Initialize database
            System.out.println("1. Initializing database...");
            dbManager.initializeDatabase();
            System.out.println("✓ Database initialization successful!");
            
            // Test 2: Add a test expense
            System.out.println("2. Adding test expense...");
            Expense testExpense = new Expense(
                "Test Expense - Coffee", 
                new BigDecimal("4.50"), 
                "Food & Dining", 
                LocalDate.now()
            );
            
            boolean added = dbManager.addExpense(testExpense);
            if (added) {
                System.out.println("✓ Test expense added successfully!");
            } else {
                System.out.println("✗ Failed to add test expense");
                return;
            }
            
            // Test 3: Retrieve all expenses
            System.out.println("3. Retrieving all expenses...");
            var expenses = dbManager.getAllExpenses();
            System.out.println("✓ Retrieved " + expenses.size() + " expenses from database");
            
            // Display first few expenses
            System.out.println("\nFirst few expenses:");
            for (int i = 0; i < Math.min(3, expenses.size()); i++) {
                Expense exp = expenses.get(i);
                System.out.printf("  - %s: $%.2f (%s) on %s\n", 
                    exp.getDescription(), 
                    exp.getAmount(), 
                    exp.getCategory(), 
                    exp.getDate()
                );
            }
            
            System.out.println("\n🎉 All database tests passed! Your setup is ready.");
            System.out.println("You can now run the main Expense Manager application.");
            
        } catch (Exception e) {
            System.err.println("❌ Database test failed:");
            System.err.println("Error: " + e.getMessage());
            System.err.println("\nTroubleshooting:");
            System.err.println("1. Make sure XAMPP MySQL is running");
            System.err.println("2. Check that the 'expense_manager_db' database exists");
            System.err.println("3. Verify MySQL connector JAR is in your classpath");
            System.err.println("4. Check database credentials in DatabaseManager.java");
            e.printStackTrace();
        }
    }
}
