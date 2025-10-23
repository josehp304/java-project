package com.adib.expensemanager.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * A custom calendar popup component that displays a visual calendar grid.
 */
public class CalendarPopup extends JPopupMenu {
    
    private LocalDate selectedDate;
    private LocalDate currentDate;
    private JLabel monthYearLabel;
    private JPanel calendarPanel;
    private CalendarPopupListener listener;
    private JComboBox<String> monthCombo;
    private JComboBox<Integer> yearCombo;
    
    public interface CalendarPopupListener {
        void dateSelected(LocalDate date);
    }
    
    public CalendarPopup(LocalDate initialDate, CalendarPopupListener listener) {
        this.selectedDate = initialDate;
        this.currentDate = initialDate;
        this.listener = listener;
        
        setBackground(Constants.getSurfaceColor());
        setBorder(BorderFactory.createLineBorder(Constants.getBorderColor(), 1));
        
        initializeComponents();
        layoutComponents();
    }
    
    private void initializeComponents() {
        // Month/Year navigation
        monthYearLabel = new JLabel();
        monthYearLabel.setFont(Constants.getLabelFont());
        monthYearLabel.setForeground(Constants.getTextPrimaryColor());
        monthYearLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // Calendar grid
        calendarPanel = new JPanel(new GridLayout(0, 7, 2, 2));
        calendarPanel.setBackground(Constants.getSurfaceColor());
        
        updateCalendar();
    }
    
    private void layoutComponents() {
        setLayout(new BorderLayout());
        
        // Header with navigation
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Calendar grid
        add(calendarPanel, BorderLayout.CENTER);
        
        // Today button
        JButton todayButton = new JButton("Today");
        todayButton.setBackground(Constants.getAccentCoralColor());
        todayButton.setForeground(Constants.getSurfaceColor());
        todayButton.setFont(Constants.getSmallFont());
        todayButton.setFocusPainted(false);
        todayButton.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        todayButton.addActionListener(e -> selectDate(LocalDate.now()));
        
        JPanel footerPanel = new JPanel(new FlowLayout());
        footerPanel.setOpaque(false);
        footerPanel.add(todayButton);
        
        add(footerPanel, BorderLayout.SOUTH);
        
        // Set size
        setPreferredSize(new Dimension(320, 360));
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Constants.getSurfaceColor());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        
        // Left navigation buttons
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        leftPanel.setOpaque(false);
        
        // Previous year button
        JButton prevYearButton = new JButton("◀◀");
        styleNavButton(prevYearButton);
        prevYearButton.setToolTipText("Previous Year");
        prevYearButton.addActionListener(e -> {
            currentDate = currentDate.minusYears(1);
            updateCalendar();
        });
        
        // Previous month button
        JButton prevButton = new JButton("◀");
        styleNavButton(prevButton);
        prevButton.setToolTipText("Previous Month");
        prevButton.addActionListener(e -> {
            currentDate = currentDate.minusMonths(1);
            updateCalendar();
        });
        
        leftPanel.add(prevYearButton);
        leftPanel.add(prevButton);
        
        // Center panel with month/year dropdowns
        JPanel centerPanel = createMonthYearPanel();
        
        // Right navigation buttons
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        rightPanel.setOpaque(false);
        
        // Next month button
        JButton nextButton = new JButton("▶");
        styleNavButton(nextButton);
        nextButton.setToolTipText("Next Month");
        nextButton.addActionListener(e -> {
            currentDate = currentDate.plusMonths(1);
            updateCalendar();
        });
        
        // Next year button
        JButton nextYearButton = new JButton("▶▶");
        styleNavButton(nextYearButton);
        nextYearButton.setToolTipText("Next Year");
        nextYearButton.addActionListener(e -> {
            currentDate = currentDate.plusYears(1);
            updateCalendar();
        });
        
        rightPanel.add(nextButton);
        rightPanel.add(nextYearButton);
        
        headerPanel.add(leftPanel, BorderLayout.WEST);
        headerPanel.add(centerPanel, BorderLayout.CENTER);
        headerPanel.add(rightPanel, BorderLayout.EAST);
        
