package product;

import order.Discount;

/**
 * Class name: Game
 * <p>
 * Description: It implements the games
 * @author Ana O.R.
 * @version 1.3
 * @see StoreProduct
 */
public class Game extends StoreProduct {
    /** The game's number of players */
    private int numPlayers;
    /* The game's age range */
    private String ageRange;

    /**
     * The game's constructor
     * @param price       the game's price
     * @param name        the game's name
     * @param description the game's description
     * @param photo       the game's photo's path
     * @param stock       the game's stock
     * @param numPlayers  the game's num players
     * @param ageRange    the game's age range
     * @param categories  the game's categories
     */
    Game(double price, String name, String description, String photo, int stock, int numPlayers,
         String ageRange, Category... categories) {
        super(price, name, description, photo, ProductType.GAME, stock, categories);
        this.numPlayers = numPlayers;
        this.ageRange = ageRange;
    }

    /**
     * It allows an employee to add a game to the store
     * @param price       the game's price
     * @param name        the game's name
     * @param description the game's description
     * @param photo       the game's photo's path
     * @param stock       the game's stock
     * @param numPlayers  the game's num players
     * @param ageRange    the game's age range
     * @param categories  the game's categories
     * @return the new game
     */
    public Game createGame(double price, String name, String description, String photo, int stock,
                           int numPlayers, String ageRange, Category... categories) {
        return new Game(price, name, description, photo, stock, numPlayers, ageRange, categories);
    }

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        return super.toString() + ", " + this.numPlayers + ", " + this.ageRange;
    }

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows for an employee to change a game's price
     * @param price the game's new price
     */
    @Override
    public void changePrice(double price) {
        super.changePrice(price);
    }

    /**
     * It allows for an employee to change a game's name
     * @param newName the game's new name
     */
    @Override
    public void changeName(String newName) {
        super.changeName(newName);
    }

    /**
     * It allows for an employee to change a game's description
     * @param newDescription the game's new description
     */
    @Override
    public void changeDescription(String newDescription) {
        super.changeDescription(newDescription);
    }

    /**
     * It allows for an employee to change a game's photo's path
     * @param newPhoto the game's new photo
     */
    @Override
    public void changePhoto(String newPhoto) {
        super.changePhoto(newPhoto);
    }

    /**
     * It allows for an employee to change a game's game type
     * @param newType the game's new product type
     */
    @Override
    public void changeType(ProductType newType) {
        super.changeType(newType);
    }

    /**
     * It allows for an employee to change the game's stock as well as blocking or unblocking stock
     * @param newStock the game's new stock
     */
    @Override
    public void changeStock(int newStock) {
        super.changeStock(newStock);
    }

    /**
     * It allows the system or an employee to add categories to a product
     * @param newCategories the categories to be added
     */
    @Override
    public void addCategory(Category... newCategories) {
        super.addCategory(newCategories);
    }

    /**
     * It allows an employee to remove categories from a product
     * @param categories the categories to be deleted
     */
    @Override
    public void removeCategory(Category... categories) {
        super.removeCategory(categories);
    }

    /**
     * It allows an employee to add discounts to products or categories (Discounts is in charge of making sure they
     * don't overlap)
     * @param newDiscount the new discount to be applied
     */
    @Override
    public void changeDiscount(Discount newDiscount) {
        super.changeDiscount(newDiscount);
    }

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

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * It returns the game's id
     * @return the game's id
     */
    @Override
    public int getId() {
        return super.getId();
    }

    /**
     * It returns the game's price
     * @return the game's price
     */
    @Override
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * It returns the game's description
     * @return the game's description
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * It returns the game's photo's path
     * @return the game's photo
     */
    @Override
    public String getPhoto() {
        return super.getPhoto();
    }

    /**
     * It returns the game's product type
     * @return the game's product type
     */
    @Override
    public ProductType getType() {
        return super.getType();
    }

    /**
     * It returns the game's categories
     * @return the game's categories
     */
    @Override
    public Category[] getCategories() {
        return super.getCategories();
    }

    /**
     * It returns's the product's stock
     * @return the product's store
     */
    @Override
    public int getStock() {
        return super.getStock();
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