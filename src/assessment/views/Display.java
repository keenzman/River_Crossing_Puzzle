package assessment.views;

import assessment.controller.Controller;
import assessment.models.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashSet;

/**
 * The view in the MVC model
 *
 * @author Robert Greener
 * @author Sakeen Zaman
 */
public class Display extends JFrame {

    /**
     * The JPanel in the Center of this JFrame
     */
    private JPanel jpCenter;

    /**
     * The JPanel in the East of this JFrame
     */
    private JPanel jpEast;

    /**
     * The JPanel in the West of this JFrame
     */
    private JPanel jpWest;

    /**
     * The JPanel in the left of the center JPanel
     */
    private JPanel jpCenterLeft;

    /**
     * The JPanel in the right of the center JPanel
     */
    private JPanel jpCenterRight;


    /**
     * The JButton that moves the boat left
     */
    private JButton jbBoatLeft;

    /**
     * The JButton that moves the boat right
     */
    private JButton jbBoatRight;

    /**
     * The JButton that moves the fox left
     */
    private JButton jbFoxLeft;

    /**
     * The JButton that moves the fox right
     */
    private JButton jbFoxRight;

    /**
     * The JButton that moves the goose left
     */
    private JButton jbGooseLeft;

    /**
     * The JButton that moves the goose right
     */
    private JButton jbGooseRight;

    /**
     * The JButton that moves the beans left
     */
    private JButton jbBeansLeft;

    /**
     * The JButton that moves the beans right
     */
    private JButton jbBeansRight;

    /**
     * The JButton that moves the farmer left
     */
    private JButton jbFarmerLeft;

    /**
     * The JButton that moves the farmer right
     */
    private JButton jbFarmerRight;

    /**
     * The controller in the MVC model
     */
    private Controller controller;

    /**
     * Makes a new display
     * @param controller The controller
     */
    public Display(Controller controller) {

        // Sets the title of the window
        super("Fox, Goose and Bag of Beans, Score: 0");

        // Terminates program if closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Sets the layout to a BorderLayout
        this.setLayout(new BorderLayout());

        this.controller = controller;

        // Initialise Labels
        JLabel jlBoat = new JLabel("Boat:");
        JLabel jlFox = new JLabel("Fox:");
        JLabel jlGoose = new JLabel("Goose:");
        JLabel jlBeans = new JLabel("Beans:");
        JLabel jlFarmer = new JLabel("Farmer:");

        // Initialise left buttons
        jbBoatLeft = new JButton("<");
        jbFoxLeft = new JButton("<");
        jbGooseLeft = new JButton("<");
        jbBeansLeft = new JButton("<");
        jbFarmerLeft = new JButton("<");

        // Initialise right buttons
        jbBoatRight = new JButton(">");
        jbFoxRight = new JButton(">");
        jbGooseRight = new JButton(">");
        jbBeansRight = new JButton(">");
        jbFarmerRight = new JButton(">");

        /*
         * Add actionListeners (using Lambda expressions) that call methods in the controller
         */
        jbBoatLeft.addActionListener(e -> controller.moveBoat(Directions.LEFT));
        jbBoatRight.addActionListener(e -> controller.moveBoat(Directions.RIGHT));

        jbFarmerLeft.addActionListener(e -> controller.move(ItemNames.FARMER, Directions.LEFT));
        jbFarmerRight.addActionListener(e -> controller.move(ItemNames.FARMER, Directions.RIGHT));

        jbFoxLeft.addActionListener(e -> controller.move(ItemNames.FOX, Directions.LEFT));
        jbFoxRight.addActionListener(e -> controller.move(ItemNames.FOX, Directions.RIGHT));

        jbGooseLeft.addActionListener(e -> controller.move(ItemNames.GOOSE, Directions.LEFT));
        jbGooseRight.addActionListener(e -> controller.move(ItemNames.GOOSE, Directions.RIGHT));

        jbBeansLeft.addActionListener(e -> controller.move(ItemNames.BEANS, Directions.LEFT));
        jbBeansRight.addActionListener(e -> controller.move(ItemNames.BEANS, Directions.RIGHT));

        //initialise the JPanels with backgrounds
        try {
            jpCenter = new JPanelBackground("resources/coursework13/water.png");
            jpEast = new JPanelBackground("resources/coursework13/grass.png");
            jpWest = new JPanelBackground("resources/coursework13/grass.png");
        } catch (IOException e) {

            //If an IO exception is caught (if the background image does not exist) print the error message
            e.printStackTrace();
        }

        //Initialise the other JPanels
        JPanel jpSouth = new JPanel();
        jpCenterLeft = new JPanel();
        jpCenterRight = new JPanel();

        //Set the layout of jpSouth to GridLayout
        jpSouth.setLayout(new GridLayout(1, 15));

        //Add all the labels and buttons to jpSouth
        jpSouth.add(jlBoat);
        jpSouth.add(jbBoatLeft);
        jpSouth.add(jbBoatRight);
        jpSouth.add(jlFox);
        jpSouth.add(jbFoxLeft);
        jpSouth.add(jbFoxRight);
        jpSouth.add(jlGoose);
        jpSouth.add(jbGooseLeft);
        jpSouth.add(jbGooseRight);
        jpSouth.add(jlBeans);
        jpSouth.add(jbBeansLeft);
        jpSouth.add(jbBeansRight);
        jpSouth.add(jlFarmer);
        jpSouth.add(jbFarmerLeft);
        jpSouth.add(jbFarmerRight);

        //Set the layout of jpCenter to a GridLayout with two panes
        jpCenter.setLayout(new GridLayout(1,2));

        //Set the layouts of jpCenterLeft and jpCenterRight to BorderLayouts
        jpCenterLeft.setLayout(new BorderLayout());
        jpCenterRight.setLayout(new BorderLayout());

        //Make jpCenterLeft and jpCenterRight transparent (so the background can be seen)
        jpCenterLeft.setOpaque(false);
        jpCenterRight.setOpaque(false);

        //Add jpCenterLeft and jpCenterRight to jpCenter
        jpCenter.add(jpCenterLeft);
        jpCenter.add(jpCenterRight);

        //Sets the layout of jpEast and jpWest to Grid Layout with 4 rows and 1 column
        jpEast.setLayout(new GridLayout(4,1));
        jpWest.setLayout(new GridLayout(4,1));

        //Sets the preferred size of jpEast, jpWest, and jpCenter
        jpEast.setPreferredSize(new Dimension(200, 700));
        jpWest.setPreferredSize(new Dimension(200,700));
        jpCenter.setPreferredSize(new Dimension(650, 700));

        //Add the top level JPanels to the JFrame
        this.add(jpSouth, BorderLayout.SOUTH);
        this.add(jpEast, BorderLayout.EAST);
        this.add(jpWest, BorderLayout.WEST);
        this.add(jpCenter, BorderLayout.CENTER);

        //Pack the JFrame
        this.pack();

        //Set resizable to false
        this.setResizable(false);
    }


