package model.utilities.exceptions;

/**
 * It defines the exception for when a dni isn't valid
 * @author Ana O.R.
 * @version 1.0
 */
public class InvalidDni extends Exception {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This exception's constructor
     */
    public InvalidDni() {
    }


    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * It returns the exception's information
     * @return the exception's information
     */
    public String toString() {
        return "A DNI must have 8 numbers and an uppercase letter (eg. 12345678A)";
    }
}