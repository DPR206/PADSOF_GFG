package model.utilities.exceptions;

/**
 * It defines the exception for when a username is already taken
 * @author Ana O.R.
 * @version 1.0
 */
public class UsernameTaken extends Exception {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This exception's constructor
     */
    public UsernameTaken() {
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * It returns the exception's information
     * @return the exception's information
     */
    @Override
    public String toString() {
        return "This username is already taken";
    }
}