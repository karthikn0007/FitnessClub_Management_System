package dao;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Trainer;

public class TrainerDAO {

    // Add Trainer
    public void addTrainer(Trainer trainer) {
        String sql = "INSERT INTO trainer (name, specialization, contact, email, isActive) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, trainer.getName());
            ps.setString(2, trainer.getSpecialization());
            ps.setString(3, trainer.getContact());
            ps.setString(4, trainer.getEmail());
            ps.setBoolean(5, trainer.isActive());

            ps.executeUpdate();
            System.out.println("Trainer added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding trainer: " + e.getMessage());
            
        }
    }

    // Update single field (used in your service)
    public void updateTrainer(int id, String field, String value) {
        String sql = "UPDATE trainer SET " + field + " = ? WHERE trainerId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, value);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Trainer updated successfully!" : "Trainer not found!");

        } catch (SQLException e) {
            System.out.println("Error updating trainer: " + e.getMessage());
        }
    }

    // Deactivate Trainer
    public void deactivateTrainer(int id) {
        String sql = "UPDATE trainer SET isActive = FALSE WHERE trainerId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Trainer Deactivated" : "Trainer ID not found!");

        } catch (SQLException e) {
            System.out.println("DB Error (deactivateTrainer): " + e.getMessage());
        }
    }

    // Get Trainer by ID
    public Trainer getTrainerById(int id) {
        String sql = "SELECT * FROM trainer WHERE trainerId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Trainer(
                    rs.getInt("trainerId"),
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getBoolean("isActive")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching trainer: " + e.getMessage());
        }
        return null;
    }

    // Get All Trainers
    public List<Trainer> getAllTrainers() {
        List<Trainer> list = new ArrayList<>();
        String sql = "SELECT * FROM trainer";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Trainer(
                    rs.getInt("trainerId"),
                    rs.getString("name"),
                    rs.getString("specialization"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getBoolean("isActive")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching trainers: " + e.getMessage());
        }
        return list;
    }
}
