package order;

/**
 * Enum Name: DiscountType
 * <p>
 * Description: It defines the discount coverages
 * @author Ana O.R.
 * @version 1.0
 */
public enum DiscountCoverage {
    /** Category discount coverage */
    CATEGORY("CA"),
    /** Pack discount coverage */
    PACK("PA"),
    /** Product discount coverage */
    PRODUCT("PR");

    /**
     * The characters that define this type in the save file
     */
    private final String symbol;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A discount type's constructor
     * @param symbol the symbol that defines said enumeration
     */
    DiscountCoverage(String symbol) {
        this.symbol = symbol;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Gets the enumeration's symbol
     * @return the enumeration's symbol
     */
    public String getSymbol() {
        return symbol;
    }
}