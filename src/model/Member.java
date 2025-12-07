package model;

import java.time.LocalDate;

public class Member {
	
    private int memberId;
    private String name;
    private String contact;
    private String email;
    private String address;
    private LocalDate dateJoined;
    private boolean isActive;
    
	public Member() {
		super();
	}

	public Member(String name, String contact, String email, String address, LocalDate dateJoined,
			boolean isActive) {
		super();
		
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.dateJoined = dateJoined;
		this.isActive = isActive;
	}
	
	public Member(int memberId, String name, String contact, String email, String address, LocalDate dateJoined,
			boolean isActive) {
		super();
		this.memberId=memberId;
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.dateJoined = dateJoined;
		this.isActive = isActive;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(LocalDate dateJoined) {
		this.dateJoined = dateJoined;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
    
    
	

}