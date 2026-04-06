package user;

/**
 * It defines the user types
 * @author Sofía C.L.
 * @version 1.0
 */
public enum Permission{
    STORE("store"),
    ORDER("order"),
    EXCHANGE("exchange");

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
     *
     */
    public String getMeaning(){
        return this.meaning;
    }
}