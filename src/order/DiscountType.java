package order;

/**
 * It defines the discount types
 * @author Ana O.R.
 * @version 1.0
 */
public enum DiscountType {
    /** Fixed percentage discount type */
    FIXED_PERCENTAGE("F"),
    /** Gift discount type */
    GIFT("G"),
    /** Quantity discount type */
    QUANTITY("Q"),
    /** Volume discount type */
    VOLUME("V");

    /**
     * The characters that define this type in the save file
     */
    private final String symbol;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A discount type's constructor
     * @param symbol the symbol that defines said enumeration
     */
    DiscountType(String symbol) {
        this.symbol = symbol;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the enumeration's symbol
     * @return the enumeration's symbol
     */
    public String getSymbol() {
        return symbol;
    }
}