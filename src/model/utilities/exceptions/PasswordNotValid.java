package model.utilities.exceptions;

/**
 * It defines the exception for when a password isn't secure
 * @author Ana O.R.
 * @version 1.0
 */
public class PasswordNotValid extends Exception {
    /** Whether the password has at least eight characters */
    private final boolean atLeastEightCharacters;
    /** Whether the password has uppercase letters */
    private final boolean anUppercaseLetter;
    /** Whether the password has lowercase letters */
    private final boolean aLowerCaseLetter;
    /** Whether the password has numbers */
    private final boolean aNumber;
    /** Whether the password has special characters */
    private final boolean aSpecialCharacter;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This exception's constructor
     * @param atLeastEightCharacters whether the password has at least eight characters
     * @param anUppercaseLetter      whether the password has uppercase letters
     * @param aLowerCaseLetter       whether the password has lowercase letters
     * @param aNumber                whether the password has numbers
     * @param aSpecialCharacter      whether the password has special characters
     */
    public PasswordNotValid(boolean atLeastEightCharacters, boolean anUppercaseLetter, boolean aLowerCaseLetter,
                            boolean aNumber, boolean aSpecialCharacter) {
        this.atLeastEightCharacters = atLeastEightCharacters;
        this.anUppercaseLetter = anUppercaseLetter;
        this.aLowerCaseLetter = aLowerCaseLetter;
        this.aNumber = aNumber;
        this.aSpecialCharacter = aSpecialCharacter;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * It returns the exception's information
     * @return the exception's information
     */
    @Override
    public String toString() {
        String firstRequisite = (atLeastEightCharacters ? "[x]" : "[ ]") + " At least 8 characters";
        String secondRequisite = (anUppercaseLetter ? "[x]" : "[ ]") + " Upper case letters";
        String thirdRequisite = (aLowerCaseLetter ? "[x]" : "[ ]") + " Lower case letters";
        String fourthRequisite = (aNumber ? "[x]" : "[ ]") + " Numbers";
        String fifthRequisite = (aSpecialCharacter ? "[x]" : "[ ]") + " Special characters";
        return "Your password complied with the following requisites:\n" + firstRequisite + "\n" + secondRequisite +
               "\n" + thirdRequisite + "\n" + fourthRequisite + "\n" + fifthRequisite;
    }
}