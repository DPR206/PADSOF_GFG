package user;
import java.time.LocalDate;
import java.util.*;


import product.*;
/**
 * Class Name: User
 * <p>
 * Description: It defines the user of the app
 * @author Sofía C.L.
 * @version 1.0
 */
public abstract class User {
	/**ID counter for the users */
	private static int id = 1;
	/**Password of the user */
	private String pwd;
	/**Username of the user */
	private String userName;
	/**ID of the user */
	private int actualID;


	/**
	 * Creates a new user
	 *
	 * @param pwd, the password of the user
	 * @param userName, the uusername of the user
	 * @param acutalID, the ID of the user
	 */
	public User(String pwd, String userName, int actualID) {
		this.pwd = pwd;
		this.userName = userName;
		this.actualID = actualID;
	}

	/**
	 * Creates a new user, but for other classes
	 *
	 * @param pwd, the password of the user
	 * @param userName, the uusername of the user
	 */
	public User(String pwd, String userName) {
		this(pwd, userName, User.id);
		User.id++;
	}
	
	/**
	 * Creates a new user without id, password or username
	 */
	public User()
	{
		this(null, null, 0);
	}
	
	/**
	 * Changes the password of the user
	 *
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

	/**
	 * Gets the username of the user
	 *
	 *
	 */
	public String getUserName(){
		return this.userName;
	}
}