        return headerPanel;
    }
    
    private void styleNavButton(JButton button) {
        button.setBackground(Constants.getBorderColor());
        button.setForeground(Constants.getTextPrimaryColor());
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(30, 25));
    }
    
    private JPanel createMonthYearPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);
        
        // Month dropdown
        monthCombo = new JComboBox<>();
        String[] months = {"January", "February", "March", "April", "May", "June",
                          "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            monthCombo.addItem(month);
        }
        monthCombo.setSelectedIndex(currentDate.getMonthValue() - 1);
        monthCombo.setBackground(Constants.getSurfaceColor());
        monthCombo.setForeground(Constants.getTextPrimaryColor());
        monthCombo.setFont(Constants.getSmallFont());
        monthCombo.setFocusable(false); // Prevent focus issues
        monthCombo.addActionListener(e -> {
            int selectedMonth = monthCombo.getSelectedIndex() + 1;
            currentDate = currentDate.withMonth(selectedMonth);
            updateCalendar();
        });
        
        // Year dropdown
        yearCombo = new JComboBox<>();
        int currentYear = LocalDate.now().getYear();
        for (int year = currentYear - 10; year <= currentYear + 10; year++) {
            yearCombo.addItem(year);
        }
        yearCombo.setSelectedItem(currentDate.getYear());
        yearCombo.setBackground(Constants.getSurfaceColor());
        yearCombo.setForeground(Constants.getTextPrimaryColor());
        yearCombo.setFont(Constants.getSmallFont());
        yearCombo.setFocusable(false); // Prevent focus issues
        yearCombo.addActionListener(e -> {
            Integer selectedYear = (Integer) yearCombo.getSelectedItem();
            if (selectedYear != null) {
                currentDate = currentDate.withYear(selectedYear);
                updateCalendar();
            }
        });
        
        panel.add(monthCombo);
        panel.add(new JLabel(" ")); // Spacer
        panel.add(yearCombo);
        
        return panel;
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            // Prevent popup from closing when dropdowns are clicked
            addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
                @Override
                public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {}
                
                @Override
                public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}
                
                @Override
                public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}
            });
        }
    }
    
    private void updateCalendar() {
        calendarPanel.removeAll();
        
        // Update month/year label
        String monthYear = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + currentDate.getYear();
        monthYearLabel.setText(monthYear);
        
        // Update dropdown selections if they exist
        if (monthCombo != null) {
            monthCombo.setSelectedIndex(currentDate.getMonthValue() - 1);
        }
        if (yearCombo != null) {
            yearCombo.setSelectedItem(currentDate.getYear());
        }
        
        // Day headers
        String[] dayHeaders = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : dayHeaders) {
            JLabel dayLabel = new JLabel(day, JLabel.CENTER);
            dayLabel.setFont(Constants.getSmallFont());
            dayLabel.setForeground(Constants.getTextSecondaryColor());
            dayLabel.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
            calendarPanel.add(dayLabel);
        }
        
        // Get first day of month and number of days
        LocalDate firstDay = currentDate.withDayOfMonth(1);
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue() % 7; // Sunday = 0
        int daysInMonth = currentDate.lengthOfMonth();
        
        // Add empty cells for days before the first day of the month
        for (int i = 0; i < firstDayOfWeek; i++) {
            calendarPanel.add(new JLabel());
        }
        
        // Add day buttons
        LocalDate today = LocalDate.now();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = currentDate.withDayOfMonth(day);
            JButton dayButton = createDayButton(date, day);
            
            // Highlight today
            if (date.equals(today)) {
                dayButton.setBackground(Constants.getAccentCoralColor());
                dayButton.setForeground(Constants.getSurfaceColor());
            }
            
            // Highlight selected date
            if (date.equals(selectedDate)) {
                dayButton.setBorder(BorderFactory.createLineBorder(Constants.getPrimarySageColor(), 2));
            }
            
            calendarPanel.add(dayButton);
        }
        
        calendarPanel.revalidate();
        calendarPanel.repaint();
    }
    
    private JButton createDayButton(LocalDate date, int day) {
        JButton button = new JButton(String.valueOf(day));
        button.setFont(Constants.getSmallFont());
        button.setForeground(Constants.getTextPrimaryColor());
        button.setBackground(Constants.getSurfaceColor());
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        
        button.addActionListener(e -> selectDate(date));
        
        return button;
    }
    
    private void selectDate(LocalDate date) {
        selectedDate = date;
        if (listener != null) {
            listener.dateSelected(date);
        }
        setVisible(false);
    }
    
    public LocalDate getSelectedDate() {
        return selectedDate;
    }
}
