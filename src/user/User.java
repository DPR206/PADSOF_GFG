package user;

import search.Searcher;

/**
 * Class Name: User
 * <p>
 * Description: It defines the user of the app
 * @author Sofía C.L.
 * @version 1.0
 */
public abstract class User {
    /** ID counter for the users */
    static public int totalId = 1;
    /** Password of the user */
    private String pwd;
    /** Username of the user */
    private String userName;
    /** ID of the user */
    private String actualID;
    /** The user's user type */
    private final UserType type;
    /** The user's searcher */
    private Searcher searching;

    /**
     * Creates a new user
     * @param type the user's user type
     * @param pwd,      the password of the user
     * @param userName, the username of the user
     * @param actualID, the ID of the user
     */
    public User(UserType type,String pwd, String userName, String actualID, boolean asc) {
        this.type = type;
        this.pwd = pwd;
        this.userName = userName;
        this.actualID = actualID;
        this.searching = new Searcher(new SearchStoreProducts(asc));
    }

    /**
     * Creates a new user, but for other classes
     * @param type the user's user type
     * @param pwd,      the password of the user
     * @param userName, the username of the user
     */
    public User(UserType type, String pwd, String userName, boolean asc) {
        this(type, pwd, userName, type.getSymbol() + String.format("%06d", ++totalId), asc);
    }

    /**
     * Creates a new user without id, password or username
     */
    public User() {
        this(UserType.UNREGISTERED_CLIENT, null, null, UserType.UNKNOWN.getSymbol() + String.format("%06d", 0));
    }

    /**
     * Changes the password of the user
     * @param newPwd, the password of the user
     */
    public void changePassword(String newPwd) {
        this.pwd = pwd;
    }

    /**
     * Gets the password of the user
     *
     *
     */
    public String getPassword() {
        return this.pwd;
    }

    public Searcher getSearcher(){
        return this.searcher;
    }
    /**
     * Gets the username of the user
     *
     *
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * It gets the user's user type
     * @return the user's user type
     */
    public UserType getType() {
        return this.type;
    }
}