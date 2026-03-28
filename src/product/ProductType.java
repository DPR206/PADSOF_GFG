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
    GAME("G"),
    /** Second hand product type */
    SECONDHAND("SH");

    /**
     * The characters that define this type in the save file
     */
    private final String symbol;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A product type's constructor
     * @param symbol the symbol that defines said enumeration
     */
    ProductType(String symbol) {
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