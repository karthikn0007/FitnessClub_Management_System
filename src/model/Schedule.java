package model;

import java.sql.Date;
import java.sql.Time;

public class Schedule {
	
    private int scheduleId;
    private int memberId;
    private int trainerId;
    private Date sessionDate;
    private Time sessionTime;
    private int durationMinutes;
    
	public Schedule() {
		super();
	}

	public Schedule(int scheduleId, int memberId, int trainerId, Date sessionDate, Time sessionTime,
			int durationMinutes) {
		super();
		this.scheduleId = scheduleId;
		this.memberId = memberId;
		this.trainerId = trainerId;
		this.sessionDate = sessionDate;
		this.sessionTime = sessionTime;
		this.durationMinutes = durationMinutes;
	}
	
	public Schedule(int memberId, int trainerId, Date sessionDate, Time sessionTime,
			int durationMinutes) {
		super();
		
		this.memberId = memberId;
		this.trainerId = trainerId;
		this.sessionDate = sessionDate;
		this.sessionTime = sessionTime;
		this.durationMinutes = durationMinutes;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public Date getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}

	public Time getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(Time sessionTime) {
		this.sessionTime = sessionTime;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	
	public String toString() {
        return scheduleId + " | Member: " + memberId + " | Trainer: " + trainerId +
               " | Date: " + sessionDate + " | Time: " + sessionTime +
               " | Duration: " + durationMinutes + " mins";
    }
    
    
    
    

}
