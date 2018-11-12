package assessment.models;

import javax.swing.*;
import java.io.IOException;

/**
 * Represents one item in the puzzle
 *
 * @author Robert Greener
 * @author Sakeen Zaman
 */
public class Item {

    /**
     * The name of the item
     */
    private ItemNames name;

    /**
     * An Array containing the names of all the Items, this Item eats
     */
    private ItemNames[] eats;

    /**
     * The image of the Item
     */
    private ImageIcon image;

    /**
     * Creates a new Item
     * @param name The name of the Item
     * @param eats An Array consisting of all the ItemNames, this Item will eat
     * @param filename The file name of the image
     * @throws IOException If image is not found
     */
    public Item(ItemNames name, ItemNames[] eats,String filename) throws IOException {
        this.name = name;

        //If eats is null, initialise this.eats with an empty Array
        if (eats == null) this.eats = new ItemNames[0];
        else this.eats = eats;

        //Sets image to a new image icon with the supplied file name
        this.image = new ImageIcon(filename);
    }

    /**
     * Gets the name of this Item
     * @return Name
     */
    public ItemNames getName() {
        return name;
    }

    /**
     * Gets the image of this Item
     * @return Image
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * Gets an Array of all the ItemNames of Items, this Item will eat
     * @return An Array of ItemNames
     */
    public ItemNames[] getEats() {
        return eats;
    }
}

