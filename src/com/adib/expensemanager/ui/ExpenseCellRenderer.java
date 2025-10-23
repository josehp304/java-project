// In package: com.yourname.expensemanager.ui
package com.adib.expensemanager.ui;

import com.adib.expensemanager.model.Expense;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 * Custom renderer to display an Expense object in a JList as a "Flow" card.
 */
public class ExpenseCellRenderer implements ListCellRenderer<Expense> {

    private RoundedPanel mainPanel;
    private JLabel descriptionLabel;
    private JLabel amountLabel;
    private JLabel categoryLabel;
    private JLabel dateLabel;

    public ExpenseCellRenderer() {
        // --- 1. Create the main card panel ---
        // We use our RoundedPanel, but with a smaller radius
        mainPanel = new RoundedPanel(12); 
        mainPanel.setLayout(new BorderLayout(Constants.getSmallSpacing(), Constants.getSmallSpacing()));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(
            Constants.getSmallSpacing(), 
            Constants.getMediumSpacing(), 
            Constants.getSmallSpacing(), 
            Constants.getMediumSpacing()
        ));

        // --- 2. Create the labels ---
        descriptionLabel = new JLabel();
        descriptionLabel.setFont(Constants.getH2Font());
        descriptionLabel.setForeground(Constants.getTextPrimaryColor());

        amountLabel = new JLabel();
        amountLabel.setFont(Constants.getH2Font());
        amountLabel.setForeground(Constants.getPrimarySageColor()); // Highlight amount
        amountLabel.setHorizontalAlignment(JLabel.RIGHT); // Align to the right

        // --- 3. Create a sub-panel for category and date ---
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2)); // 1 row, 2 cols
        bottomPanel.setOpaque(false); // Make it transparent

        categoryLabel = new JLabel();
        categoryLabel.setFont(Constants.getLabelFont());
        categoryLabel.setForeground(Constants.getTextSecondaryColor());
        
        dateLabel = new JLabel();
        dateLabel.setFont(Constants.getLabelFont());
        dateLabel.setForeground(Constants.getTextSecondaryColor());
        dateLabel.setHorizontalAlignment(JLabel.RIGHT);

        bottomPanel.add(categoryLabel);
        bottomPanel.add(dateLabel);

        // --- 4. Create a top panel for description and amount ---
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(descriptionLabel, BorderLayout.WEST);
        topPanel.add(amountLabel, BorderLayout.EAST);
        
        // --- 5. Add all components to the main card panel ---
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Expense> list, 
                                                  Expense expense, 
                                                  int index, 
                                                  boolean isSelected, 
                                                  boolean cellHasFocus) {
                                                      
        // --- 5. Set the data for this specific row ---
        // We get the 'expense' object for the current row and
        // put its data into our labels.
        descriptionLabel.setText(expense.getDescription());
        amountLabel.setText(String.format("$%.2f", expense.getAmount()));
        categoryLabel.setText(expense.getCategory());
        dateLabel.setText(expense.getDate().toString()); // You can format this better later

        // --- 6. Update colors based on current theme ---
        descriptionLabel.setForeground(Constants.getTextPrimaryColor());
        amountLabel.setForeground(Constants.getPrimarySageColor());
        categoryLabel.setForeground(Constants.getTextSecondaryColor());
        dateLabel.setForeground(Constants.getTextSecondaryColor());
        
        // --- 7. Handle selection colors ---
        // This is key for good UX
        if (isSelected) {
            // Set background for a selected item
            mainPanel.setBackground(Constants.getCardHoverColor()); 
        } else {
            // Set background for a normal (unselected) item
            mainPanel.setBackground(Constants.getSurfaceColor()); 
        }

        return mainPanel;
    }
}