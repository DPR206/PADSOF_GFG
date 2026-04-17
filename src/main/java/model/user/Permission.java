package model.user;

/**
 * It defines the user types
 * @author Sofía C.L.
 * @version 1.0
 */
public enum Permission{
	/** Permission to perform store actions */
    STORE("model/store"),
    /** Permission to perform order actions */
    ORDER("model/order"),
    /** Permission to perform exchange actions */
    EXCHANGE("model/exchange");

	/**
     * The string that define the permissions
     */
    private final String meaning;

    /**
     * Constructor of the enum
     * @param meaning, the string that defines the permissions
     */
    Permission(String meaning){
        this.meaning = meaning;
    }

    /**
     * Gets the meaning
     * @return a string with the meaning
     */
    public String getMeaning(){
        return this.meaning;
    }
}