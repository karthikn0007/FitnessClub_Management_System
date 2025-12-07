package model;

public class Plan {
	
    private int planId;
    private String name;
    private String description;
    private int durationWeeks;
    
	public Plan() {
		super();
	}

	public Plan(int planId, String name, String description, int durationWeeks) {
		super();
		this.planId = planId;
		this.name = name;
		this.description = description;
		this.durationWeeks = durationWeeks;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDurationWeeks() {
		return durationWeeks;
	}

	public void setDurationWeeks(int durationWeeks) {
		this.durationWeeks = durationWeeks;
	}
    
    
    

}
