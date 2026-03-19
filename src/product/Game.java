package product;

/**
 * Class name: Game
 * <p>
 * Description: It implements the games
 * @author Ana O.R.
 * @version 1.5
 * @see StoreProduct
 */
public class Game extends StoreProduct {
    /** The game's number of players */
    private int numPlayers;
    /* The game's age range */
    private String ageRange;
    /* The game's style */
    private GameStyle gameStyle;

    /*------------------------------------------------- CONSTRUCTORS -------------------------------------------------*/

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
    public Game(double price, String name, String description, String photo, int stock, int numPlayers,
                String ageRange, GameStyle gameStyle, Category... categories) {
        super(price, name, description, photo, ProductType.GAME, stock, categories);
        this.numPlayers = numPlayers;
        this.ageRange = ageRange;
        this.gameStyle = gameStyle;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        /* TYPE(C/G/F);ID;NAME;DESCRIPTION;PRICE;STOCK;CATEGORIES;PAGES;AUTHOR;EDITORIAL;YEAR;PLAYERS;AGE;
        STYLE(Cards/Dice/GameBoard/Miniature);BRAND;MATERIAL;DIMENSION */
        return ProductType.GAME.getSymbol() + ";" + this.getId() + ";" + this.getName() + ";" + this.getDescription() +
                ";" + this.getPrice() + ";" + this.getStock() + ";" + this.getPrintCategories() + ";" + /*paginas*/
                ";" + /*autor*/ ";" + /*editorial*/ ";" + /*año*/ ";" + this.numPlayers + ";" + this.ageRange + ";" +
                this.gameStyle.getFormatedName() + ";" /*marca*/ + ";" /*material*/ + ";" /*dimension*/;
    }

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows an employee to change a game's number of players
     * @param newNumPlayers the game's number of players
     */
    public void changeNumPlayers(int newNumPlayers) {
        this.numPlayers = newNumPlayers;
    }

    /**
     * It allows an employee to change a game's age range
     * @param newAgeRange the game's age range
     */
    public void changeAgeRange(String newAgeRange) {
        this.ageRange = newAgeRange;
    }

    /**
     * It allows an employee to change a game's style
     * @param newGameStyle the game's style
     */
    public void changeGameStyle(GameStyle newGameStyle) {
        this.gameStyle = newGameStyle;
    }

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * It returns the game's number of players
     * @return the game's number of players
     */
    public int getNumPlayers() {
        return this.numPlayers;
    }

    /**
     * It returns the game's age range
     * @return the game's age range
     */
    public String getAgeRange() {
        return this.ageRange;
    }

    /**
     * It returns the game's style
     * @return the game's style
     */
    public GameStyle getGameStyle() {
        return this.gameStyle;
    }
}