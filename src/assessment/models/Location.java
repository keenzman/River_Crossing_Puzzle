package assessment.models;

import java.util.HashSet;

/**
 * A location that can store a number of Items
 *
 * @author Robert Greener
 * @author Sakeen Zaman
 */
public class Location {

    /**
     * The Items currently stored at this location
     */
    private HashSet<Item> items;

    /**
     * The max number of Items this location can store
     */
    private int maxNumberOfItems;

    /**
     * The side this location is on
     */
    private Directions side;

    /**
     * Makes a new location
     * @param items The initial Items this location contains
     * @param maxNumberOfItems The max number of Items this location can store
     * @param side The side this location is on
     */
    public Location(HashSet<Item> items, int maxNumberOfItems, Directions side) {
        this.items = items;
        this.maxNumberOfItems = maxNumberOfItems;
        this.side = side;
    }

    /**
     * Makes a new empty location
     * @param maxNumberOfItems The max number of Items this location can store
     * @param side The side this location is on
     */
    public Location(int maxNumberOfItems, Directions side) {
        this.items = new HashSet<>();
        this.maxNumberOfItems = maxNumberOfItems;
        this.side = side;
    }

    /**
     * Adds an Item to this location
     * @param item The Item to add
     * @return True if successful
     */
    public boolean addItem(Item item) {

        //If there is no room for the Item, return false
        if (items.size() >= maxNumberOfItems) return false;

        //Otherwise, add the item and return true
        else {
            items.add(item);
            return true;
        }
    }

    /**
     * Remove an Item from this location
     * @param item The Item to remove
     * @return True is successful
     */
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    /**
     * Gets the Items at this location
     * @return A HashSet containing all the Items
     */
    public HashSet<Item> getItems() {
        return items;
    }

    /**
     * Get the number of free slots
     * @return The number of free slots
     */
    public int getSpaces() {
        return maxNumberOfItems - items.size();
    }

    /**
     * Get the side of this location
     * @return The side of this location
     */
    public Directions getSide() {
        return side;
    }

    /**
     * Sets the side of this location
     * @param side The new side
     */
    protected void setSide (Directions side) {
        this.side = side;
    }


    /**
     * Checks whether a supplied Item is at this location
     * @param item The Item to check
     * @return True if the Item is at this location
     */
    public boolean contains(Item item) {
        return items.contains(item);
    }
}
