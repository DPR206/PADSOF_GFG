package product;

/**
 * Class name: Game
 * <p>
 * Description: It implements the games
 * @author Ana O.R.
 * @version 1.0
 * @see StoreProduct
 */
public class Game extends StoreProduct {
    private int numPlayers;
    private String ageRange;

    Game(int id, double price, String name, String description, String photo, int stock,
         Category... categories) {
        super(id, price, name, description, photo, ProductType.GAME, stock, categories);
    }

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows for an employee to change a game's price
     * @param price the game's new price
     */
    public void changePrice(double price) {
        super.changePrice(price);
    }

    /**
     * It allows for an employee to change a game's name
     * @param newName the game's new name
     */
    public void changeName(String newName) {
        super.changeName(newName);
    }

    /**
     * It allows for an employee to change a game's description
     * @param newDescription the game's new description
     */
    public void changeDescription(String newDescription) {
        super.changeDescription(newDescription);
    }

    /**
     * It allows for an employee to change a game's photo's path
     * @param newPhoto the game's new photo
     */
    public void changePhoto(String newPhoto) {
        super.changePhoto(newPhoto);
    }

    /**
     * It allows for an employee to change a game's game type
     * @param newType the game's new product type
     */
    public void changeType(ProductType newType) {
        super.changeType(newType);
    }

    /**
     * It allows for an employee to change the game's stock as well as blocking or unblocking stock
     * @param newStock the game's new stock
     */
    public void changeStock(int newStock) {
        super.changeStock(newStock);
    }

    // DUE: Change -> categories

    // DUE: Change -> numPlayers
    // DUE: Change -> ageRange

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */
    /**
     * It returns the game's id
     * @return the game's id
     */
    public int getId() {
        return super.getId();
    }

    /**
     * It returns the game's price
     * @return the game's price
     */
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * It returns the game's description
     * @return the game's description
     */
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * It returns the game's photo's path
     * @return the game's photo
     */
    public String getPhoto() {
        return super.getPhoto();
    }

    /**
     * It returns the game's product type
     * @return the game's product type
     */
    public ProductType getType() {
        return super.getType();
    }

    /**
     * It returns the game's categories
     * @return the game's categories
     */
    public Category[] getCategories() {
        return super.getCategories();
    }

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
}