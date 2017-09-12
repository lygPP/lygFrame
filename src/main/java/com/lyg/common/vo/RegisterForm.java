package com.lyg.common.vo;

import org.springframework.web.multipart.MultipartFile;

import com.lyg.model.User;

public class RegisterForm {
	private String userName;
	private String password;
	private String userAge;
	private String userAddress;
	private MultipartFile profilePicture;
	
	public User toUser(){
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setUserAddress(userAddress);
		user.setUserAge(userAge);
		user.setPicPath(profilePicture.getOriginalFilename());
		return user;
	}

	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
}
