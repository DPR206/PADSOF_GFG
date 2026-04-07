package search;

/**
 * This class represents a price range filter used to limit
 * search results based on a minimum and maximum price.
 *
 * <p>It is typically used in product search operations
 * to filter items within a specified price range.</p>
 *
 * @author Sofía C.L.
 * @version 1.4
 * @see SearchStoreProducts
 */
public class PriceFilter {

    /**
     * Minimum price of the filter.
     */
    private double min;

    /**
     * Maximum price of the filter.
     */
    private double max;

    /**
     * Constructs a new PriceFilter with the specified price range.
     *
     * @param min the minimum price (inclusive)
     * @param max the maximum price (inclusive)
     */
    public PriceFilter(double min, double max){
        this.min = min;
        this.max = max;
    }

    /**
     * Returns the minimum price of the filter.
     *
     * @return the minimum price
     */
    public double getMin(){
        return this.min;
    }

    /**
     * Returns the maximum price of the filter.
     *
     * @return the maximum price
     */
    public double getMax(){
        return this.max;
    }
}