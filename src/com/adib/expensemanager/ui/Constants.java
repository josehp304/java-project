// In package: com.adib.expensemanager.ui
package com.adib.expensemanager.ui;

import java.awt.Color;
import java.awt.Font;

/**
 * Constants class that provides theme-aware colors and styling constants.
 * All colors are now retrieved from ThemeManager to support dark/light mode switching.
 */
public class Constants {
    
    // --- Theme Manager Instance ---
    private static final ThemeManager themeManager = ThemeManager.getInstance();
    
    // --- Colors (now theme-aware) ---
    public static Color getBackgroundColor() { return themeManager.getBackgroundColor(); }
    public static Color getSurfaceColor() { return themeManager.getSurfaceColor(); }
    public static Color getTextPrimaryColor() { return themeManager.getTextPrimaryColor(); }
    public static Color getTextSecondaryColor() { return themeManager.getTextSecondaryColor(); }
    public static Color getBorderColor() { return themeManager.getBorderColor(); }
    public static Color getCardHoverColor() { return themeManager.getCardHoverColor(); }
    
    // Common colors (same for both themes)
    public static Color getPrimarySageColor() { return themeManager.getPrimarySageColor(); }
    public static Color getAccentCoralColor() { return themeManager.getAccentCoralColor(); }
    public static Color getSuccessGreenColor() { return themeManager.getSuccessGreenColor(); }
    public static Color getErrorRedColor() { return themeManager.getErrorRedColor(); }
    
    // Legacy color constants for backward compatibility
    @Deprecated
    public static final Color COLOR_PRIMARY_SAGE = getPrimarySageColor();
    @Deprecated
    public static final Color COLOR_ACCENT_CORAL = getAccentCoralColor();
    @Deprecated
    public static final Color COLOR_BACKGROUND = getBackgroundColor();
    @Deprecated
    public static final Color COLOR_SURFACE = getSurfaceColor();
    @Deprecated 
    public static final Color COLOR_TEXT_PRIMARY = getTextPrimaryColor();
    @Deprecated
    public static final Color COLOR_TEXT_SECONDARY = getTextSecondaryColor();
    @Deprecated
    public static final Color COLOR_BORDER = getBorderColor();

    // --- Fonts ---
    public static Font getH1Font() { return themeManager.getH1Font(); }
    public static Font getH2Font() { return themeManager.getH2Font(); }
    public static Font getBodyFont() { return themeManager.getBodyFont(); }
    public static Font getLabelFont() { return themeManager.getLabelFont(); }
    public static Font getSmallFont() { return themeManager.getSmallFont(); }
    
    // Legacy font constants for backward compatibility
    @Deprecated
    public static final Font FONT_H1 = getH1Font();
    @Deprecated
    public static final Font FONT_H2 = getH2Font();
    @Deprecated
    public static final Font FONT_BODY = getBodyFont();
    @Deprecated
    public static final Font FONT_LABEL = getLabelFont();
    
    // --- Spacing ---
    public static int getMediumSpacing() { return themeManager.getMediumSpacing(); }
    public static int getSmallSpacing() { return themeManager.getSmallSpacing(); }
    public static int getLargeSpacing() { return themeManager.getLargeSpacing(); }
    
    // Legacy spacing constants for backward compatibility
    @Deprecated
    public static final int SPACE_MD = getMediumSpacing();
    @Deprecated
    public static final int SPACE_SM = getSmallSpacing();
}