package com.kishonnishant.cartcontrollers;

import java.io.Serializable;

public class User implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String userName;
private String email;
//change phone Number
private String phoneNumber;
private String password;

public User(String userName, String email, String phoneNumber, String password) {
	super();
	this.userName = userName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.password = password;
}
/**
 * @return the userName
 */

public String getUserName() {
	return userName;
}
/**
 * @param userName the userName to set
 */
public void setUserName(String userName) {
	this.userName = userName;
}
/**
 * @return the email
 */
public String getEmail() {
	return email;
}
/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return the phoneNumber
 */
public String getPhoneNumber() {
	return phoneNumber;
}
/**
 * @param phoneNumber the phoneNumber to set
 */
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
/**
 * @return the password
 */
public String getPassword() {
	return password;
}
/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "User [userName=" + userName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", password=" + password
			+ "]";
}

}
