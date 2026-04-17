package model.product;

/**
 * It defines the game's possible styles
 * @author Ana O.R.
 * @version 1.0
 */
public enum GameStyle {
    /** Cards game style */
    CARDS("Cards"),
    /** Dice game style */
    DICE("Dice"),
    /** Game board game style */
    GAMEBOARD("Gameboard"),
    /** Miniature game style */
    MINIATURE("Miniature");

    /** The string that defines an enumerations when written in the save file */
    private final String formatedName;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A game style's constructor
     * @param assignedFormatedName the name that defines said enumeration
     */
    GameStyle(String assignedFormatedName) {
        this.formatedName = assignedFormatedName;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Gets the enumeration's name
     * @return the enumeration's name
     */
    public String getFormatedName() {
        return formatedName;
    }
}