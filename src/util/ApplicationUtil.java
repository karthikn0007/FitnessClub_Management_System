package util;

import model.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class ApplicationUtil {

    // ✅ Parse Member input: name:contact:email:address
    public static Member parseMemberFromString(String input) {
        try {
            String[] data = input.split(":");
            if (data.length < 4) {
                System.out.println(" Invalid member input format!");
                return null;
            }

            Member member = new Member();
            
            member.setName(data[0]);
            member.setContact(data[1]);
            member.setEmail(data[2]);
            member.setAddress(data[3]);
            member.setDateJoined(LocalDate.now()); // auto current date
            member.setActive(true);

            return member;

        } catch (Exception e) {
            System.out.println(" Error parsing member: " + e.getMessage());
            return null;
        }
    }

    // ✅ Parse Trainer input: name:specialization:contact:email
    public static Trainer parseTrainerFromString(String input) {
        try {
            String[] data = input.split(":");
            if (data.length < 4) {
                System.out.println("Invalid trainer input format!");
                return null;
            }

            Trainer trainer = new Trainer();
            trainer.setName(data[0]);
            trainer.setSpecialization(data[1]);
            trainer.setContact(data[2]);
            trainer.setEmail(data[3]);
            trainer.setActive(true);

            return trainer;

        } catch (Exception e) {
            System.out.println("Error parsing trainer: " + e.getMessage());
            return null;
        }
    }

    //  Parse Plan input: name:description:durationWeeks
    public static Plan parsePlanFromString(String input) {
        try {
            String[] data = input.split(":");
            if (data.length < 3) {
                System.out.println(" Invalid plan input format!");
                return null;
            }

            Plan plan = new Plan();
            plan.setName(data[0]);
            plan.setDescription(data[1]);
            plan.setDurationWeeks(Integer.parseInt(data[2]));

            return plan;

        } catch (Exception e) {
            System.out.println("Error parsing plan: " + e.getMessage());
            return null;
        }
    }

    //  Parse Package input: name:price:planId
    public static model.Package parsePackageFromString(String input) {
        try {
            String[] data = input.split(":");
            if (data.length < 3) {
                System.out.println("Invalid package input format!");
                return null;
            }

            model.Package pkg = new model.Package();
            pkg.setName(data[0]);
            pkg.setPrice(Double.parseDouble(data[1]));
            pkg.setPlanId(Integer.parseInt(data[2]));

            return pkg;

        } catch (Exception e) {
            System.out.println("Error parsing package: " + e.getMessage());
            return null;
        }
    }

    //  Parse Schedule input: memberId:trainerId:sessionDate(YYYY-MM-DD):sessionTime(HH:MM:SS):durationMinutes
    public static Schedule parseScheduleFromString(String input) {
        try {
            String[] data = input.split(":");
            

            Schedule schedule = new Schedule();
            String timePart = data[3] + ":" + data[4] + ":" + data[5];
            schedule.setMemberId(Integer.parseInt(data[0]));
            schedule.setTrainerId(Integer.parseInt(data[1]));
            schedule.setSessionDate(Date.valueOf(data[2]));
            schedule.setSessionTime(Time.valueOf(timePart));
            schedule.setDurationMinutes(Integer.parseInt(data[6]));

            return schedule;

        } catch (Exception e) {
            System.out.println("❌ Error parsing schedule: " + e.getMessage());
            return null;
        }
    }

    //  Parse Billing input: memberId:packageId:billingDate(YYYY-MM-DD):amount:status
    public static Billing parseBillingFromString(String input) {
        try {
            String[] data = input.split(":");
            if (data.length < 5) {
                System.out.println("Invalid billing input format!");
                return null;
            }

            Billing bill = new Billing();
            bill.setMemberId(Integer.parseInt(data[0]));
            bill.setPackageId(Integer.parseInt(data[1]));
            bill.setBillingDate(Date.valueOf(data[2]));
            bill.setAmount(Double.parseDouble(data[3]));
            bill.setBillingStatus(data[4]);

            return bill;

        } catch (Exception e) {
            System.out.println("Error parsing billing: " + e.getMessage());
            return null;
        }
    }
}
