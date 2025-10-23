// In package: com.yourname.expensemanager.main
package com.adib.expensemanager.main;

import javax.swing.SwingUtilities;
import com.adib.expensemanager.controller.AppController;
import com.adib.expensemanager.model.DatabaseManager;
import com.adib.expensemanager.view.MainFrame;

public class App {
    
    public static void main(String[] args) {
        // !! CRITICAL: Run all Swing code on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            
            // 1. Create the Model
            DatabaseManager model = new DatabaseManager();
            
            // 2. Create the View
            MainFrame view = new MainFrame();
            
            // 3. Create the Controller (which links model and view)
            new AppController(model, view);
            
            // 4. Make the app visible
            view.setVisible(true);
        });
    }
}