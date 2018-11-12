package assessment.models;

import javax.swing.*;
import java.io.IOException;
import java.util.HashSet;

/**
 * This is the boat the goes across the river
 *
 * @author Robert Greener
 * @author Sakeen Zaman
 */
public class Boat extends Location {
    /**
     * The image of the boat
     */
    private ImageIcon image;

    /**
     * The score
     */
    private int score;

    /**
     * Makes a new boat
     * @param items A HashSet containing the initial Items on the boat
     * @param maxNumberOfItems The max number of Items the boat can take
     * @param side The initial side of the boat
     * @param filename The filename of the image of the boat
     * @throws IOException If the image is not found
     */
    public Boat(HashSet<Item> items, int maxNumberOfItems, Directions side, String filename) throws IOException {
        super(items, maxNumberOfItems, side);
        //Creates a new ImageIcon with the supplied name
        this.image = new ImageIcon(filename);
        //Sets initial score to 0
        score = 0;
    }

    /**
     * Makes a new empty boat
     * @param maxNumberOfItems The max number of Items the boat can take
     * @param side The initial side of the boat
     * @param filename The filename of the image of the boat
     * @throws IOException If the image is not found
     */
    public Boat(int maxNumberOfItems, Directions side, String filename) throws IOException {
        super(maxNumberOfItems, side);

        //Creates a new ImageIcon with the supplied name
        this.image = new ImageIcon(filename);

        //Sets initial score to 0
        score = 0;
    }

    /**
     * Changes the side of the boat
     * @param side The new side
     */
    public void setSide(Directions side) {
        super.setSide(side);
    }

    /**
     * Gets the boat's image
     * @return Returns the boat's image
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * Gets the score
     * @return Returns the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Decrements the score by 1
     */
    public void decrementScore() {
        score--;
    }
}
