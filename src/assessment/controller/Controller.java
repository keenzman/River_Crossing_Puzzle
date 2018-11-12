package assessment.controller;

import assessment.models.*;
import assessment.views.Display;

import java.io.IOException;
import java.util.HashSet;

/**
 * Controller in MVC
 *
 * @author Robert Greener
 * @author Sakeen Zaman
 */
public class Controller {

    /**
     * Boat in the puzzle
     */
    private Boat boat;

    /**
     * Left bank of the river
     */
    private Location leftBank;

    /**
     * Right bank of the river
     */
    private Location rightBank;

    /**
     * Beans
     */
    private Item beans;

    /**
     * The farmer which is required to move the boat
     */
    private Item farmer;

    /**
     * The fox(eats goose)
     */
    private Item fox;

    /**
     * The goose(eats beans)
     */
    private Item goose;

    /**
     * All of the items in the puzzle
     */
    private HashSet<Item> allItems;

    /**
     * The view in the MVC model
     */
    private Display display;

    /**
     * Makes new controller
     */
    public Controller (){

        //Try and catch on an IO excepton

        try {

            //Makes a new boat with a max number of items of 2, starts on the right hand side with a supplied image
            boat = new Boat(2, Directions.RIGHT, "resources/coursework13/boat.png");

            //Makes a new beans which eats nothing with a supplied image
            beans = new Item(ItemNames.BEANS, new ItemNames[0], "resources/coursework13/beans.png");

            //Makes a new farmer which eats nothing with a supplied image
            farmer = new Item(ItemNames.FARMER, new ItemNames[0], "resources/coursework13/farmer.png");

            //Makes a new fox that eats a goose with a supplied image
            fox = new Item(ItemNames.FOX, new ItemNames[] {ItemNames.GOOSE}, "resources/coursework13/fox.png");

            //Makes a new goose which eats the beans with a supplied image
            goose = new Item(ItemNames.GOOSE, new ItemNames[] {ItemNames.BEANS},"resources/coursework13/goose.png");
        } catch (IOException e) {

            //if an IO exception is caught, print the message
            e.printStackTrace();
        }

        //Initialise allItems and add all the items to it
        allItems = new HashSet<>();
        allItems.add(beans);
        allItems.add(farmer);
        allItems.add(fox);
        allItems.add(goose);

        //Initialise leftBank, which can hold all of the items and is on the left hand side
        leftBank = new Location(allItems.size(), Directions.LEFT);

        /*Initialise rightBank which contains all of the items,
         *has a max capacity of all of the items and is on the right hand side
         */
        rightBank = new Location((HashSet<Item>) allItems.clone(), allItems.size(), Directions.RIGHT);

        //Make a new display
        display = new Display(this);

        //Calls redraw method to display items
        display.reDraw(leftBank, rightBank, boat);

        //Make the display visible
        display.setVisible(true);
    }

    /**
     * Moves an item in a direction
     * @param itemName The name of the item to move
     * @param direction The direction to move the item in
     * @return True if the move is successful
     */
    public boolean move(ItemNames itemName, Directions direction) {

        //Initial return value to false
        boolean moveSuccessful = false;

        //Initialise item as null
        Item item = null;

        //Searches allItems for an item with the supplied name
        for (Item i : allItems) {
            if (i.getName() == itemName) item = i;
        }

        //If the item is not found, throw an IllegalArgumentException
        if (item == null) throw new IllegalArgumentException(itemName.name() + " not found");

        /*
         * If the item is on the leftBank,
         * AND direction to move is RIGHT
         * AND the boat has space on it
         * AND the boat is on the left hand side
         */
        if (leftBank.contains(item) && direction == Directions.RIGHT
                && boat.getSpaces() > 0 && boat.getSide() == Directions.LEFT) {

            //Remove the item from the leftBank and add it to the boat
            leftBank.removeItem(item);
            boat.addItem(item);

            //Set move successful to true
            moveSuccessful = true;

        }
        /*
         * If the item is on the rightBank,
         * AND the direction to move is left,
         * AND the boat has space on it,
         * AND the boat is on the right hand side
         */
        else if (rightBank.contains(item) && direction == Directions.LEFT
                && boat.getSpaces() > 0 && boat.getSide() == Directions.RIGHT) {

            // Remove item from rightBank and add it to the boat
            rightBank.removeItem(item);
            boat.addItem(item);

            //Set move successful to true
            moveSuccessful = true;
        }
        //If the boat contains the item AND boat is on the same side as the direction of travel
        else if (boat.contains(item) && boat.getSide() == direction) {

            //Remove the item from the boat
            boat.removeItem(item);

            //If the direction is right, add the item to the rightBank
            if (direction == Directions.RIGHT) {
                rightBank.addItem(item);
            }
            //Otherwise, add the item to leftBank
            else {
                leftBank.addItem(item);
            }

            //Set move successful to true
            moveSuccessful = true;
        }

        //Call refresh
        refresh();

        //Return move successful
        return moveSuccessful;
    }

