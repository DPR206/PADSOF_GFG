package search;
/**
 * It implements the price filter
 * @author Sofía C.L.
 * @version 1.3
 * @see SearchStoreProducts
  */
public class PriceFilter{
    private double min;
    private double max;
    /**
	 * Creates a new price filter
	 *
	 * @param min, minimun price wanted
	 * @param max, maximum price wanted
	 */
    public PriceFilter(double min, double max){
        this.min = min;
        this.max = max;
    }
    /**
	 * Gets the minimun
	 *
	 */
    public double getMin(){
        return this.min;
    }
    /**
	 * Gets the maximum
	 *
	 */
    public double getMax(){
        return this.max;
    }
}