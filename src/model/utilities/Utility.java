package model.utilities;

import model.store.Store;
import model.user.RegisteredClient;
import model.user.User;
import model.utilities.exceptions.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * It implements elements such a sign in method and login method which don't belong to either classes
 * @author Sofía C.L. and Duna P.R.
 * @version 1.1.
 */
public class Utility implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Creates a new utility
     */
    public Utility() {
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * The sign in process
     * @return a new user
     */
    public User signIn(String userName, String pwd, String id, IdType idType)
            throws PasswordNotValid, UsernameTaken, InvalidDni {
        RegisteredClient rc;
        User u;

        Map<String, User> users = Store.getInstance().getUsers();

        if (users.containsKey(userName)) {
            throw new UsernameTaken();
        }
        this.securePassword(pwd);

        validId(id, idType);

        rc = new RegisteredClient(userName, id, pwd, true);

        u = rc;
        Store.getInstance().getRegisteredClients().put(userName, rc);
        return u;

    }

    /**
     * The log in process
     * @param userName the user's name
     * @param pwd      the user's password
     * @return the associated user (if there is one)
     */
    public User logIn(String userName, String pwd) {
        User u;

        //DEBUG: if (Store.getInstance().getUsers().size() < 2) {
        //DEBUG:     System.out.println("NO HAY USUARIOS FUCKKKKK");
        //DEBUG: }
        //DEBUG: for (User user : Store.getInstance().getUsers().values()) {
        //DEBUG:     System.out.println("Username: " + user.getUserName() + " Password: " + user.getPassword() + "\n");
        //DEBUG: }

        if (Store.getInstance().getUsers().containsKey(userName)) {
            u = Store.getInstance().getUsers().get(userName);
            //DEBUG: System.out.println("Password: " + u.getPassword() + " , expected: " + pwd);
            if (u.getPassword().equals(pwd)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Checks if a password is secure
     * @param psswd the password to check
     * @return true if the password is safe, false if else
     * @throws PasswordNotValid the password isn't secure
     */
    public boolean securePassword(String psswd) throws PasswordNotValid, InvalidDni {
        int upper = 0;
        int lower = 0;
        int numbers = 0;
        @SuppressWarnings ("unused") int spaces = 0;
        int specialCharacters = 0;
        boolean atLeastEightCharacters = true;
        boolean anUppercaseLetter = true;
        boolean aLowerCaseLetter = true;
        boolean aNumber = true;
        boolean aSpecialCharacter = true;
        boolean secure;

        for (char c : psswd.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upper++;
            } else if (Character.isLowerCase(c)) {
                lower++;
            } else if (Character.isDigit(c)) {
                numbers++;
            } else if (Character.isWhitespace(c)) {
                spaces++;
            } else {
                specialCharacters++;
            }
        }

        if (psswd.length() < 8) {
            secure = false;
            atLeastEightCharacters = false;
        } else if (upper <= 0) {
            secure = false;
            anUppercaseLetter = false;
        } else if (lower <= 0) {
            secure = false;
            aLowerCaseLetter = false;
        } else if (numbers <= 0) {
            secure = false;
            aNumber = false;
        } else if (specialCharacters <= 0) {
            secure = false;
            aSpecialCharacter = false;
        } else {
            secure = true;
        }

        if (!secure) {
            throw new PasswordNotValid(atLeastEightCharacters, anUppercaseLetter, aLowerCaseLetter, aNumber,
                    aSpecialCharacter);
        }

        return secure;
    }

    public void validId(String id, IdType idType) throws InvalidDni {
        switch (idType) {
            case DNI -> validDni(id);
            case NIE -> validNie(id);
            default -> throw new InvalidDni();
        }
    }

    public void validDni(String id) throws InvalidDni {
        /* (8 números y una letra = 9) */
        if (id.length() != 9) {
            throw new InvalidDni();
        }
        /* Números */
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                throw new InvalidDni();
            }
        }
        /* Letra */
        if (!Character.isUpperCase(id.charAt(8))) {
            throw new InvalidDni();
        }
        /* Algoritmo de validez: https://interior.gob
        .es/opencms/es/servicios-al-ciudadano/tramites-y-gestiones/dni/calculo-del-digito-de-control-del-nif-nie/*/
        char letra;
        int resto = Integer.parseInt(id.substring(0, 8)) % 23;
        switch (resto) {
            case 0 -> letra = 'T';
            case 1 -> letra = 'R';
            case 2 -> letra = 'W';
            case 3 -> letra = 'A';
            case 4 -> letra = 'G';
            case 5 -> letra = 'M';
            case 6 -> letra = 'Y';
            case 7 -> letra = 'F';
            case 8 -> letra = 'P';
            case 9 -> letra = 'D';
            case 10 -> letra = 'X';
            case 11 -> letra = 'B';
            case 12 -> letra = 'N';
            case 13 -> letra = 'J';
            case 14 -> letra = 'Z';
            case 15 -> letra = 'S';
            case 16 -> letra = 'Q';
            case 17 -> letra = 'V';
            case 18 -> letra = 'H';
            case 19 -> letra = 'L';
            case 20 -> letra = 'C';
            case 21 -> letra = 'K';
            case 22 -> letra = 'E';
            default -> throw new InvalidDni();
        }

        if (id.charAt(8) != letra) {
            throw new InvalidDni();
        }
    }

    public void validNie(String id) throws InvalidDni {
        int firstNum;
        /* Algoritmo de validez: https://interior.gob
        .es/opencms/es/servicios-al-ciudadano/tramites-y-gestiones/dni/calculo-del-digito-de-control-del-nif-nie/*/
        switch (id.charAt(0)) {
            case 'X' -> firstNum = 0;
            case 'Y' -> firstNum = 1;
            case 'Z' -> firstNum = 2;
            default -> throw new InvalidDni();
        }
        validDni(Character.toString(firstNum) + id.substring(1, 9));
    }
}