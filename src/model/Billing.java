package model;

import java.sql.Date;
//import java.time.LocalDate;

public class Billing {
	
    private int billId;
    private int memberId;
    private int packageId;
    private Date billingDate;
    private double amount;
    private String billingStatus;
    
	public Billing() {
		super();
	}

	public Billing(int billId, int memberId, int packageId, Date billingDate, double amount, String billingStatus) {
		super();
		this.billId = billId;
		this.memberId = memberId;
		this.packageId = packageId;
		this.billingDate = billingDate;
		this.amount = amount;
		this.billingStatus = billingStatus;
	}
	
	public Billing(int memberId, int packageId, Date billingDate, double amount, String billingStatus) {
		super();
		this.memberId = memberId;
		this.packageId = packageId;
		this.billingDate = billingDate;
		this.amount = amount;
		this.billingStatus = billingStatus;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(String billingStatus) {
		this.billingStatus = billingStatus;
	}
	
	
    
    
    
    

}
