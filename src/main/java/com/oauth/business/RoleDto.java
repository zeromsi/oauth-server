package com.oauth.business;

public class RoleDto {
	private int id;
	private String name;
	
	public int getId() {
		return id; 
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public RoleDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "RoleVM [id=" + id + ", name=" + name + "]";
	}
	public RoleDto() {
		super();
	}

	
	
}