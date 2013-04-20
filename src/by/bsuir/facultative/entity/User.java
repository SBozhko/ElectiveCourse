/**
 * 
 */
package by.bsuir.facultative.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SBozhko
 * 
 */
public abstract class User {

	private String email;
	private String password;
	private long idUser;
	/**
	 * @return the idUser
	 */
	public long getIdUser() {
		return idUser;
	}


	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}


	private String fullName;
	

	/**
	 * 
	 */
	public User() {
		super();
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	/**
	 * @param email
	 * @param password
	 * @param idUser
	 * @param fullName
	 */
	public User(String email, String password, long idUser, String fullName) {
		this.email = email;
		this.password = password;
		this.idUser = idUser;
		this.fullName = fullName;
	}





	
}
