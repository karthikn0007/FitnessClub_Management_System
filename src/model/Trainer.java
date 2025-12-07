package model;

public class Trainer {
	
    private int trainerId;
    private String name;
    private String specialization;
    private String contact;
    private String email;
    private boolean isActive;
    
	public Trainer() {
		super();
	}

	public Trainer(int trainerId, String name, String specialization, String contact, String email, boolean isActive) {
		super();
		this.trainerId = trainerId;
		this.name = name;
		this.specialization = specialization;
		this.contact = contact;
		this.email = email;
		this.isActive = isActive;
	}
	
	public Trainer(String name, String specialization, String contact, String email, boolean isActive) {
		super();
		this.name = name;
		this.specialization = specialization;
		this.contact = contact;
		this.email = email;
		this.isActive = isActive;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
    
    
	

}
