package product;

import java.time.LocalDate;

/**
 * It implements the game store product type
 * @author Ana O.R.
 * @version 1.7
 * @see StoreProduct
 */
public class Game extends StoreProduct {
    /** The game's number of players */
    private int numPlayers;
    /** The game's age range */
    private String ageRange;
    /** The game's style */
    private GameStyle gameStyle;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A game's general constructor
     * @param id                 the game's id
     * @param price              the game's price
     * @param name               the game's name
     * @param description        the game's description
     * @param photo              the game's photo's path
     * @param averagePunctuation the game's average punctuation
     * @param addedDate          the game's added date
     * @param stock              the game's stock
     * @param numPlayers         the game's num players
     * @param ageRange           the game's age range
     * @param gameStyle          the game's style
     * @param categories         the game's categories
     */
    public Game(String id, double price, String name, String description, String photo, double averagePunctuation,
                LocalDate addedDate, int stock, int numPlayers, String ageRange, GameStyle gameStyle,
                Category... categories) {
        super(id, price, name, description, photo, averagePunctuation, addedDate, ProductType.GAME, stock, categories);
        this.numPlayers = numPlayers;
        this.ageRange = ageRange;
        this.gameStyle = gameStyle;
    }

    /**
     * The game's constructor
     * @param price       the game's price
     * @param name        the game's name
     * @param description the game's description
     * @param photo       the game's photo's path
     * @param stock       the game's stock
     * @param numPlayers  the game's num players
     * @param ageRange    the game's age range
     * @param gameStyle   the game's style
     * @param categories  the game's categories
     */
    public Game(double price, String name, String description, String photo, int stock, int numPlayers, String ageRange,
                GameStyle gameStyle, Category... categories) {
        super(price, name, description, photo, ProductType.GAME, stock, categories);
        this.numPlayers = numPlayers;
        this.ageRange = ageRange;
        this.gameStyle = gameStyle;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It prints the product's info when seen individually
     */
    @Override
    public void bigPrintInfo() {
        /* super;NUM_PAGES;AUTHOR;EDITORIAL;YEAR;<NUM_PLAYERS;AGE_RANGE;GAME_STYLE>;BRAND;MATERIAL;DIMENSION */
        super.bigPrintInfo();
        System.out.println("Number of players: " + this.numPlayers);
        System.out.println("Age range: " + this.ageRange);
        System.out.println("Game style: " + this.gameStyle.getFormatedName());
    }

    /**
     * It prints the product's info when managed
     */
    @Override
    public void printAllInfo() {
        super.printAllInfo();
        System.out.println("Number of players: " + this.numPlayers);
        System.out.println("Age range: " + this.ageRange);
        System.out.println("Game style: " + this.gameStyle.getFormatedName());
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It returns the game's age range
     * @return the game's age range
     */
    public String getAgeRange() {
        return this.ageRange;
    }

    /**
     * It allows an employee to change a game's age range
     * @param newAgeRange the game's age range
     */
    public void setAgeRange(String newAgeRange) {
        this.ageRange = newAgeRange;
    }

    /**
     * It returns the game's style
     * @return the game's style
     */
    public GameStyle getGameStyle() {
        return this.gameStyle;
    }

    /**
     * It allows an employee to change a game's style
     * @param newGameStyle the game's style
     */
    public void setGameStyle(GameStyle newGameStyle) {
        this.gameStyle = newGameStyle;
    }

    /**
     * It returns the game's number of players
     * @return the game's number of players
     */
    public int getNumPlayers() {
        return this.numPlayers;
    }

    /**
     * It allows an employee to change a game's number of players
     * @param newNumPlayers the game's number of players
     */
    public void setNumPlayers(int newNumPlayers) {
        this.numPlayers = newNumPlayers;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        /* super;NUM_PAGES;AUTHOR;EDITORIAL;YEAR;<NUM_PLAYERS;AGE_RANGE;GAME_STYLE>;BRAND;MATERIAL;DIMENSION */
        return super.toString() + ";" + /*num_pages*/ ";" + /*author*/ ";" + /*editorial*/ ";" + /*year*/ ";" +
               this.numPlayers + ";" + this.ageRange + ";" + this.gameStyle.getFormatedName() + ";" /*brand*/ +
               ";" /*material*/ + ";" /*dimension*/;
    }
}