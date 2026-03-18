package product;

/**
 * Class name: Game
 * <p>
 * Description: It implements the games
 * @author Ana O.R.
 * @version 1.4
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
    public Game(double price, String name, String description, String photo, int stock, int numPlayers,
                String ageRange, Category... categories) {
        super(price, name, description, photo, ProductType.GAME, stock, categories);
        this.numPlayers = numPlayers;
        this.ageRange = ageRange;
    }

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        // TIPO(C/J/F);ID;NOMBRE;DESCRIPCIÓN;PRECIO;UNIDADES;CATEGORÍAS;PAGINAS;AUTOR;EDITORIAL;AÑO;JUGADORES;EDAD
        // ;ESTILO(Cartas/Dados/Tablero/Miniatura);MARCA;MATERIAL;DIMENSION
        return ProductType.GAME.getSymbol() + ";" + this.getId() + ";" + this.getName() + ";" + this.getDescription() +
                ";" + this.getPrice() + ";" + this.getStock() + ";" + /*paginas*/ ";" + /*autor*/ ";" + /*editorial*/ ";" + /*año*/ ";" + this.numPlayers + ";" + this.ageRange + ";" + this.getPrintCategories() + ";"
                /*marca*/ + ";" /*material*/ + ";" /*dimension*/;
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

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * It returns the game's categories in a save-file-friendly manner
     * @return a string containing the game's categories
     */
    public String getPrintCategories() { // ! revisar
        Category[] categories = super.getCategories();
        StringBuilder sb = new StringBuilder();

        for (Category category : categories) {
            sb.append(category.toString()).append(", ");
        }

        return sb.toString();
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