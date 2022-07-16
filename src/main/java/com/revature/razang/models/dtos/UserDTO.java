package com.revature.razang.models.dtos;

import java.sql.Date;

/** A Data Transfer version of User to
 * @author Raza Ghulam
 * @author Colby Tang
 */
public class UserDTO {
	private int userId; 
	private String username;
	private Date birthDate; 
	private String email; 
	private String phone; 

    public UserDTO(int userId, String username, Date birthDate, String email, String phone) {
		this.userId = userId;
		this.username = username;
		this.birthDate = birthDate;
		this.email = email;
		this.phone = phone;
	}

	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		String retString = "UserId={0}, Username={1}, BirthDate={2}, Email={3}, Phone={4}";
		retString = String.format(retString, getUserId(), getUsername(), getBirthDate(), getEmail(), getPhone());
		return retString;
	}
	
	/** 
	 * @return int
	 */
	public int getUserId() {
		return userId;
	}
	
	/** 
	 * @param customer_id
	 */
	public void setUserId(int customer_id) {
		this.userId = customer_id;
	}
	
	/** 
	 * @return String
	 */
	public String getUsername() {
		return username;
	}
	
	/** 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/** 
	 * @return Date
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	
	/** 
	 * @param birthDate
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	/** 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	
	/** 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/** 
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}
	
	/** 
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
}