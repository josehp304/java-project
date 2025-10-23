package com.adib.expensemanager.view;

import com.adib.expensemanager.ui.Constants;
import com.adib.expensemanager.ui.RoundedPanel;
import com.adib.expensemanager.model.Expense;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import com.adib.expensemanager.ui.CalendarPopup;

/**
 * Dialog for adding new expenses with a modern, theme-aware UI.
 */
public class AddExpenseDialog extends JDialog {
    
    private JTextField descriptionField;
    private JTextField amountField;
    private JComboBox<String> categoryCombo;
    private JTextField dateField;
    private JButton calendarButton;
    private JButton saveButton;
    private JButton cancelButton;
    
    private Expense resultExpense;
    
    public AddExpenseDialog(JFrame parent) {
        super(parent, "Add New Expense", true);
        setSize(450, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        initializeComponents();
        layoutComponents();
        applyTheme();
        
        // Set up button actions
        saveButton.addActionListener(_ -> saveExpense());
        cancelButton.addActionListener(_ -> dispose());
        
        // Set default date to today
        dateField.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        
        // Add Enter key support
        getRootPane().setDefaultButton(saveButton);
        
        // Focus on description field when dialog opens
        descriptionField.requestFocusInWindow();
    }
    
    private void initializeComponents() {
        descriptionField = new JTextField(20);
        descriptionField.setToolTipText("Enter a brief description of your expense");
        
        amountField = new JTextField(20);
        amountField.setToolTipText("Enter the amount (e.g., 25.50)");
        
        dateField = new JTextField(15);
        dateField.setToolTipText("Enter date in YYYY-MM-DD format");
        
        calendarButton = new JButton("📅");
        calendarButton.setToolTipText("Select date from calendar");
        
        // Category options with emojis for better visual appeal
        String[] categories = {
            "🍽️ Food & Dining", "🚗 Transportation", "🛍️ Shopping", "🎬 Entertainment", 
            "🏥 Healthcare", "💡 Bills & Utilities", "✈️ Travel", "📚 Education", 
            "🎁 Gifts & Donations", "📱 Technology", "🏠 Home & Garden", "🎮 Hobbies", 
            "👕 Clothing", "💰 Other"
        };
        categoryCombo = new JComboBox<>(categories);
        categoryCombo.setToolTipText("Select the category that best fits your expense");
        
        saveButton = new JButton("💾 Save Expense");
        cancelButton = new JButton("❌ Cancel");
        
        // Add placeholder text hints
        setupFieldHints();
        
        // Set up calendar button action
        calendarButton.addActionListener(_ -> showCalendarPopup());
    }
    
    private void setupFieldHints() {
        // Add focus listeners to show/hide hints
        descriptionField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (descriptionField.getText().equals("e.g., Coffee at Starbucks")) {
                    descriptionField.setText("");
                    descriptionField.setForeground(Constants.getTextPrimaryColor());
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (descriptionField.getText().isEmpty()) {
                    descriptionField.setText("e.g., Coffee at Starbucks");
                    descriptionField.setForeground(Constants.getTextSecondaryColor());
                }
            }
        });
        