    /**
     * Moves the boat in a given direction
     * @param direction The direction to move
     * @return True if the move was successful
     */
    public boolean moveBoat(Directions direction) {

        //If the boat is already on the correct side, OR the boat does not contain the farmer, return false
        if (direction == boat.getSide() || !boat.contains(farmer)) return false;

        //Change the boat's side
        boat.setSide(direction);

        //Decrement the score
        boat.decrementScore();

        // Call refresh
        refresh();

        // Return true
        return true;
    }

    /**
     * Checks whether the current situation is valid
     * @return True if valid
     */
    private boolean checkValidity() {

        //Location sideToCheck will be the side which does not have a boat on it
        Location sideToCheck;

        //If the boat is on the right, sideToCheck is leftBank
        if (boat.getSide() == Directions.RIGHT) {
            sideToCheck = leftBank;
        }
        //Otherwise, sideToCheck is on the rightBank
        else {
            sideToCheck = rightBank;
        }
        //Make a new HashSet consisting of ItemNames
        HashSet<ItemNames> itemsOnBank = new HashSet<>();

        //Populate itemsOnBank with the ItemNames of all the items on the bank
        for (Item i : sideToCheck.getItems()) {
            itemsOnBank.add(i.getName());
        }

        //For each Item i on the bank
        for (Item i : sideToCheck.getItems()) {

            //For each ItemNames i eats
            for (ItemNames itemEaten : i.getEats()) {

                //If an item is on the bank, that i eats, return false
                if (itemsOnBank.contains(itemEaten)) return false;
            }
        }

        return true;
    }

    /**
     * Checks whether the puzzle is complete
     * @return True if complete
     */
    private boolean checkComplete() {
        //Returns true if there are no spaces left on the leftBank
        return leftBank.getSpaces() == 0;
    }

    /**
     * Starts the program
     * @param args Command line arguments ignored
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
    }

    /**
     * redraws display and checks conditions
     */
    private void refresh() {

        //redraws display
        display.reDraw(leftBank, rightBank, boat);

        //if the current situation is not valid
        if (!checkValidity()) {
            //if the user wishes to restart, call reset
            if (display.questionDialog("Game Over!\nDo you wish to restart?", "Game Over")) {
                reset();
            }
            //Otherwise, disable the buttons and set the title to Game Over
            else {
                display.disableButtons();
                display.setTitle("Game Over");
            }
        }
        //If the game is complete
        else if (checkComplete()) {
            //If the user wishes to restart, call reset
            if (display.questionDialog("You've won the game!\nYour score was " + boat.getScore() +
                    "\nDo you wish to restart?", "Game Complete")) {
                reset();
            }
            //Otherwise, disable the buttons and change the title
            else {
                display.disableButtons();
                display.setTitle("Game Complete - Score: " + boat.getScore());
            }
        }
    }

    /**
     * Resets to initial state
     */
    private void reset() {
        try {
            //Reassigns boat to a new Boat
            boat = new Boat(2, Directions.RIGHT, "resources/coursework13/boat.png");
        } catch (IOException e) {
            //If an IO exception is thrown, print the error message
            e.printStackTrace();
        }
        //Reassigns leftBank to a new Location
        leftBank = new Location(allItems.size(), Directions.LEFT);

        //Reassigns rightBank to a new Location containing all of the items
        rightBank = new Location((HashSet<Item>) allItems.clone(), allItems.size(), Directions.RIGHT);

        //Redraws the display
        display.reDraw(leftBank, rightBank, boat);
    }
}
