package com.api.dbModels;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "Tbl_User")
public class Tbl_User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String userId;	
	private String password;
	private LocalDateTime  createdDate;
	private LocalDateTime  updatedDate;
	private char isActive;
	private char isLocked;
	private char isVerified;
	public Tbl_User() {
	}

	public Tbl_User(long id, String firstName, String lastName,
			String email, String password,String userId,LocalDateTime  createdDate
			,LocalDateTime  updatedDate,char isActive,char isLocked,char isVerified) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;	
		this.password = password;
		this.userId = userId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.isActive = isActive;
		this.isLocked = isLocked;
		this.isVerified=isVerified;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName= lastName;
	}
	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email= email;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDateTime  getCaretedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime  createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDateTime  getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime  updatedDate) {
		this.updatedDate = updatedDate;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public char getIsActive() {
		return isActive;
	}

	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	
	
	
	public char getisLocked() {
		return isLocked;
	}

	public void setisLocked(char isLocked) {
		this.isLocked = isLocked;
	}
	
	
	public char getisVerified() {
		return isVerified;
	}

	public void setisVerified(char isVerified) {
		this.isVerified = isVerified;
	}
}
