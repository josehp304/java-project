package com.adib.expensemanager.ui;

import java.awt.Color;
import java.awt.Font;

/**
 * Manages theme switching between light and dark modes.
 * Persists theme preference across application restarts.
 */
public class ThemeManager {
    
    public enum Theme {
        LIGHT, DARK
    }
    
    private static ThemeManager instance;
    private Theme currentTheme = Theme.DARK; // Always use dark theme
    
    // Light theme colors
    private static final Color LIGHT_BACKGROUND = new Color(248, 249, 250);
    private static final Color LIGHT_SURFACE = new Color(255, 255, 255);
    private static final Color LIGHT_TEXT_PRIMARY = new Color(45, 51, 58);
    private static final Color LIGHT_TEXT_SECONDARY = new Color(125, 138, 151);
    private static final Color LIGHT_BORDER = new Color(233, 236, 239);
    private static final Color LIGHT_CARD_HOVER = new Color(245, 247, 250);
    
    // Dark theme colors - Pure black theme
    private static final Color DARK_BACKGROUND = new Color(0, 0, 0); // Pure black
    private static final Color DARK_SURFACE = new Color(20, 20, 20); // Very dark gray
    private static final Color DARK_TEXT_PRIMARY = new Color(255, 255, 255); // White
    private static final Color DARK_TEXT_SECONDARY = new Color(180, 180, 180); // Light gray
    private static final Color DARK_BORDER = new Color(40, 40, 40); // Dark gray
    private static final Color DARK_CARD_HOVER = new Color(30, 30, 30); // Dark gray
    
    // Common colors (same for both themes)
    private static final Color PRIMARY_SAGE = new Color(58, 107, 93);
    private static final Color ACCENT_CORAL = new Color(255, 125, 99);
    private static final Color SUCCESS_GREEN = new Color(76, 175, 80);
    private static final Color ERROR_RED = new Color(244, 67, 54);
    
    private ThemeManager() {
        // Always use dark theme - no preferences needed
    }
    
    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }
    
    public Theme getCurrentTheme() {
        return currentTheme;
    }
    
    public void setTheme(Theme theme) {
        // Theme switching disabled - always dark
        this.currentTheme = Theme.DARK;
    }
    
    public void toggleTheme() {
        // Theme switching disabled - always dark
        this.currentTheme = Theme.DARK;
    }
    
    // Color getters - always return dark theme colors
    public Color getBackgroundColor() {
        return DARK_BACKGROUND;
    }
    
    public Color getSurfaceColor() {
        return DARK_SURFACE;
    }
    
    public Color getTextPrimaryColor() {
        return DARK_TEXT_PRIMARY;
    }
    
    public Color getTextSecondaryColor() {
        return DARK_TEXT_SECONDARY;
    }
    
    public Color getBorderColor() {
        return DARK_BORDER;
    }
    
    public Color getCardHoverColor() {
        return DARK_CARD_HOVER;
    }
    
    // Common colors (same for both themes)
    public Color getPrimarySageColor() {
        return PRIMARY_SAGE;
    }
    
    public Color getAccentCoralColor() {
        return ACCENT_CORAL;
    }
    
    public Color getSuccessGreenColor() {
        return SUCCESS_GREEN;
    }
    
    public Color getErrorRedColor() {
        return ERROR_RED;
    }
    
    // Font getters
    public Font getH1Font() {
        return new Font(Font.SANS_SERIF, Font.BOLD, 24);
    }
    
    public Font getH2Font() {
        return new Font(Font.SANS_SERIF, Font.BOLD, 18);
    }
    
    public Font getBodyFont() {
        return new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    }
    
    public Font getLabelFont() {
        return new Font(Font.SANS_SERIF, Font.BOLD, 12);
    }
    
    public Font getSmallFont() {
        return new Font(Font.SANS_SERIF, Font.PLAIN, 11);
    }
    
    // Spacing constants
    public int getMediumSpacing() {
        return 16;
    }
    
    public int getSmallSpacing() {
        return 8;
    }
    
    public int getLargeSpacing() {
        return 24;
    }
}
