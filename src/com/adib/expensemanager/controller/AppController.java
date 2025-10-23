package com.adib.expensemanager.controller;

import com.adib.expensemanager.model.DatabaseManager;
import com.adib.expensemanager.model.Expense;
import com.adib.expensemanager.view.MainFrame;
import com.adib.expensemanager.view.AddExpenseDialog;
import java.util.List;
import javax.swing.JOptionPane;

public class AppController {
 
 private DatabaseManager model;
 private MainFrame view;

 public AppController(DatabaseManager model, MainFrame view) {
     this.model = model;
     this.view = view;
     
     // Initialize database
     model.initializeDatabase();
     
     // Add all listeners
     addListeners();
     
     // Load initial data
     refreshDashboard();
 }
 
 private void addListeners() {
     // Listen for clicks on the "+ Add Expense" button
     this.view.getAddExpenseButton().addActionListener(_ -> showAddExpenseDialog());
 }
 
 private void showAddExpenseDialog() {
     AddExpenseDialog dialog = new AddExpenseDialog(this.view);
     dialog.setVisible(true);
     
     // Get the result from the dialog
     Expense newExpense = dialog.getResultExpense();
     if (newExpense != null) {
         saveNewExpense(newExpense);
     }
 }
 
 public void saveNewExpense(Expense expense) {
     if (model.addExpense(expense)) {
         // Success! Refresh the dashboard
         refreshDashboard();
         JOptionPane.showMessageDialog(view, "Expense added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
     } else {
         JOptionPane.showMessageDialog(view, "Failed to add expense. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
     }
 }
 
 public void refreshDashboard() {
     List<Expense> expenses = model.getAllExpenses();
     view.getDashboardPanel().updateExpenseList(expenses);
 }
}