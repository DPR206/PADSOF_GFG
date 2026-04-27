package model.store;

import model.order.Order;
import model.product.Category;
import model.product.StoreProduct;
import model.user.RegisteredClient;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.sqrt;

/**
 * It implements the store's recommender
 * @author Ana O.R.
 * @version 1.1
 */
public class Recommender implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    private static final Recommender PARAM = new Recommender();
    HashMap<Category, Double> categoriesAndScores = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A recommender's constructor
     */
    Recommender() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Obtains the recommender
     * @return the store's recommender
     */
    public static Recommender getInstance() {
        return PARAM;
    }

    /**
     * It recommends products which are similar to those bought by a client, excluding those who were already bought
     * @param client the desired client
     * @return the top-K recommended products
     */
    public List<StoreProduct> recommendSimilarProducts(RegisteredClient client) { /* 1st method */
        int K = Parameter.getParam().getkRecommend();
        List<Category> categories = new ArrayList<>(Store.getInstance().getCategories().values());
        Map<StoreProduct, Double> productsAndRatings = getPurchasedProducts(client);
        if (productsAndRatings.isEmpty()) {
            return Collections.emptyList();
        }

        /* Step 1: Product representation -> All products p can be represented as a vector of categories v(p) */
        Map<StoreProduct, double[]> productVectors = new HashMap<>();
        for (StoreProduct product : Store.getInstance().getStoreProducts().values()) {
            double[] productVector = new double[categories.size()];
            for (Category category : product.getCategories()) {
                productVector[categories.indexOf(category)] = 1.0;
            }

            productVector = normalizeL2(productVector);

            productVectors.put(product, productVector);
        }

        /* Step 2: User profile -> All users u can be represented as a vector v(u) as a vector of categories v(p) */
        double[] userVector = new double[categories.size()];

        for (Map.Entry<StoreProduct, Double> entry : productsAndRatings.entrySet()) {
            StoreProduct product = entry.getKey();
            for (Category category : product.getCategories()) {
                int index = categories.indexOf(category);
                /* totalScore = totalScore + (a*<score> + b) */
                userVector[index] = userVector[index] + (entry.getValue() * Parameter.getParam().getScoreAParam() +
                                                         Parameter.getParam().getScoreBParam());

            }
        }

        /* Step 3: Normalize */
        userVector = normalizeL2(userVector);

        /* Step 5.1: For each store product that can be bought, calculate its cosine similarity */
        Map<StoreProduct, Double> scores = new HashMap<>();
        for (StoreProduct product : Store.getInstance().getStoreProducts().values()) {
            double similarity = cosineSimilarity(userVector, productVectors.get(product));

            /* Step 5.2: Exclude products that were bought previously */
            if (!(productsAndRatings.containsKey(product)) && (product.getStock() > 0)) {
                scores.put(product, similarity);
            }
        }

        return getTopK(scores, K);
    }

    /**
     * It normalizes a vector
     * @param vector the desired vector
     * @return the vector's normalization
     */
    private double[] normalizeL2(double[] vector) {
        /* ||v|| = SQRT(SUM [1..n] (vi*vi)); Norm(v) = v/||v|| */
        double sum = 0.0;
        for (double v : vector) {
            sum = sum + (v * v);
        }

        double norm = sqrt(sum);
        if (norm == 0) {
            return vector;
        }

        double[] result = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i] / norm;
        }

        return result;
    }

    /**
     * It calculates the cosine similarity between two vectors
     * @param vector1 one of the desired vectors
     * @param vector2 the other desired vector
     * @return the cosine similarity between the two vectors
     */
    private double cosineSimilarity(double[] vector1, double[] vector2) {
        double cosineSimilarity = 0.0;

        for (int i = 0; i < vector1.length; i++) {
            cosineSimilarity = cosineSimilarity + (vector1[i] * vector2[i]);
        }

        return cosineSimilarity;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the products purchased by the client and their score
     * @param client the client whose products will be analysed
     * @return the products purchased by the client and their score
     */
    private Map<StoreProduct, Double> getPurchasedProducts(RegisteredClient client) {
        Map<StoreProduct, Double> result = new HashMap<>();

        if (client.getOrderHistory() == null) {
            // NOTE: The client hasn't bought anything yet
            return result;
        }

        for (Order order : client.getOrderHistory().getOrders()) {
            for (StoreProduct product : order.getSp()) {
                if (product.getReviews().containsKey(client)) {
                    result.put(product, (double) product.getReviews().get(client).getScoring());
                } else {
                    result.put(product, 0.0);
                }
            }
        }
        return result;
    }

    /**
     * It orders and filters a Hashmap according to its values, gets top K elements
     * @param scores the desired Hashmap
     * @param k      the number of products to be filtered to
     * @return the resulting list of keys, ordered and filtered
     */
    private List<StoreProduct> getTopK(Map<StoreProduct, Double> scores, int k) {
        /* https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values */
        /* Step 5.3 and 5.4: Order by decreasing order and return top K */
        Map<StoreProduct, Double> topK =
                scores.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(k)
                      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
                              LinkedHashMap::new));

        return new ArrayList<>(topK.keySet());
    }
}