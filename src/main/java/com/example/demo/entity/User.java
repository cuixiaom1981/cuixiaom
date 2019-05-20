package com.example.demo.entity;

import java.io.Serializable;

public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5055722426452031344L;

	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
long id;
	String name;
String password;
String role;
String image;

public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public User(long id,String name, String password,String role,String image) {
	super();
	this.id = id;
	this.role=role;
	this.name = name;
	this.password = password;
	this.image=image;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
