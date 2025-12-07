
package dao;

import java.sql.Date;
import java.sql.*;

import java.util.*;
import model.Billing;


public class BillingDAO {

    // Add Billing (Generate Bill)
	public double getPackagePrice(int packageId) {
	    String sql = "SELECT price FROM package WHERE packageId = ?";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, packageId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            return rs.getDouble("price");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error fetching price: " + e.getMessage());
	    }
	    return -1; // return -1 if not found
	}
	
	
	
    public void addBilling(Billing bill) {
        String sql = "INSERT INTO billing (memberId, packageId, billingDate, amount, billingStatus) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, bill.getMemberId());
            ps.setInt(2, bill.getPackageId());

//            if (bill.getBillingDate() !=null){
            ps.setDate(3,bill.getBillingDate());
//            else
//                ps.setNull(3, Types.DATE);
//            ps.setObject(3,bill.getBillingDate());

            ps.setDouble(4, bill.getAmount());
            ps.setString(5, bill.getBillingStatus());

            ps.executeUpdate();
            System.out.println("Bill generated successfully!");

        } catch (SQLException e) {
            System.out.println("Error generating bill: " + e.getMessage());
        }
    }

    // Update Billing
    public void updateBilling(int billId, String field, String value) {
        String sql = "UPDATE billing SET " + field + " = ? WHERE billId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, value);
            ps.setInt(2, billId);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Bill updated successfully!" : "Bill not found!");

        } catch (SQLException e) {
            System.out.println("Error updating bill: " + e.getMessage());
        }
    }

    // Get Bill by ID
    public Billing getBillingById(int billId) {
        String sql = "SELECT * FROM billing WHERE billId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, billId);
            ResultSet rs = ps.executeQuery();

//            if (rs.next()) {
//               Date sqlDate = rs.getDate("billingDate");
//                LocalDate billingDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;
//                
//                return new Billing(
//                        rs.getInt("billId"),
//                        rs.getInt("memberId"),
//                        rs.getInt("packageId"),
//                   billingDate,
////                        rs.getLocalDate("billingDate");
//                        rs.getDouble("amount"),
//                        rs.getString("billingStatus")
//                );
//            }
            
            if (rs.next()) {
                return new Billing(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getDate(4),
                    rs.getDouble(5),
                    rs.getString(6)
                    
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching bill: " + e.getMessage());
        }
        return null;
    }

    // Get Bills by Member ID
    public List<Billing> getBillsByMemberId(int memberId) {
        List<Billing> list = new ArrayList<>();
        String sql = "SELECT * FROM billing WHERE memberId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, memberId);
            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Date sqlDate = rs.getDate("billingDate");
//                LocalDate billingDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;
//
//                list.add(new Billing(
//                        rs.getInt("billId"),
//                        rs.getInt("memberId"),
//                        rs.getInt("packageId"),
//                        billingDate,
//                        rs.getDouble("amount"),
//                        rs.getString("billingStatus")
//                ));
//            }
            
            while (rs.next()) {
                list.add(new Billing(
                		rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDouble(5),
                        rs.getString(6)
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching bills by member: " + e.getMessage());
        }
        return list;
    }

    // Get All Bills
    public List<Billing> getAllBills() {
        List<Billing> list = new ArrayList<>();
        String sql = "SELECT * FROM billing";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

//            while (rs.next()) {
//                Date sqlDate = rs.getDate("billingDate");
//                LocalDate billingDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;
//
//                list.add(new Billing(
//                        rs.getInt("billId"),
//                        rs.getInt("memberId"),
//                        rs.getInt("packageId"),
//                        billingDate,
//                        rs.getDouble("amount"),
//                        rs.getString("billingStatus")
//                ));
//            }
        	
        	while (rs.next()) {
                list.add(new Billing(
                		rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDouble(5),
                        rs.getString(6)
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error fetching all bills: " + e.getMessage());
        }
        return list;
    }
}

