package search;
/**
 * It implements the punctuation filter
 * @author Sofía C.L.
 * @version 1.3
 * @see SearchStoreProducts
  */
public class PunctuationFilter{
    private int min;
    private int max;
    /**
	 * Creates a new punctuation filter
	 *
	 * @param min, minimum punctuation wanted
	 * @param max, maximum punctuation wanted
	 */
    public PunctuationFilter(int min, int max){
        this.min = min;
        this.max = max;
    }
    /**
	 * Gets the minimun punctuation
	 *
	 */
    public int getMin(){
        return this.min;
    }
    /**
	 * Gets the maximun punctuation
	 *
	 */
    public int getMax(){
        return this.max;
    }
}