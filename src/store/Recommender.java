package store;

import order.Order;
import product.Category;
import product.StoreProduct;
import user.RegisteredClient;

import java.util.HashMap;
import java.util.List;

/**
 * Class name: Recommender
 * <p>
 * Description: It implements the store's recommender
 * @author Ana O.R.
 * @version 1.0
 */
public class Recommender {
    /**
     * The Categories and scores.
     */
// NOTE: Por ambición e indecisión, primando la segunda, he decidido implementar ambos recomendadores de Moodle,
    // rezad por mí y por nuestro código
    HashMap<Category, Double> categoriesAndScores = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A recommender's constructor
     */
    Recommender() {

    }

    /**
     * It recommends similar products to those bought by a certain client
     * @param client the desired client
     */
    public void recommendSimilarProducts(RegisteredClient client) { /* 1st method */
        List<Order> orders = client.getOrderHistory().getOrders();
        // NOTE: Wtf es O(N^3), miedo, terror, pánico
        for (Order order : orders) {
            List<StoreProduct> products = order.getSp();
            for (StoreProduct product : products) {
                List<Category> categories = List.of(product.getCategories());
                for (Category category : categories) {
                    if (categoriesAndScores.containsKey(category)) {
                        Double totalScore = categoriesAndScores.get(category);
                        categoriesAndScores.remove(category);
                        /* totalScore = totalScore + scoreWeight */
                        /* scoreWeight = a*<score> + b */
                        totalScore = totalScore + (product.getReviews().get(client).getScoring() *
                                                   Parameter.getParam().getScoreAParam() +
                                                   Parameter.getParam().getScoreBParam());
                        categoriesAndScores.put(category, totalScore);
                    }
                }
            }
        }

        // DUE: Continuar
    }
}