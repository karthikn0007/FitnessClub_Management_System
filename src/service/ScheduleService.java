package service;
import java.sql.SQLException;
import java.time.*;
import java.util.*;

import dao.ScheduleDAO;
import model.Member;
import model.Schedule;
import util.ApplicationUtil;

public class ScheduleService 
{
	private ScheduleDAO Schedule = new ScheduleDAO();
    private Scanner sc = new Scanner(System.in);
    
//    Add Schedule Details
    public void addSchedule(String input) {

        Schedule schedule = ApplicationUtil.parseScheduleFromString(input);
        if (schedule != null) 
        {
            Schedule.addSchedule(schedule);
        }
    }
//    
    
    public void updateSchedule(int id,String field,String newValue) {
        

        Schedule.updateSchedule(id, field, newValue);
    }
    
    
//    View Single Schedule
    public Schedule viewScheduleById(int scheduleId){
        
            return Schedule.getScheduleById(scheduleId);
            
}
    
    
//    View Schedules by Member ID
    public Schedule viewSchedulesByMemberId(int memberId) {
        
        	return Schedule.getScheduleById(memberId);
            
        
    }
    
    // View Schedules by Trainer ID
    public Schedule viewSchedulesByTrainerId(int trainerId) {
        
        	return Schedule.getScheduleById(trainerId);
            
         
    }

    // View All Schedules
    public List<Schedule> viewAllSchedules() {
        
            List<Schedule> schedules = Schedule.getAllSchedules();
            return schedules;
         
    }
}
