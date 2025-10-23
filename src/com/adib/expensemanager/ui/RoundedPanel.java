// In package: com.yourname.expensemanager.ui
package com.adib.expensemanager.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 * A custom JPanel that paints a rounded rectangle background.
 * This is used to create the "card" components for the "Flow" design system.
 */
public class RoundedPanel extends JPanel {

    /** The arc width and height for the rounded corners. */
    private int cornerRadius;

    /**
     * Creates a new RoundedPanel with a default corner radius of 20.
     */
    public RoundedPanel() {
        super();
        this.cornerRadius = 20; // Default radius
        
        // This is crucial! We are painting our own background,
        // so we must tell the JPanel not to paint its default one.
        setOpaque(false);
    }
    
    /**
     * Creates a new RoundedPanel with a specified corner radius.
     * @param cornerRadius The radius (in pixels) for the corners.
     */
    public RoundedPanel(int cornerRadius) {
        super();
        this.cornerRadius = cornerRadius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Create a copy of the Graphics object to not interfere with other painting
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Turn on anti-aliasing for smooth, high-quality edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // --- Paint the Rounded Background ---
        // We use the panel's getBackground() color
        g2.setColor(getBackground());
        
        // Create the shape of the rounded rectangle
        Shape clipShape = new RoundRectangle2D.Float(
            0, 0, 
            getWidth() - 1, getHeight() - 1, 
            cornerRadius, cornerRadius
        );
        
        // Fill the shape
        g2.fill(clipShape);
        
        // Dispose of the graphics copy
        g2.dispose();

        // !! IMPORTANT !!
        // Call super.paintComponent(g) AFTER drawing the background.
        // This ensures that all children components (JLabels, JButtons, etc.)
        // inside this panel are painted correctly on top of our
        // custom background.
        super.paintComponent(g);
    }
    
    // (Optional but helpful) Override setOpaque to make sure it stays false
    @Override
    public void setOpaque(boolean isOpaque) {
        // We always want to be non-opaque (transparent)
        // so we can paint our own background.
        super.setOpaque(false);
    }
}