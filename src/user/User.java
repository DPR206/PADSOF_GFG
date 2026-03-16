package user;
import java.time.LocalDate;
import java.util.*;


import product.*;

public abstract class User {
	private static int id = 0;
	
	private String pwd;
	private String userName;
	private int actualID;
	
	public User(String pwd, String userName, int actualID) {
		this.pwd = pwd;
		this.userName = userName;
		this.actualID = actualID;
	}
	
	public User(String pwd, String userName) {
		this(pwd, userName, User.id);
		User.id++;
	} 
	
	public void changePassword(String newPwd) {
		this.pwd = pwd;
	}
	
	public String getPassword() {
		return this.pwd;
	}	
		
		
}

