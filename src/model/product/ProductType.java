package model.product;

/**
 * It defines the store product types
 * @author Ana O.R.
 * @version 1.2
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
     * It gets the enumeration's symbol
     * @return the enumeration's symbol
     */
    public String getSymbol() {
        return symbol;
    }
}