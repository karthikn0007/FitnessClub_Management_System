package model;

public class Package {
	
    private int packageId;
    private String name;
    private double price;
    private int planId;
    
	public Package() {
		super();
	}
	
	public Package(int packageId, String name, double price, int planId) {
		super();
		this.packageId = packageId;
		this.name = name;
		this.price = price;
		this.planId = planId;
	}
	
	public int getPackageId() {
		return packageId;
	}
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
    
    
    

}
