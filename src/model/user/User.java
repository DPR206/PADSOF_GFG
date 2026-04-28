package model.user;

import model.exchange.Exchange;
import model.search.SearchStoreProducts;
import model.search.Searcher;
import model.utilities.Utility;
import model.utilities.exceptions.InvalidId;
import model.utilities.exceptions.PasswordNotValid;

import java.io.Serial;
import java.io.Serializable;

/**
 * It defines the user of the app
 * @author Sofía C.L.
 * @version 1.0
 */
public abstract class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    /** ID counter for the users */
    static public int totalId = 1;
    /** The user's user type */
    private final UserType type;
    /** Password of the user */
    private String pwd;
    /** Username of the user */
    private String userName;
    /** ID of the user */
    private String actualID;
    /** The user's searcher */
    private Searcher searching;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Creates a new user
     * @param type      the user's user type
     * @param pwd,      the password of the user
     * @param userName, the username of the user
     * @param actualID, the ID of the user
     * @param asc       the results' order in the search
     */
    public User(UserType type, String pwd, String userName, String actualID, boolean asc) {
        this.type = type;
        //DEBUG: System.out.println("Password will be: " + pwd);
        this.pwd = pwd;
        this.userName = userName;
        this.actualID = actualID;
        this.searching = new Searcher(new SearchStoreProducts(asc));
        //DEBUG: System.out.println("Adding user...");
        // Store.getInstance().addUser(this); <- Antes iba aquí pero en verdad no guardamos los unregistered
    }

    /**
     * Creates a new user, but for other classes
     * @param type      the user's user type
     * @param pwd,      the password of the user
     * @param userName, the username of the user
     * @param asc       the results' order in the search
     */
    public User(UserType type, String pwd, String userName, boolean asc) {
        this(type, pwd, userName, type.getSymbol() + String.format("%06d", ++totalId), asc);
    }

    /**
     * Creates a new user without id, password or username
     */
    public User() {
        this(UserType.UNREGISTERED_CLIENT, null, null, UserType.UNKNOWN.getSymbol() + String.format("%06d", 0), true);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It sets the total id of the users
     * @param newTotalId the totalId
     */
    public static void setTotalId(int newTotalId) {
        Exchange.totalId = newTotalId;
    }

    /**
     * Changes the password of the user
     * @param newPwd, the password of the user
     */
    public void changePassword(String newPwd) throws PasswordNotValid, InvalidId {
        Utility utility = new Utility();
        if (utility.securePassword(newPwd)) {
            this.pwd = newPwd;
        } else {
            System.out.println(
                    "Make sure your password has: \n " + "-At least 8 characters\n" + "-Upper case letters\n" +
                    "-Lower case letters\n" + "-Numbers\n" + "-Special characters\n");
        }
    }

    /**
     * Changes the order of the search (ascendant or descendant)
     * @param bool, determines the order (ascendant or descendant) on which the products appear
     */
    public void changeSearchOrder(boolean bool) {
        this.searching.changeProductOrder(bool);
    }

    /**
     * It toggles the search order between ascending and descending
     */
    public void toggleSearchOrder() {
        this.searching.toggleProductOrder();
    }

    /**
     * Adds a new price filter to the user
     * @param min, minimum price to search
     * @param max, maximum price to search
     */
    public void addPriceFilter(double min, double max) {
        this.searching.getStoreSearcher().addPriceFilter(min, max);
    }

    /**
     * Adds a new punctuation filter to the user
     * @param min, minimum punctuation to search
     * @param max, maximum punctuation to search
     */
    public void addPunctuationFilter(int min, int max) {
        this.searching.getStoreSearcher().addPunctuationFilter(min, max);
    }

    /**
     * It prints the user's info in a cyphered way
     */
    public void printInfo() {
        // https://stackoverflow.com/questions/2804827/create-a-string-with-n-characters
        String cypheredPassword = new String(new char[this.pwd.length()]).replace('\0', '#');

        System.out.print("Username" + this.userName);
        System.out.println("Password:" + cypheredPassword + "\n");
    }

    /**
     * It gets the user's actual ID (String)
     * @return the user's id
     */
    public String getId() {
        return this.actualID;
    }

    /**
     * Obtains the password of the user
     * @return the user's password
     */
    public String getPassword() {
        return this.pwd;
    }

    /**
     * Obtains the user's searcher
     * @return the user's searcher
     */
    public Searcher getSearcher() {
        return this.searching;
    }

    /**
     * It gets the user's user type
     * @return the user's user type
     */
    public UserType getType() {
        return this.type;
    }

    /**
     * Gets the username of the user
     * @return the user's name
     */
    public String getUserName() {
        return this.userName;
    }
}