    /**
     * Disable all the buttons
     */
    public void disableButtons() {
        jbBoatLeft.setEnabled(false);
        jbBoatRight.setEnabled(false);
        jbFoxLeft.setEnabled(false);
        jbFoxRight.setEnabled(false);
        jbGooseLeft.setEnabled(false);
        jbGooseRight.setEnabled(false);
        jbBeansLeft.setEnabled(false);
        jbBeansRight.setEnabled(false);
        jbFarmerLeft.setEnabled(false);
        jbFarmerRight.setEnabled(false);
    }

    /**
     * Redraws the windows
     * @param leftBank The Location for the leftBank
     * @param rightBank The Location for the rightBank
     * @param boat The Boat
     */
    public void reDraw(Location leftBank, Location rightBank, Boat boat) {

        //Remove all elements from jpWest, jpCenterLeft, jpCenterRight, jpEast
        jpWest.removeAll();
        jpCenterLeft.removeAll();
        jpCenterRight.removeAll();
        jpEast.removeAll();

        //The panel on which the boat should appear
        JPanel boatPanel;

        //For each item on the leftBank, add a new JLabel with the image of the Item to jpWest
        for (Item item : leftBank.getItems()) jpWest.add(new JLabel(item.getImage()));

        //For each item on the rightBank, add a new JLabel with the image of the Item to jpEast
        for (Item item : rightBank.getItems()) jpEast.add(new JLabel(item.getImage()));

        //If the boat is on the left, set boatPanel to jpCenterLeft
        if (boat.getSide() == Directions.LEFT) boatPanel = jpCenterLeft;

        //Otherwise, set boatPanel to jpCenterRight
        else boatPanel = jpCenterRight;

        //Add the boat to the North of boatPanel
        boatPanel.add(new JLabel(boat.getImage()), BorderLayout.NORTH);

        // Gets the boat's Items and converts the HashSet to an Array
        HashSet<Item> itemHashSet = boat.getItems();
        Item[] itemsArray = itemHashSet.toArray(new Item[itemHashSet.size()]);

        //If there is at least one item in itemsArray, add this to the west of the boatPanel
        if (itemsArray.length >= 1) boatPanel.add(new JLabel(itemsArray[0].getImage()), BorderLayout.WEST);

        //If there is a second item, in the itemsArray, add this to the east of the boatPanel
        if (itemsArray.length == 2) boatPanel.add(new JLabel(itemsArray[1].getImage()), BorderLayout.EAST);


        //Redraws panels on the screen
        jpWest.revalidate();
        jpEast.revalidate();
        jpCenterRight.revalidate();
        jpCenterLeft.revalidate();

        jpWest.repaint();
        jpEast.repaint();
        jpCenterRight.repaint();
        jpCenterLeft.repaint();

        //Updates score in the title
        this.setTitle("Fox, Goose and Bag of Beans, Score: " + boat.getScore());
    }

    /**
     * Shows a new question dialog to the user
     * @param message The message to show in a dialog window
     * @param title The title of the dialog window
     * @return true if the user clicked yes
     */
    public boolean questionDialog(String message, String title) {

        /*
         * Shows a new confirmDialog with a parent component of this window, message and title as supplied,
         * YES and NO buttons, and a Type Of QUESTION_MESSAGE
         *
         * Return true, if YES was clicked
         */
        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION;
    }
}
