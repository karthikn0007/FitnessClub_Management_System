package dao;



import java.sql.*;
import java.sql.Date;
import java.util.*;
import model.Member;

public class MemberDAO {

    // Add Member
    public void addMember(Member member){
        String sql = "INSERT INTO member (name, contact, email, address, date_joined, isActive) VALUES (?, ?, ?, ?, ?, ?)";
        try
              {
        	
        	Connection con = DBConnection.getConnection();
        	PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, member.getName());
            ps.setString(2, member.getContact());
            ps.setString(3, member.getEmail());
            ps.setString(4, member.getAddress());
            ps.setDate(5, Date.valueOf(member.getDateJoined()));
            ps.setBoolean(6, member.isActive());

            ps.executeUpdate();
            System.out.println("Member added successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding member: " + e.getMessage());
        }
    }

    // Update Member
    public void updateMember(int id, String field, String value) {
        String sql = "UPDATE member SET " + field + " = ? WHERE memberId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, value);
            ps.setInt(2, id);

            int result = ps.executeUpdate();
            System.out.println(result > 0 ? " Member updated successfully!" : " Member not found!");

        } catch (SQLException e) {
            System.out.println(" Error updating member: " + e.getMessage());
        }
    }
    
    
    
    public void deactivateMember(int id)  {
        String sql = "UPDATE member SET isActive=FALSE WHERE memberId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Member deactivated." : "Member ID not found.");

        } catch (SQLException e) {
            System.out.println("DB Error (deactivateMember): " + e.getMessage());
        }
    }

    // Get Member by ID
    public Member getMemberById(int id) {
        String sql = "SELECT * FROM member WHERE memberId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Member(
                    rs.getInt("memberId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getDate("date_joined").toLocalDate(),
                    rs.getBoolean("isActive")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error fetching member: " + e.getMessage());
        }
        return null;
    }

    // Get All Members
    public List<Member> getAllMembers() {
        List<Member> list = new ArrayList<>();
        String sql = "SELECT * FROM member";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Member(
                    rs.getInt("memberId"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getDate("date_joined").toLocalDate(),
                    rs.getBoolean("isActive")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching members: " + e.getMessage());
        }
        return list;
    }
}