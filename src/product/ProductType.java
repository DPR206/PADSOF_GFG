package product;

/**
 * Enum Name: ConservationStatus
 * <p>
 * Description: It defines the conservations status
 * @author Ana O.R.
 * @version 1.0
 */
public enum ProductType {
    /** Comic product type */
    COMIC("C"),
    /** Figurine product type */
    FIGURINE("F"),
    /** Game product type */
    GAME("G");

    /**
     * The characters that define this type in the save file
     */
    private String symbol;

    ProductType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the enumeration's symbol
     * @return the enumeration's symbol
     */
    public String getSymbol() {
        return symbol;
    }
}