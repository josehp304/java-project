package com.adib.expensemanager.view ;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import com.adib.expensemanager.ui.Constants;

public class MainFrame extends JFrame {

    private DashboardPanel dashboardPanel;
    private JButton addExpenseButton;

    public MainFrame() {
        setTitle("Flow Expense Manager");
        setSize(new Dimension(900, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        // Main content pane uses BorderLayout, which is good.
        setLayout(new BorderLayout());
        
        // Set the "empty space" background color
        getContentPane().setBackground(Constants.getBackgroundColor());

        // --- 1. CREATE THE HEADER ---
        // Create a new panel just for the header
        JPanel headerPanel = new JPanel();
        
        // Use a BorderLayout *for the header panel itself*
        headerPanel.setLayout(new BorderLayout()); 
        
        // Set the header's background to be the "card" color
        headerPanel.setBackground(Constants.getSurfaceColor());
        
        // Add padding to the header so it's not on the edges
        headerPanel.setBorder(BorderFactory.createEmptyBorder(
            Constants.getMediumSpacing(), 
            Constants.getMediumSpacing(), 
            Constants.getMediumSpacing(), 
            Constants.getMediumSpacing()
        ));
        
        // --- 2. CREATE THE TITLE LABEL ---
        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(Constants.getH1Font());
        titleLabel.setForeground(Constants.getTextPrimaryColor());
        headerPanel.add(titleLabel, BorderLayout.WEST); // Add to the left

        // --- 3. CREATE AND STYLE THE ADD EXPENSE BUTTON ---
        addExpenseButton = new JButton("+ Add Expense");
        
        // !! APPLY THE "FLOW" STYLES !!
        addExpenseButton.setBackground(Constants.getAccentCoralColor());
        addExpenseButton.setForeground(Constants.getSurfaceColor());
        addExpenseButton.setFont(Constants.getLabelFont());
        addExpenseButton.setFocusPainted(false);
        addExpenseButton.setBorder(BorderFactory.createEmptyBorder(
            Constants.getSmallSpacing(), // 8px top/bottom padding
            Constants.getMediumSpacing(), // 16px left/right padding
            Constants.getSmallSpacing(), 
            Constants.getMediumSpacing()
        ));
        
        headerPanel.add(addExpenseButton, BorderLayout.EAST); // Add button to the right

        // --- 4. ADD HEADER TO THE MAINFRAME ---
        // Add our fully-built headerPanel to the "NORTH" (top)
        add(headerPanel, BorderLayout.NORTH);
        
        // --- 5. ADD DASHBOARD TO THE MAINFRAME ---
        dashboardPanel = new DashboardPanel();
        // Add the main content to the "CENTER"
        add(dashboardPanel,BorderLayout.CENTER);
    }
    
    // --- Getters ---
    // (Used by the Controller to add ActionListeners)
    public JButton getAddExpenseButton() {
        return addExpenseButton;
    }
    
    public DashboardPanel getDashboardPanel() {
        return dashboardPanel;
    }
}