package assessment.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A JPanel with a background image
 *
 * @author Robert Greener
 * @author Sakeen Zaman
 */
public class JPanelBackground extends JPanel {

    /**
     * The background image of the JPanel
     */
    private Image backgroundImage;

    /**
     * Makes a new JPanel with a background image
     * @param fileName The filename of the background image
     * @throws IOException If the background image is not found
     */
    public JPanelBackground(String fileName) throws IOException {

        //Sets background image to the image at that file name
        backgroundImage = ImageIO.read(new File(fileName));
    }

    /**
     * Draws the background image
     * @param g Needed for super class method
     */
    @Override
    public void paintComponent(Graphics g) {
        //Calls super method
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
