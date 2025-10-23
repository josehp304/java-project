# Expense Manager App

A modern Java Swing application for managing personal expenses with dark/light mode support and MySQL database integration.

## Features

- 🎨 **Modern UI**: Clean, card-based design with rounded corners
- 🌙 **Dark/Light Mode**: Toggle between themes with persistence
- 💾 **Database Integration**: Full CRUD operations with XAMPP MySQL
- 📊 **Expense Categories**: Pre-defined categories for better organization
- 📅 **Date Management**: Easy date selection and display
- 💰 **Amount Tracking**: Precise decimal amount handling

## Prerequisites

1. **Java Development Kit (JDK) 11 or higher**
2. **XAMPP** with MySQL server running
3. **Eclipse IDE** (or any Java IDE)

## Setup Instructions

### 1. Database Setup

1. Start XAMPP and ensure MySQL is running
2. Open phpMyAdmin (http://localhost/phpmyadmin)
3. Run the SQL script provided in `database_setup.sql`:
   ```sql
   -- Create database
   CREATE DATABASE IF NOT EXISTS expense_manager_db;
   USE expense_manager_db;
   
   -- Create table
   CREATE TABLE IF NOT EXISTS expenses (
       id INT AUTO_INCREMENT PRIMARY KEY,
       description VARCHAR(255) NOT NULL,
       amount DECIMAL(10,2) NOT NULL,
       category VARCHAR(100) NOT NULL,
       expense_date DATE NOT NULL,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   ```

### 2. MySQL Connector Setup

1. Download MySQL Connector/J from: https://dev.mysql.com/downloads/connector/j/
2. Extract the JAR file (e.g., `mysql-connector-java-8.0.33.jar`)
3. Add the JAR to your project:
   - **Eclipse**: Right-click project → Properties → Java Build Path → Libraries → Add External JARs
   - **Command Line**: Add to classpath when compiling/running

### 3. Database Configuration

The app is configured to connect to:
- **Host**: localhost
- **Port**: 3306
- **Database**: expense_manager_db
- **Username**: root
- **Password**: (empty - default XAMPP setup)

If your XAMPP setup is different, modify the connection details in `DatabaseManager.java`:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/expense_manager_db";
private static final String USER = "root";
private static final String PASS = "";
```

### 4. Running the Application

1. Compile the project:
   ```bash
   javac -cp "path/to/mysql-connector-java-8.0.33.jar" -d bin src/**/*.java
   ```

2. Run the application:
   ```bash
   java -cp "bin:path/to/mysql-connector-java-8.0.33.jar" com.adib.expensemanager.main.App
   ```

   Or simply run the `App.java` file from your IDE.

## Project Structure

```
src/
├── com/adib/expensemanager/
│   ├── controller/
│   │   └── AppController.java          # Main application controller
│   ├── main/
│   │   └── App.java                    # Application entry point
│   ├── model/
│   │   ├── DatabaseManager.java        # Database operations
│   │   └── Expense.java                # Expense data model
│   ├── ui/
│   │   ├── Constants.java              # Theme-aware constants
│   │   ├── ThemeManager.java           # Dark/light mode management
│   │   ├── ExpenseCellRenderer.java    # Custom list cell renderer
│   │   └── RoundedPanel.java           # Custom rounded panel component
│   └── view/
│       ├── MainFrame.java              # Main application window
│       ├── DashboardPanel.java         # Expense list display
│       └── AddExpenseDialog.java       # Add expense dialog
└── module-info.java                    # Java module configuration
```

## Usage

1. **Launch the application** - The main window will open with the dashboard
2. **Add expenses** - Click the "+ Add Expense" button to open the dialog
3. **Fill in details**:
   - Description: Brief description of the expense
   - Amount: Enter the cost (e.g., 25.50)
   - Category: Select from predefined categories
   - Date: Enter in YYYY-MM-DD format (defaults to today)
4. **Save** - Click "Save Expense" to add to the database
5. **Toggle theme** - Click the moon/sun icon to switch between dark and light modes
6. **View expenses** - All expenses are displayed in chronological order

## Theme System

The application features a comprehensive theme system:

- **Light Mode**: Clean, bright interface with subtle shadows
- **Dark Mode**: Dark interface with high contrast for low-light usage
- **Persistence**: Theme preference is saved and restored between sessions
- **Real-time switching**: No restart required to change themes

## Database Operations

The application supports full CRUD operations:

- **Create**: Add new expenses through the dialog
- **Read**: Display all expenses in the dashboard
- **Update**: (Future feature - edit existing expenses)
- **Delete**: (Future feature - remove expenses)

## Troubleshooting

### Common Issues

1. **Database Connection Error**:
   - Ensure XAMPP MySQL is running
   - Check database name and credentials in `DatabaseManager.java`
   - Verify the `expense_manager_db` database exists

2. **MySQL Connector Error**:
   - Ensure the MySQL connector JAR is in your classpath
   - Download the correct version for your Java version

3. **Module Path Issues**:
   - If using modules, uncomment the MySQL connector requirement in `module-info.java`
   - Or run without module path: `java --class-path` instead of `--module-path`

### Database Connection Test

You can test the database connection by running this simple test:

```java
import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/expense_manager_db", 
                "root", 
                ""
            );
            System.out.println("Database connection successful!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}
```

## Future Enhancements

- Edit/Delete expense functionality
- Expense statistics and charts
- Export to CSV/PDF
- Budget tracking and alerts
- Multiple user support
- Mobile app companion

## License

This project is open source and available under the MIT License.
