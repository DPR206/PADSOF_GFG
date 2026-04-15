package search;

/**
    * It implements the type of searches that will be done
    * @author Sofía C.L.
    * @version 1.3
    * @see Searcher
    */
public enum SearchType{
	/** A search made within the store */
    S_STORE("store"),
    /** A search made within the orders */
    S_ORDER("order"),
    /** A search made within the exchanges */
    S_EXCHANGE("exchange"),
    /** A search made within the packs */
    S_PACK("pack"),
    /** A search made within the employees */
    S_EMPLOYEE("employee"),
    /** A search made within the second-hand products */
    S_SECOND_HAND("second hand"),
    /** A search made within the valuation products */
    S_VALUATION_PROD("valuation prod");

    private final String theType;

    /**
	 * Creates the enum and initializes the name of the type of search, asc, determines if the searches are ascendant
     * or descendant
     * @param theType, determines the type of search we are doing
	 */
    SearchType(String theType){
        this.theType = theType;
    }

    /**
	 * Gets the String of the type
     * @return the String of the type of search
	 *
	 */
    public String getType(){
        return this.theType;
    }
}