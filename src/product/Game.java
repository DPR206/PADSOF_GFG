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

    // DUE: Change -> name
    // DUE: Change -> description
    // DUE: Change -> photo
    // DUE: Change -> price
    // DUE: Change -> type
    // DUE: Change -> stock
    // DUE: Change -> categories
    // DUE: Change -> stock
    // DUE: Change -> numPlayers
    // DUE: Change -> ageRange
}
