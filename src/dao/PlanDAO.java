package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Plan;

public class PlanDAO {

    // Add Plan
    public void addPlan(Plan plan) {
        String sql = "INSERT INTO plan (name, description, duration_weeks) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, plan.getName());
            ps.setString(2, plan.getDescription());
            ps.setInt(3, plan.getDurationWeeks());
            ps.executeUpdate();

            System.out.println("Plan added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding plan: " + e.getMessage());
        }
    }

    // Update Plan
//    public void updatePlan(Plan plan) {
//        String sql = "UPDATE plan SET name=?, description=?, duration_weeks=? WHERE planId=?";
//
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, plan.getName());
//            ps.setString(2, plan.getDescription());
//            ps.setInt(3, plan.getDurationWeeks());
//            ps.setInt(4, plan.getPlanId());
//
//            int rows = ps.executeUpdate();
//            System.out.println(rows > 0 ? "Plan updated!" : "Plan ID not found!");
//
//        } catch (SQLException e) {
//            System.out.println("Error updating plan: " + e.getMessage());
//        }
//    }

    
    public void updatePlan(int id, String field, String newValue) {
        String sql = "UPDATE plan SET " + field + " = ? WHERE planId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // If updating duration, convert to int
            if (field.equals("duration_weeks")) {
                ps.setInt(1, Integer.parseInt(newValue));
            } else {
                ps.setString(1, newValue);
            }
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Plan updated successfully!" : "Plan ID not found!");

        } catch (Exception e) {
            System.out.println("Error updating plan: " + e.getMessage());
        }
    }

    // Get Plan By ID
    public Plan getPlanById(int id) {
        String sql = "SELECT * FROM plan WHERE planId=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Plan(
                    rs.getInt("planId"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("duration_weeks")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching plan: " + e.getMessage());
        }
        return null;
    }

    // Get All Plans
    public List<Plan> getAllPlans() {
        List<Plan> list = new ArrayList<>();
        String sql = "SELECT * FROM plan";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Plan(
                    rs.getInt("planId"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("duration_weeks")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching plans: " + e.getMessage());
        }
        return list;
    }
}
