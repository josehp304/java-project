// In package: com.adib.expensemanager.view
package com.adib.expensemanager.view;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import com.adib.expensemanager.model.Expense;
import com.adib.expensemanager.ui.Constants;
import com.adib.expensemanager.ui.ExpenseCellRenderer; // <-- IMPORT ADDED
import java.util.List;
import java.awt.BorderLayout; 
import javax.swing.BorderFactory;

@SuppressWarnings("serial")
public class DashboardPanel extends JPanel {

    private JList<Expense> expenseList;
    private DefaultListModel<Expense> expenseListModel;

    public DashboardPanel() {
        // --- FIX 1: Set the layout for this panel ---
        // This tells the panel how to arrange components *inside* itself
        setLayout(new BorderLayout()); 
        
        // --- FIX 2: Set the main background color ---
        setBackground(Constants.getBackgroundColor()); 
        
        // --- FIX 3: Add "breathing room" (padding) ---
        // This adds a 16px border around the *outside* of this panel
        setBorder(BorderFactory.createEmptyBorder(
            Constants.getMediumSpacing(), 
            Constants.getMediumSpacing(), 
            Constants.getMediumSpacing(), 
            Constants.getMediumSpacing()
        ));

        // ... (your code to init the JList)
        
        // --- FIX 4: Add your JList (in a scroll pane) ---
        // Add it to the CENTER of *this* panel's BorderLayout
        expenseListModel = new DefaultListModel<>();
        expenseList = new JList<>(expenseListModel);
        
        // --- !! CODE ADDED !! ---
        // Tell the JList to use our new custom renderer
        expenseList.setCellRenderer(new ExpenseCellRenderer());
        
        // (Optional) Add some spacing between list items
        expenseList.setFixedCellHeight(90); // Set a fixed height for each card
        expenseList.setBackground(Constants.getBackgroundColor()); // Match the panel background
        // --- !! END OF ADDED CODE !! ---
        
        // Add the list to a JScrollPane so you can scroll
        JScrollPane scrollPane = new JScrollPane(expenseList);
        
        // --- !! CODE MODIFIED !! ---
        // Make the scroll pane's border invisible
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); 
        scrollPane.getViewport().setBackground(Constants.getBackgroundColor());
        
        // Add the scroll pane to the panel
        add(scrollPane, BorderLayout.CENTER); 
    }
    
    // This method will be called by the Controller
    public void updateExpenseList(List<Expense> expenses) {
        expenseListModel.clear();
        for (Expense exp : expenses) {
            expenseListModel.addElement(exp);
        }
    }
    
}