package com.example.demo.entity;

public class Image {
	long id;
	String name;
    String image;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Image(long id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}

}
