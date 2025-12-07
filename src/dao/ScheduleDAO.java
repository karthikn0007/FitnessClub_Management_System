package dao;
import java.sql.Date;
import java.sql.*;
import java.time.*;

import java.util.*;

import model.Schedule;
import exception.TrainerNotAvailableException;
public class ScheduleDAO {

	Connection con = DBConnection.getConnection();
	
	private boolean isTrainerAvailable(int trainerId, Date date, Time time) throws SQLException {
        String sql = "SELECT * FROM schedule WHERE trainerId = ? AND session_date = ? AND session_time = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, trainerId);
            ps.setDate(2, date);
            ps.setTime(3, time);

            ResultSet rs = ps.executeQuery();
            return !rs.next(); // true = available
        }
    }
	
	  // Add Schedule
	public void addSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedule (memberId, trainerId, session_date, session_time, duration_minutes) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {

            // Check trainer availability before inserting
            if (!isTrainerAvailable(schedule.getTrainerId(), schedule.getSessionDate(), schedule.getSessionTime())) {
                throw new TrainerNotAvailableException("Trainer is not available at this time!");
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, schedule.getMemberId());
                ps.setInt(2, schedule.getTrainerId());
                ps.setDate(3, schedule.getSessionDate());
                ps.setTime(4, schedule.getSessionTime());
                ps.setInt(5, schedule.getDurationMinutes());

                ps.executeUpdate();
                System.out.println("✅ Schedule added successfully!");
            }

        } catch (TrainerNotAvailableException e) {
            System.out.println("⚠️ " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Error adding schedule: " + e.getMessage());
        }
    }

	public void updateSchedule(int id,String field,String newValue) {
		// TODO Auto-generated method stub
		
		
		String sql = "UPDATE schedule SET " + field + " = ? WHERE scheduleId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
        	
        	if(field.equals("session_date")) {
        		ps.setDate(1, Date.valueOf(newValue));
                ps.setInt(2, id);
        	}
        	else if(field.equals("session_time")) {
        		ps.setTime(1, Time.valueOf(newValue));
                ps.setInt(2, id);
        	}
        	else {
        		ps.setInt(1, Integer.parseInt(newValue));
                ps.setInt(2, id);
        	}


            int result = ps.executeUpdate();
            System.out.println(result > 0 ? " Session updated successfully!" : " Session not found!");

        } catch (SQLException e) {
            System.out.println(" Error updating schedule: " + e.getMessage());
        }
	}
	
	// Viewing Schedule Details 
	public Schedule getScheduleById(int scheduleId) {
        String sql = "SELECT * FROM schedule WHERE scheduleId = ?";
        try {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, scheduleId);
        ResultSet rs = pst.executeQuery();

        Schedule schedule = null;
        if (rs.next()) {
            schedule = new Schedule(
                rs.getInt("scheduleId"),
                rs.getInt("trainerId"),
                rs.getInt("memberId"),
                rs.getDate("session_date"),
                rs.getTime("session_time"),
                rs.getInt("duration_minutes")
            );
        }
        con.close();
        return schedule;
        }
        catch (SQLException e) {
            System.out.println("Error adding schedule: " + e.getMessage());
        }
        return null;
    }
	
	//Viewing schedule details by MemberId 
	public Schedule getMemberById(int id) 
	{
		String sql = "select * from schedule where memberId = ?;";
		try {
		PreparedStatement pst = con.prepareStatement(sql);
		
		pst.setInt(1,id);
		ResultSet rs = pst.executeQuery();

		if(rs.next())
		{
			return new Schedule(
					rs.getInt("scheduleId"),
	                rs.getInt("trainerId"),
	                rs.getInt("memberId"),
	                rs.getDate("session_date"),
	                rs.getTime("session_time"),
	                rs.getInt("duration_minutes"));
		}
		
		}
		catch (SQLException e) {
            System.out.println("Error adding schedule: " + e.getMessage());
        }
		return null;
	}
	
	// Viewing Schedule Details by Member ID
//    public List<Schedule> getSchedulesByMemberId(int memberId) throws SQLException {
//        String sql = "SELECT * FROM schedule WHERE memberId = ?";
//        Connection con = DBConnection.getConnection();
//        PreparedStatement pst = con.prepareStatement(sql);
//        pst.setInt(1, memberId);
//        ResultSet rs = pst.executeQuery();
//
//        List<Schedule> schedules = new ArrayList<>();
//        while (rs.next()) {
//            schedules.add(new Schedule(
//                rs.getInt("scheduleId"),
//                rs.getInt("trainerId"),
//                rs.getInt("memberId"),
//                rs.getDate("sessionDate").toLocalDate(),
//                rs.getTime("sessionTime").toLocalTime(),
//                rs.getInt("durationMinutes")
//            ));
//        }
//        con.close();
//        return schedules;
//    }

    // Viewing Schedule by Trainer ID
//    public List<Schedule> getSchedulesByTrainerId(int trainerId) throws SQLException {
//        String sql = "SELECT * FROM schedule WHERE trainerId = ?";
//        Connection con = DBConnection.getConnection();
//        PreparedStatement pst = con.prepareStatement(sql);
//        pst.setInt(1, trainerId);
//        ResultSet rs = pst.executeQuery();
//
//        List<Schedule> schedules = new ArrayList<>();
//        while (rs.next()) {
//            schedules.add(new Schedule(
//                rs.getInt("scheduleId"),
//                rs.getInt("trainerId"),
//                rs.getInt("memberId"),
//                rs.getDate("sessionDate").toLocalDate(),
//                rs.getTime("sessionTime").toLocalTime(),
//                rs.getInt("durationMinutes")
//            ));
//        }
//        con.close();
//        return schedules;
//    }

    // Viewing All Schedules
    public List<Schedule> getAllSchedules()  {
        String sql = "SELECT * FROM schedule";
        try {
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        List<Schedule> schedules = new ArrayList<>();
        while (rs.next()) {
            schedules.add(new Schedule(
                rs.getInt("scheduleId"),
                rs.getInt("trainerId"),
                rs.getInt("memberId"),
                rs.getDate("session_date"),
                rs.getTime("session_time"),
                rs.getInt("duration_minutes")
            ));
        }
        con.close();
        return schedules;
        }
        catch (SQLException e) {
            System.out.println("Error adding schedule: " + e.getMessage());
        }
        return null;
        
    }
    
}
	
