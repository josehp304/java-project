# Database Documentation - Expense Manager Application

This document provides a comprehensive overview of the database schema used in the Expense Manager application.

---

## Table of Contents

1. [Database Overview](#database-overview)
2. [Database Tables](#database-tables)
3. [Table Attributes](#table-attributes)
4. [Entity-Relationship Diagram](#entity-relationship-diagram)
5. [Database Design Layout](#database-design-layout)
6. [Sample Queries](#sample-queries)

---

## Database Overview

| Property | Value |
|----------|-------|
| **Database Name** | `expense_manager_db` |
| **Database Type** | MySQL |
| **Connection Host** | localhost |
| **Default Port** | 3306 |
| **Default Username** | root |
| **Default Password** | (empty) |

**Summary**: The Expense Manager database is designed to store and manage personal expense records. It follows a simple, single-table design optimized for CRUD (Create, Read, Update, Delete) operations on expense entries.

---

## Database Tables

### Table List Summary

| # | Table Name | Description | Records Purpose |
|---|------------|-------------|-----------------|
| 1 | `expenses` | Stores all expense entries | Tracks individual expense transactions including description, amount, category, and date |

---

## Table Attributes

### 1. `expenses` Table

**Purpose**: Primary table for storing all expense records in the application.

**Summary**: This table captures detailed information about each expense entry, including a description of the expense, the monetary amount, categorization for organizing expenses, and the date when the expense occurred. It also maintains automatic timestamps for record creation and updates.

| Column Name | Data Type | Constraints | Description |
|-------------|-----------|-------------|-------------|
| `id` | INT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier for each expense record |
| `description` | VARCHAR(255) | NOT NULL | Brief description of the expense (e.g., "Coffee at Starbucks") |
| `amount` | DECIMAL(10,2) | NOT NULL | Monetary value of the expense with 2 decimal precision |
| `category` | VARCHAR(100) | NOT NULL | Category classification for the expense |
| `expense_date` | DATE | NOT NULL | Date when the expense occurred |
| `created_at` | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | Timestamp when the record was created |
| `updated_at` | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP | Timestamp of last record modification |

#### Attribute Details

**id**
- Type: Integer (Auto-incrementing)
- Purpose: Serves as the primary key for unique identification of each expense entry
- Constraints: Cannot be null, automatically generated

**description**
- Type: Variable-length string (max 255 characters)
- Purpose: Stores a human-readable description of the expense
- Example Values: "Groceries at Walmart", "Gas for car", "Movie tickets"

**amount**
- Type: Decimal with precision (10,2)
- Purpose: Stores the monetary value of the expense
- Format: Supports values up to 99,999,999.99
- Example Values: 5.50, 45.00, 78.32

**category**
- Type: Variable-length string (max 100 characters)
- Purpose: Classifies expenses into predefined categories for organization and reporting
- Predefined Categories Used:
  - Food & Dining
  - Transportation
  - Entertainment
  - Bills & Utilities
  - Shopping
  - Healthcare
  - Gifts & Donations
  - Other

**expense_date**
- Type: DATE
- Purpose: Records the actual date when the expense was incurred
- Format: YYYY-MM-DD

**created_at**
- Type: TIMESTAMP
- Purpose: Automatically records when the expense entry was created
- Default: Current timestamp at insertion

**updated_at**
- Type: TIMESTAMP
- Purpose: Automatically updates when the record is modified
- Default: Current timestamp, updates on modification

---

## Entity-Relationship Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                         expenses                                 │
├─────────────────────────────────────────────────────────────────┤
│  PK   id              INT          AUTO_INCREMENT               │
├─────────────────────────────────────────────────────────────────┤
│       description     VARCHAR(255) NOT NULL                     │
│       amount          DECIMAL(10,2) NOT NULL                    │
│       category        VARCHAR(100) NOT NULL                     │
│       expense_date    DATE         NOT NULL                     │
│       created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP    │
│       updated_at      TIMESTAMP    ON UPDATE CURRENT_TIMESTAMP  │
└─────────────────────────────────────────────────────────────────┘
```

### Table Relationships

Currently, the database uses a **single-table design** with no foreign key relationships. This simple architecture is optimal for:
- Personal expense tracking
- Minimal complexity
- Fast CRUD operations
- Easy maintenance

---

## Database Design Layout

### Physical Schema Diagram

```
┌──────────────────────────────────────────────────────────────────────────┐
│                        expense_manager_db                                 │
│                        (MySQL Database)                                   │
├──────────────────────────────────────────────────────────────────────────┤
│                                                                          │
│   ┌────────────────────────────────────────────────────────────────┐    │
│   │                        expenses                                 │    │
│   │                    (Primary Data Table)                         │    │
│   ├────────────────────────────────────────────────────────────────┤    │
│   │                                                                 │    │
│   │   ┌─────────────┐ ┌─────────────┐ ┌─────────────────────────┐ │    │
│   │   │     id      │ │ description │ │         amount          │ │    │
│   │   │   (PK)      │ │ (VARCHAR)   │ │       (DECIMAL)         │ │    │
│   │   │   INT       │ │  255 chars  │ │        10,2             │ │    │
│   │   └─────────────┘ └─────────────┘ └─────────────────────────┘ │    │
│   │                                                                 │    │
│   │   ┌─────────────┐ ┌─────────────┐ ┌─────────────────────────┐ │    │
│   │   │  category   │ │expense_date │ │       created_at        │ │    │
│   │   │ (VARCHAR)   │ │   (DATE)    │ │      (TIMESTAMP)        │ │    │
│   │   │  100 chars  │ │ YYYY-MM-DD  │ │    Auto-generated       │ │    │
│   │   └─────────────┘ └─────────────┘ └─────────────────────────┘ │    │
│   │                                                                 │    │
│   │   ┌─────────────────────────────────────────────────────────┐ │    │
│   │   │                     updated_at                           │ │    │
│   │   │                    (TIMESTAMP)                           │ │    │
│   │   │              Auto-updated on change                      │ │    │
│   │   └─────────────────────────────────────────────────────────┘ │    │
│   │                                                                 │    │
│   └────────────────────────────────────────────────────────────────┘    │
│                                                                          │
└──────────────────────────────────────────────────────────────────────────┘
```

### Data Flow Diagram

```
┌──────────────────────────────────────────────────────────────────────────┐
│                          APPLICATION LAYER                                │
│                                                                          │
│  ┌────────────────┐    ┌────────────────┐    ┌────────────────────┐     │
│  │    MainFrame   │ -> │  AppController │ -> │  DatabaseManager   │     │
│  │    (UI View)   │    │  (Controller)  │    │     (Model)        │     │
│  └────────────────┘    └────────────────┘    └─────────┬──────────┘     │
│                                                         │                 │
└─────────────────────────────────────────────────────────┼─────────────────┘
                                                          │
                                                          │ JDBC Connection
                                                          │ (MySQL Connector/J)
                                                          ▼
┌──────────────────────────────────────────────────────────────────────────┐
│                          DATABASE LAYER                                   │
│                                                                          │
│  ┌────────────────────────────────────────────────────────────────┐     │
│  │                      MySQL Server                               │     │
│  │                    (XAMPP/localhost:3306)                       │     │
│  │                                                                 │     │
│  │    ┌──────────────────────────────────────────────────────┐   │     │
│  │    │              expense_manager_db                       │   │     │
│  │    │                                                       │   │     │
│  │    │    ┌────────────────────────────────────────────┐   │   │     │
│  │    │    │              expenses TABLE                 │   │   │     │
│  │    │    │                                             │   │   │     │
│  │    │    │  • Store expense records                    │   │   │     │
│  │    │    │  • Full CRUD operations                     │   │   │     │
│  │    │    │  • Category-based organization              │   │   │     │
│  │    │    │  • Date-based ordering                      │   │   │     │
│  │    │    └────────────────────────────────────────────┘   │   │     │
│  │    │                                                       │   │     │
│  │    └──────────────────────────────────────────────────────┘   │     │
│  │                                                                 │     │
│  └────────────────────────────────────────────────────────────────┘     │
│                                                                          │
└──────────────────────────────────────────────────────────────────────────┘
```

### Java Model Mapping

```
┌─────────────────────────────────────────────────────────────────────────┐
│                     Java Model: Expense.java                             │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                          │
│   Database Column          Java Field           Java Type                │
│   ──────────────           ──────────           ─────────                │
│   id                  ->   id                   int                      │
│   description         ->   description          String                   │
│   amount              ->   amount               BigDecimal               │
│   category            ->   category             String                   │
│   expense_date        ->   date                 LocalDate                │
│                                                                          │
│   Note: created_at and updated_at are handled by the database            │
│         and not mapped to the Java model                                 │
│                                                                          │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## Sample Queries

### Create Table
```sql
CREATE TABLE IF NOT EXISTS expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    category VARCHAR(100) NOT NULL,
    expense_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### Insert Record (Create)
```sql
INSERT INTO expenses (description, amount, category, expense_date) 
VALUES ('Coffee at Starbucks', 5.50, 'Food & Dining', '2024-01-15');
```

### Select All Records (Read)
```sql
SELECT * FROM expenses ORDER BY expense_date DESC;
```

### Update Record
```sql
UPDATE expenses 
SET description = ?, amount = ?, category = ?, expense_date = ? 
WHERE id = ?;
```

### Delete Record
```sql
DELETE FROM expenses WHERE id = ?;
```

### Get Record by ID
```sql
SELECT * FROM expenses WHERE id = ?;
```

### Category Summary Query
```sql
SELECT category, SUM(amount) as total, COUNT(*) as count 
FROM expenses 
GROUP BY category 
ORDER BY total DESC;
```

### Monthly Summary Query
```sql
SELECT 
    YEAR(expense_date) as year, 
    MONTH(expense_date) as month, 
    SUM(amount) as total 
FROM expenses 
GROUP BY YEAR(expense_date), MONTH(expense_date) 
ORDER BY year DESC, month DESC;
```

---

## Database Operations (CRUD)

| Operation | Method in DatabaseManager.java | SQL Query |
|-----------|-------------------------------|-----------|
| **Create** | `addExpense(Expense)` | INSERT INTO expenses... |
| **Read (All)** | `getAllExpenses()` | SELECT * FROM expenses ORDER BY expense_date DESC |
| **Read (Single)** | `getExpenseById(int)` | SELECT * FROM expenses WHERE id = ? |
| **Update** | `updateExpense(Expense)` | UPDATE expenses SET ... WHERE id = ? |
| **Delete** | `deleteExpense(int)` | DELETE FROM expenses WHERE id = ? |
| **Initialize** | `initializeDatabase()` | CREATE TABLE IF NOT EXISTS expenses... |

---

## Potential Future Extensions

The current single-table design can be extended to support additional features:

### Suggested Additional Tables

1. **categories** - For managing custom expense categories
   ```
   id, name, description, icon, color, created_at
   ```

2. **users** - For multi-user support
   ```
   id, username, email, password_hash, created_at
   ```

3. **budgets** - For budget tracking
   ```
   id, user_id, category_id, amount, period, start_date, end_date
   ```

4. **recurring_expenses** - For automated recurring entries
   ```
   id, expense_template_id, frequency, next_due_date, is_active
   ```

---

## Notes

- The database uses MySQL with XAMPP server as the default setup
- Connection is configured for localhost with default XAMPP credentials
- All dates are stored in YYYY-MM-DD format
- Monetary amounts use DECIMAL(10,2) for precise financial calculations
- Timestamps are automatically managed by MySQL for auditing purposes

---

*Last Updated: December 2024*
*Version: 1.0*
