/**
 * 
 */
/**
 * 
 */
/*
 * This module name 'expensemanager' is a simple example.
 * If your root package is com.yourname.expensemanager,
 * you would typically name the module: com.yourname.expensemanager
 */
module com.adib.expensemanager {
    
    // 1. For Java Swing & AWT (UI components, windows, events)
    requires java.desktop;
    
    // 2. For JDBC (Connection, PreparedStatement, ResultSet)
    requires java.sql;
    
    // 3. For the MySQL Driver
    // Note: You need to download mysql-connector-java-8.0.33.jar 
    // and add it to your project's classpath or module path
    // For now, we'll comment this out - you'll need to add the JAR manually
    // requires mysql.connector.j;
    
    /*
     * 4. (Optional) You only need 'opens' if you get reflection errors.
     * For example, if a JTable or JList can't access your 'Expense' class.
     * If you get an 'Illegal reflective access' warning, add this:
     */
    // opens com.yourname.expensemanager.model;
}