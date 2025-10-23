// In package: com.yourname.expensemanager.model
package com.adib.expensemanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    private int id;
    private String description;
    private BigDecimal amount;
    private String category;
    private LocalDate date;

    // --- Constructors ---
    public Expense(String description, BigDecimal amount, String category, LocalDate date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
    
    // Constructor with ID for database operations
    public Expense(int id, String description, BigDecimal amount, String category, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
    
    // ... add another constructor that includes 'id' for fetching from DB ...
    
    // --- Getters and Setters ---
    
    // (Right-click > Source > Generate Getters and Setters...)
}