package search;

/**
    * It implements the type of searches that will be done
    * @author Sofía C.L.
    * @version 1.3
    * @see Searcher
    */
public enum SearchType{
    S_STORE("store"),
    S_ORDER("order"),
    S_EXCHANGE("exchange"),
    S_PACK("pack"),
    S_EMPLOYEE("employee"),
    S_SECOND_HAND("second hand"),
    S_VALUATION_PROD("valuation prod");

    private String theType;

    /**
	 * Creates the enum and initializes the name of the type of search
	 *
	 * @param asc, determines if the searches are ascendant or descendant
     * @param type, determines the type of search we are doing
	 */
    private SearchType(String theType){
        this.theType = theType;
    }

    /**
	 * Gets the String of the type
	 *
	 */
    public String getType(){
        return this.theType;
    }
}