        amountField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (amountField.getText().equals("e.g., 5.50")) {
                    amountField.setText("");
                    amountField.setForeground(Constants.getTextPrimaryColor());
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (amountField.getText().isEmpty()) {
                    amountField.setText("e.g., 5.50");
                    amountField.setForeground(Constants.getTextSecondaryColor());
                }
            }
        });
        
        // Set initial placeholder text
        descriptionField.setText("e.g., Coffee at Starbucks");
        descriptionField.setForeground(Constants.getTextSecondaryColor());
        amountField.setText("e.g., 5.50");
        amountField.setForeground(Constants.getTextSecondaryColor());
    }
    
    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Header panel with title
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBackground(Constants.getBackgroundColor());
        JLabel titleLabel = new JLabel("➕ Add New Expense");
        titleLabel.setFont(Constants.getH2Font());
        titleLabel.setForeground(Constants.getTextPrimaryColor());
        titleLabel.setBorder(BorderFactory.createEmptyBorder(Constants.getMediumSpacing(), 0, Constants.getSmallSpacing(), 0));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Main content panel
        RoundedPanel mainPanel = new RoundedPanel(16);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Constants.getSurfaceColor());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(
            Constants.getLargeSpacing(),
            Constants.getLargeSpacing(),
            Constants.getLargeSpacing(),
            Constants.getLargeSpacing()
        ));
        
        // Form panel with better spacing
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(Constants.getMediumSpacing(), 0, Constants.getMediumSpacing(), Constants.getMediumSpacing());
        gbc.anchor = GridBagConstraints.WEST;
        
        // Description field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createLabel("📝 Description:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        formPanel.add(descriptionField, gbc);
        
        // Amount field
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        formPanel.add(createLabel("💰 Amount ($):"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        formPanel.add(amountField, gbc);
        
        // Category field
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        formPanel.add(createLabel("🏷️ Category:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        formPanel.add(categoryCombo, gbc);
        
        // Date field with calendar button
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        formPanel.add(createLabel("📅 Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        
        // Create a panel for date field and calendar button
        JPanel datePanel = new JPanel(new BorderLayout());
        datePanel.setOpaque(false);
        datePanel.add(dateField, BorderLayout.CENTER);
        datePanel.add(calendarButton, BorderLayout.EAST);
        
        formPanel.add(datePanel, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel with better styling
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, Constants.getMediumSpacing(), 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(Constants.getMediumSpacing(), 0, 0, 0));
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add components to dialog
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(Constants.getLabelFont());
        label.setForeground(Constants.getTextPrimaryColor());
        return label;
    }
    
    private void applyTheme() {
        getContentPane().setBackground(Constants.getBackgroundColor());
        
        // Style text fields
        styleTextField(descriptionField);
        styleTextField(amountField);
        styleTextField(dateField);
        
        // Style combo box
        categoryCombo.setBackground(Constants.getSurfaceColor());
        categoryCombo.setForeground(Constants.getTextPrimaryColor());
        categoryCombo.setFont(Constants.getBodyFont());
        
        // Style buttons
        styleButton(saveButton, Constants.getAccentCoralColor(), Constants.getSurfaceColor());
        styleButton(cancelButton, Constants.getBorderColor(), Constants.getTextPrimaryColor());
        styleCalendarButton(calendarButton);
        
        // Update all labels in the dialog
        updateLabelColors();
        
        // Update the main panel background
        updateMainPanelColors();
    }
    
    private void updateMainPanelColors() {
        // Find and update the main RoundedPanel background
        updateComponentColors(getContentPane());
    }
    
    private void updateLabelColors() {
        // Recursively update all JLabel colors
        updateComponentColors(getContentPane());
    }
    
    private void updateComponentColors(Component component) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            label.setForeground(Constants.getTextPrimaryColor());
        } else if (component instanceof RoundedPanel) {
            RoundedPanel panel = (RoundedPanel) component;
            panel.setBackground(Constants.getSurfaceColor());
        } else if (component instanceof JPanel) {
            JPanel panel = (JPanel) component;
            panel.setBackground(Constants.getBackgroundColor());
        } else if (component instanceof Container) {
            Container container = (Container) component;
            for (Component child : container.getComponents()) {
                updateComponentColors(child);
            }
        }
    }
    
    private void styleTextField(JTextField field) {
        field.setBackground(Constants.getSurfaceColor());
        field.setForeground(Constants.getTextPrimaryColor());
        field.setFont(Constants.getBodyFont());
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Constants.getBorderColor()),
            BorderFactory.createEmptyBorder(Constants.getSmallSpacing(), Constants.getSmallSpacing(), 
                                          Constants.getSmallSpacing(), Constants.getSmallSpacing())
        ));
    }
    
    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(Constants.getLabelFont());
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(
            Constants.getSmallSpacing(), Constants.getMediumSpacing(),
            Constants.getSmallSpacing(), Constants.getMediumSpacing()
        ));
    }
    
    private void styleCalendarButton(JButton button) {
        button.setBackground(Constants.getBorderColor());
        button.setForeground(Constants.getTextPrimaryColor());
        button.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(
            Constants.getSmallSpacing(), Constants.getSmallSpacing(),
            Constants.getSmallSpacing(), Constants.getSmallSpacing()
        ));
        button.setPreferredSize(new Dimension(40, 30));
    }
    
    private void saveExpense() {
        try {
            // Validate description
            String description = descriptionField.getText().trim();
            if (description.isEmpty() || description.equals("e.g., Coffee at Starbucks")) {
                showError("Please enter a description for your expense.");
                descriptionField.requestFocusInWindow();
                return;
            }
            
            if (description.length() > 100) {
                showError("Description must be 100 characters or less.");
                descriptionField.requestFocusInWindow();
                return;
            }
            
            // Validate amount
            String amountText = amountField.getText().trim();
            if (amountText.isEmpty() || amountText.equals("e.g., 5.50")) {
                showError("Please enter an amount for your expense.");
                amountField.requestFocusInWindow();
                return;
            }
            
            BigDecimal amount;
            try {
                amount = new BigDecimal(amountText);
            } catch (NumberFormatException e) {
                showError("Please enter a valid amount (e.g., 25.50).");
                amountField.requestFocusInWindow();
                return;
            }
            
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                showError("Amount must be greater than zero.");
                amountField.requestFocusInWindow();
                return;
            }
            
            if (amount.compareTo(new BigDecimal("999999.99")) > 0) {
                showError("Amount cannot exceed $999,999.99.");
                amountField.requestFocusInWindow();
                return;
            }
            
            // Validate category
            String category = (String) categoryCombo.getSelectedItem();
            if (category == null || category.isEmpty()) {
                showError("Please select a category for your expense.");
                categoryCombo.requestFocusInWindow();
                return;
            }
            
            // Remove emoji from category for storage
            String cleanCategory = category.replaceFirst("^[^\\p{L}\\p{N}]\\s*", "");
            
            // Validate date
            String dateText = dateField.getText().trim();
            if (dateText.isEmpty()) {
                showError("Please enter a date for your expense.");
                dateField.requestFocusInWindow();
                return;
            }
            
            LocalDate date;
            try {
                date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                showError("Please enter a valid date in YYYY-MM-DD format (e.g., 2024-01-15).");
                dateField.requestFocusInWindow();
                return;
            }
            
            // Check if date is in the future
            if (date.isAfter(LocalDate.now())) {
                showError("Expense date cannot be in the future.");
                dateField.requestFocusInWindow();
                return;
            }
            
            // Check if date is too far in the past (optional - 5 years)
            if (date.isBefore(LocalDate.now().minusYears(5))) {
                showError("Expense date cannot be more than 5 years in the past.");
                dateField.requestFocusInWindow();
                return;
            }
            
            resultExpense = new Expense(description, amount, cleanCategory, date);
            dispose();
            
        } catch (Exception e) {
            showError("An unexpected error occurred. Please try again.");
            e.printStackTrace();
        }
    }
    
    private void showCalendarPopup() {
        // Get current date from field or use today's date
        LocalDate initialDate = LocalDate.now();
        try {
            String currentDateText = dateField.getText().trim();
            if (!currentDateText.isEmpty() && !currentDateText.equals("e.g., 2024-01-15")) {
                initialDate = LocalDate.parse(currentDateText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        } catch (Exception e) {
            // Use current date if parsing fails
        }
        
        // Create calendar popup
        CalendarPopup calendarPopup = new CalendarPopup(initialDate, date -> {
            dateField.setText(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            dateField.setForeground(Constants.getTextPrimaryColor());
        });
        
        // Show popup below the calendar button
        calendarPopup.show(calendarButton, 0, calendarButton.getHeight());
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public Expense getResultExpense() {
        return resultExpense;
    }
}
