package com.ibm.mentor.message.request;

public class BlockUserRequest {

	private String username;
	private boolean active;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
