package dao;

import java.sql.*;
import java.util.*;
import model.Package;

public class PackageDAO {

    // 1️⃣ Add Package
    public void addPackage(Package pack) {
        String sql = "INSERT INTO package (name, price, planId) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, pack.getName());
            ps.setDouble(2, pack.getPrice());
            ps.setInt(3, pack.getPlanId()); // Store selected planId

            ps.executeUpdate();
            System.out.println("Package added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding package: " + e.getMessage());
        }
    }

    // 2️⃣ Update Package
    public void updatePackage(int id, String field, String newValue) {

        String sql = "UPDATE package SET " + field + " = ? WHERE packageId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (field.equalsIgnoreCase("price")) {
                ps.setDouble(1, Double.parseDouble(newValue)); // Convert string to double
            } else {
                ps.setString(1, newValue);
            }

            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            System.out.println(rows > 0 ? "Package updated successfully!" : "Package ID not found!");

        } catch (SQLException e) {
            System.out.println("Error updating package: " + e.getMessage());
        }
    }

    // 3️⃣ Get Package By ID
    public Package getPackageById(int id) {
        String sql = "SELECT * FROM package WHERE packageId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Package(
                        rs.getInt("packageId"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("planId")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching package: " + e.getMessage());
        }
        return null;
    }

    // 4️⃣ Get All Packages
    public List<Package> getAllPackages() {

        List<Package> list = new ArrayList<>();
        String sql = "SELECT * FROM package";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Package(
                        rs.getInt("packageId"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("planId")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching packages: " + e.getMessage());
        }
        return list;
    }
    
    
}
