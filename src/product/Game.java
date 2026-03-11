package product;

import store.Store;

/**
 * Class name: Game
 * <p>
 * Description: It implements the games
 * @author Ana O.R.
 * @version 1.0
 * @see StoreProduct
 */
public class Game extends StoreProduct {
    /** The game's number of players */
    private int numPlayers;
    /* The game's age range */
    private String ageRange;

    /**
     * The game's constructor
     * @param id          the game's id
     * @param price       the game's price
     * @param name        the game's name
     * @param description the game's description
     * @param photo       the game's photo's path
     * @param stock       the game's stock
     * @param numPlayers  the game's num players
     * @param ageRange    the game's age range
     * @param categories  the game's categories
     */
    Game(int id, double price, String name, String description, String photo, int stock, int numPlayers,
         String ageRange, Category... categories) {
        super(id, price, name, description, photo, ProductType.GAME, stock, categories);
        this.numPlayers = numPlayers;
        this.ageRange = ageRange;
    }

    /**
     * It allows an employee to add a game to the store
     * @param store       the store
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
    public Game addGame(Store store, double price, String name, String description, String photo, int stock,
                        int numPlayers, String ageRange, Category... categories) {
        return new Game(store.getProductId(), price, name, description, photo, stock, numPlayers, ageRange, categories);
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

    // DUE: Change -> categories

    // DUE: Change -> numPlayers
    // DUE: Change -> ageRange

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