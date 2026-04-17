package model.search;

/**
 * This class represents a punctuation (rating) filter used to limit
 * search results based on a minimum and maximum score.
 *
 * <p>It is typically used in product search operations
 * to filter items according to their rating or evaluation score.</p>
 *
 * @author Sofía C.L.
 * @version 1.4
 * @see SearchStoreProducts
 */
public class PunctuationFilter {

    /**
     * Minimum punctuation (rating) of the filter.
     */
    private int min;

    /**
     * Maximum punctuation (rating) of the filter.
     */
    private int max;

    /**
     * Constructs a new PunctuationFilter with the specified range.
     *
     * @param min the minimum punctuation (inclusive)
     * @param max the maximum punctuation (inclusive)
     */
    public PunctuationFilter(int min, int max){
        this.min = min;
        this.max = max;
    }

    /**
     * Returns the minimum punctuation of the filter.
     *
     * @return the minimum punctuation
     */
    public int getMin(){
        return this.min;
    }

    /**
     * Returns the maximum punctuation of the filter.
     *
     * @return the maximum punctuation
     */
    public int getMax(){
        return this.max;
    }
}