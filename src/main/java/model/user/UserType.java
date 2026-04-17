package model.user;

/**
 * It defines the user types
 * @author Ana O.R.
 * @version 1.0
 */
public enum UserType {
    /** Unregistered client */
    UNREGISTERED_CLIENT("U"),
    /** Registered client */
    REGISTERED_CLIENT("R"),
    /** Employee */
    EMPLOYEE("E"),
    /** Manager */
    MANAGER("M"),
    /** Default user type, also used for logIn */
    UNKNOWN("?");

    /**
     * The characters that define this type in the save file
     */
    private final String symbol;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A product type's constructor
     * @param symbol the symbol that defines said enumeration
     */
    UserType(String symbol) {
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