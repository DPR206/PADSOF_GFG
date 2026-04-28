package model.utilities.exceptions;

import model.utilities.IdType;

/**
 * It defines the exception for when a dni isn't valid
 * @author Ana O.R.
 * @version 1.0
 */
public class InvalidId extends Exception {
    private final IdType idType;
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This exception's constructor
     */
    public InvalidId(IdType idType) {
        this.idType = idType;
    }


    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * It returns the exception's information
     * @return the exception's information
     */
    public String toString() {
        if (idType == IdType.DNI) {
            return "ID wasn't valid:\n" + "A DNI must have 8 numbers and an uppercase letter (eg. 12345678A)";
        } else if (idType == IdType.NIE) {
            return "ID wasn't valid:\n" + "A NIE must have a letter, 7 numbers and another letter (eg. X1234567A)";
        } else {
            return "ID wasn't valid\n";
        }
    }
}