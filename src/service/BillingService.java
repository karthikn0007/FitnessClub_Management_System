package service;

import java.util.*;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Scanner;
import dao.BillingDAO;
import model.Billing;
import util.ApplicationUtil;

public class BillingService {
    private BillingDAO billingDAO = new BillingDAO();
    private Scanner sc = new Scanner(System.in);

    // Generate Bill
    public double getPackagePrice(int packageId) {
    	return billingDAO.getPackagePrice(packageId);
    }
    public void generateBill(Billing billing ) {
    	
//        System.out.println("Enter billing details (memberId:packageId:billingDate(YYYY-MM-DD):amount:status):");
//        String input = sc.nextLine();
//
//        Billing bill = ApplicationUtil.parseBillingFromString(input);
//        if (bill != null) {
//            billingDAO.addBilling(bill);
//        }
    	billingDAO.addBilling(billing);
    	
    }

    // Update Bill
    public void updateBill(int billId,String field, String value) {
       billingDAO.updateBilling(billId,field,value);
    }

    // View Bill by ID,
    public Billing viewBillById(int billid) {
    	return billingDAO.getBillingById(billid);
    }

    // View Bills by Member ID
    public List<Billing>viewBillsByMemberId(int memId) {

        List<Billing> bills = billingDAO.getBillsByMemberId(memId);
        if (bills.isEmpty()) {
            return null;
        }
        else {
        	return bills;
        } 
    }

    // View All Bills
    public List<Billing> viewAllBills() {
        return billingDAO.getAllBills();
    }
}
