package main;

import java.util.Scanner;
import java.util.*;
import service.*;
import model.*;
import model.Package;
import model.Billing;
import java.time.LocalDate;
import java.sql.Date;
public class MainUI {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MemberService memberService = new MemberService();
        TrainerService trainerService = new TrainerService();
        PlanService planService = new PlanService();
        PackageService packageService = new PackageService();
        ScheduleService scheduleService = new ScheduleService();
        BillingService billingService = new BillingService();

        int mainChoice = 0;

        System.out.println("=========================================");
        System.out.println("    FITNESS HEALTH CLUB MANAGEMENT  ");
        System.out.println("=========================================");

        do {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Member Management");
            System.out.println("2. Trainer Management");
            System.out.println("3. Plan Management");
            System.out.println("4. Package Management");
            System.out.println("5. Schedule Management");
            System.out.println("6. Billing Management");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            mainChoice = sc.nextInt();

            switch (mainChoice) {

                // ---------------- MEMBER MENU ----------------
                case 1:
                    int memberChoice;
                    do {
                        System.out.println("\n=== Member Management ===");
                        System.out.println("1. Add Member");
                        System.out.println("2. Update Member");
                        System.out.println("3. Deactivate Member");
                        System.out.println("4. View Member by ID");
                        System.out.println("5. View All Members");
                        System.out.println("6. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        memberChoice = sc.nextInt();
                        sc.nextLine();

                        switch (memberChoice) {
                            case 1: 
                            	System.out.println("Enter member details (name:contact:email:address):");
                                String input = sc.nextLine();
                            	memberService.addMember(input); break;
                            case 2:
                            	int choice;
                            	String field = "";
                                String newValue = "";
                                int id;
                                loop:
                            	do {
                            	System.out.print("Enter Member ID to update: ");
                                id = sc.nextInt();
                                sc.nextLine(); // consume newline

                                System.out.println("\nSelect field to update:");
                                System.out.println("1. Update Name");
                                System.out.println("2. Update Contact");
                                System.out.println("3. Update Address");
                                System.out.println("4. Back");
                                System.out.print("Enter your choice: ");
                                choice = sc.nextInt();
                                sc.nextLine();

                                

                                switch (choice) {
                                    case 1:
                                        field = "name";
                                        System.out.print("Enter new name: ");
                                        newValue = sc.nextLine();
                                        break loop;
                                    case 2:
                                        field = "contact";
                                        System.out.print("Enter new contact: ");
                                        newValue = sc.nextLine();
                                        break loop;
                                    case 3:
                                        field = "address";
                                        System.out.print("Enter new address: ");
                                        newValue = sc.nextLine();
                                        break loop;
                                    case 4:
                                    	break;
                                    default:
                                        System.out.println("Invalid choice!");
                                        return;
                                }
                 
                            	}while(choice!=4);
                            	if(choice!=4) {
                            	memberService.updateMember(id , field, newValue);
                            	}break;
                            case 3:
                            	System.out.print("Enter Member ID to deactivate: ");
                                int id2 = sc.nextInt();
                            	memberService.deactivateMember(id2); break;
                            case 4:
                            	System.out.print("Enter Member ID: ");
                            	
                                int id3 = sc.nextInt();
                                sc.nextLine();
                            	Member member=memberService.viewMemberById(id3);
                            	if (member != null) {
                                    System.out.println("\n--- Member Details ---");
                                    System.out.println("ID: " + member.getMemberId());
                                    System.out.println("Name: " + member.getName());
                                    System.out.println("Contact: " + member.getContact());
                                    System.out.println("Email: " + member.getEmail());
                                    System.out.println("Address: " + member.getAddress());
                                    System.out.println("Joined: " + member.getDateJoined());
                                    System.out.println("Active: " + member.isActive());
                                } else {
                                    System.out.println("Member not found!");
                                }break;
                            case 5: 
                            	
                            	List<Member> members=memberService.viewAllMembers(); 
                            	if (members.isEmpty()) {
                                    System.out.println("No members found!");
                                    return;
                                }
                                System.out.println("\n=== All Members ===");
                                System.out.println("ID   Name    Contact      Gmail    Address    Date_Joined    IsActive");
                                for (Member m : members) {
                                    System.out.println(m.getMemberId() + " | " + m.getName() + " | " + m.getContact() + " | " + m.getEmail() + " | " + m.getAddress() + " | " + m.getDateJoined()+" | Active: " + m.isActive());
                                }
                            	break;
                            case 6: break;
                            default: System.out.println("Invalid choice.");
                        }
                    } while (memberChoice != 6);
                    break;

                // ---------------- TRAINER MENU ----------------
                    
                case 2:
                	int trainerChoice;
                	do {
                	    System.out.println("\n=== Trainer Management ===");
                	    System.out.println("1. Add Trainer");
                	    System.out.println("2. Update Trainer");
                	    System.out.println("3. Deactivate Trainer");
                	    System.out.println("4. View Trainer by ID");
                	    System.out.println("5. View All Trainers");
                	    System.out.println("6. Back to Main Menu");
                	    System.out.print("Enter your choice: ");
                	    trainerChoice = sc.nextInt();
                	    sc.nextLine();

                	    switch (trainerChoice) {

                	        case 1:
                	            System.out.println("Enter trainer details (name:specialization:contact:email):");
                	            String input = sc.nextLine();
                	            trainerService.addTrainer(input);
                	            break;

                	        case 2:
                	            int id;
                	            int choice;
                	            String field = "";
                	            String newValue = "";

                	            updateLoop:
                	            do {
                	                System.out.print("Enter Trainer ID to update: ");
                	                id = sc.nextInt();
                	                sc.nextLine();

                	                System.out.println("\nSelect field to update:");
                	                System.out.println("1. Update Name");
                	                System.out.println("2. Update Specialization");
                	                System.out.println("3. Update Contact");
                	                System.out.println("4. Update Email");
                	                System.out.println("5. Back");
                	                System.out.print("Enter your choice: ");
                	                choice = sc.nextInt();
                	                sc.nextLine();

                	                switch (choice) {
                	                    case 1:
                	                        field = "name";
                	                        System.out.print("Enter new name: ");
                	                        newValue = sc.nextLine();
                	                        break updateLoop;

                	                    case 2:
                	                        field = "specialization";
                	                        System.out.print("Enter new specialization: ");
                	                        newValue = sc.nextLine();
                	                        break updateLoop;

                	                    case 3:
                	                        field = "contact";
                	                        System.out.print("Enter new contact: ");
                	                        newValue = sc.nextLine();
                	                        break updateLoop;

                	                    case 4:
                	                        field = "email";
                	                        System.out.print("Enter new email: ");
                	                        newValue = sc.nextLine();
                	                        break updateLoop;

                	                    case 5:
                	                        break;

                	                    default:
                	                        System.out.println("Invalid choice!");
                	                        return;
                	                }
                	            } while (choice != 5);

                	            if (choice != 5) {
                	                trainerService.updateTrainer(id, field, newValue);
                	            }
                	            break;

                	        case 3:
                	            System.out.print("Enter Trainer ID to deactivate: ");
                	            int deactivateId = sc.nextInt();
                	            trainerService.deactivateTrainer(deactivateId);
                	            break;

                	        case 4:
                	            System.out.print("Enter Trainer ID: ");
                	            int viewId = sc.nextInt();
                	            sc.nextLine();
                	            Trainer trainer = trainerService.viewTrainerById(viewId);

                	            if (trainer != null) {
                	                System.out.println("\n--- Trainer Details ---");
                	                System.out.println("ID: " + trainer.getTrainerId());
                	                System.out.println("Name: " + trainer.getName());
                	                System.out.println("Specialization: " + trainer.getSpecialization());
                	                System.out.println("Contact: " + trainer.getContact());
                	                System.out.println("Email: " + trainer.getEmail());
                	                System.out.println("Active: " + trainer.isActive());
                	            } else {
                	                System.out.println("Trainer not found!");
                	            }
                	            break;

                	        case 5:
                	            List<Trainer> trainers = trainerService.viewAllTrainers();
                	            if (trainers.isEmpty()) {
                	                System.out.println("No trainers found!");
                	            } else {
                	                System.out.println("\n=== All Trainers ===");
                	                System.out.println("ID | Name | Specialization | Contact | Email | Active");
                	                for (Trainer t : trainers) {
                	                    System.out.println(t.getTrainerId() + " | " + t.getName() + " | " + 
                	                        t.getSpecialization() + " | " + t.getContact() + " | " + t.getEmail() + 
                	                        " | Active: " + t.isActive());
                	                }
                	            }
                	            break;

                	        case 6:
                	            break;

                	        default:
                	            System.out.println("Invalid choice.");
                	    }

                	} while (trainerChoice != 6);
                	break;


                // ---------------- PLAN MENU ----------------
                case 3:
                	int planChoice;
                	do {
                	    System.out.println("\n=== Plan Management ===");
                	    System.out.println("1. Add Plan");
                	    System.out.println("2. Update Plan");
                	    System.out.println("3. View Plan by ID");
                	    System.out.println("4. View All Plans");
                	    System.out.println("5. Back to Main Menu");
                	    System.out.print("Enter your choice: ");
                	    planChoice = sc.nextInt();
                	    sc.nextLine();

                	    switch (planChoice) {

                	        case 1:
                	            System.out.println("Enter plan details (name:description:duration_weeks):");
                	            String input = sc.nextLine();
                	            planService.addPlan(input);
                	            break;

                	        case 2:
                	            int id;
                	            int choice;
                	            String field = "";
                	            String newValue = "";

                	            updateLoop:
                	            do {
                	                System.out.print("Enter Plan ID to update: ");
                	                id = sc.nextInt();
                	                sc.nextLine();

                	                System.out.println("\nSelect field to update:");
                	                System.out.println("1. Update Name");
                	                System.out.println("2. Update Description");
                	                System.out.println("3. Update Duration (weeks)");
                	                System.out.println("4. Back");
                	                System.out.print("Enter your choice: ");
                	                choice = sc.nextInt();
                	                sc.nextLine();

                	                switch (choice) {
                	                    case 1:
                	                        field = "name";
                	                        System.out.print("Enter new name: ");
                	                        newValue = sc.nextLine();
                	                        break updateLoop;

                	                    case 2:
                	                        field = "description";
                	                        System.out.print("Enter new description: ");
                	                        newValue = sc.nextLine();
                	                        break updateLoop;

                	                    case 3:
                	                        field = "duration_weeks";
                	                        System.out.print("Enter new duration in weeks: ");
                	                        newValue = sc.nextLine();
                	                        break updateLoop;

                	                    case 4:
                	                        break;

                	                    default:
                	                        System.out.println("Invalid choice!");
                	                        return;
                	                }

                	            } while (choice != 4);

                	            if (choice != 4) {
                	                planService.updatePlan(id, field, newValue);
                	            }
                	            break;

                	        case 3:
                	            System.out.print("Enter Plan ID: ");
                	            int viewId = sc.nextInt();
                	            sc.nextLine();
                	            Plan p = planService.viewPlanById(viewId);

                	            if (p != null) {
                	                System.out.println("\n--- Plan Details ---");
                	                System.out.println("ID: " + p.getPlanId());
                	                System.out.println("Name: " + p.getName());
                	                System.out.println("Description: " + p.getDescription());
                	                System.out.println("Duration Weeks: " + p.getDurationWeeks());
                	            } else {
                	                System.out.println("Plan not found!");
                	            }
                	            break;

                	        case 4:
                	            List<Plan> plans = planService.viewAllPlans();
                	            if (plans.isEmpty()) {
                	                System.out.println("No Plans Available!");
                	            } else {
                	                System.out.println("\n=== Available Plans ===");
                	                System.out.println("ID | Name | Description | Duration (Weeks)");
                	                for (Plan pl : plans) {
                	                    System.out.println(pl.getPlanId() + " | " + pl.getName() + " | " 
                	                                       + pl.getDescription() + " | " + pl.getDurationWeeks());
                	                }
                	            }
                	            break;

                	        case 5:
                	            break;

                	        default:
                	            System.out.println("Invalid choice.");
                	    }

                	} while (planChoice != 5);
                	break;

                // ---------------- PACKAGE MENU ----------------
                case 4:
                	int packageChoice;
                	do {
                	    System.out.println("\n=== Package Management ===");
                	    System.out.println("1. Add Package");
                	    System.out.println("2. Update Package");
                	    System.out.println("3. View Package by ID");
                	    System.out.println("4. View All Packages");
                	    System.out.println("5. Back to Main Menu");
                	    System.out.print("Enter your choice: ");
                	    packageChoice = sc.nextInt();
                	    sc.nextLine();

                	    switch (packageChoice) {

                	        case 1:
                	            System.out.println("Enter package details (name:price:planId):");
                	            String input = sc.nextLine();
                	            packageService.addPackage(input);
                	            break;

                	        case 2:
                	            int id, choice;
                	            String field = "";
                	            String newValue = "";

                	            updateLoop:
                	            do {
                	                System.out.print("Enter Package ID to update: ");
                	                id = sc.nextInt();
                	                sc.nextLine();

                	                System.out.println("\nSelect field to update:");
                	                System.out.println("1. Update Name");
                	                System.out.println("2. Update Price");
                	                System.out.println("3. Update Plan ID");
                	                System.out.println("4. Back");
                	                System.out.print("Enter your choice: ");
                	                choice = sc.nextInt();
                	                sc.nextLine();

                	                switch (choice) {
                	                    case 1:
                	                        field = "name";
                	                        System.out.print("Enter new name: ");
                	                        newValue = sc.nextLine();
                	                        break updateLoop;

                	                    case 2:
                	                        field = "price";
                	                        System.out.print("Enter new price: ");
                	                        newValue = sc.nextLine();
                	                        break updateLoop;

                	                    case 3:
                	                        field = "planId";
                	                        System.out.print("Enter new plan ID: ");
                	                        newValue = sc.nextLine();
                	                        break updateLoop;

                	                    case 4:
                	                        break;

                	                    default:
                	                        System.out.println("Invalid choice!");
                	                        return;
                	                }
                	            } while (choice != 4);

                	            if (choice != 4) {
                	                packageService.updatePackage(id, field, newValue);
                	            }
                	            break;

                	        case 3:
                	            System.out.print("Enter Package ID: ");
                	            int viewId = sc.nextInt();
                	            sc.nextLine();
                	            Package pack = packageService.viewPackageById(viewId);

                	            if (pack != null) {
                	                System.out.println("\n--- Package Details ---");
                	                System.out.println("ID: " + pack.getPackageId());
                	                System.out.println("Name: " + pack.getName());
                	                System.out.println("Price: ₹" + pack.getPrice());
                	                System.out.println("Plan ID: " + pack.getPlanId());
                	            } else {
                	                System.out.println("Package not found!");
                	            }
                	            break;

                	        case 4:
                	            List<Package> packs = packageService.viewAllPackages();
                	            if (packs.isEmpty()) {
                	                System.out.println("No Packages Available!");
                	            } else {
                	                System.out.println("\n=== All Packages ===");
                	                System.out.println("ID | Name | Price | Plan ID");
                	                for (Package p : packs) {
                	                    System.out.println(p.getPackageId() + " | " + p.getName() + " | " + p.getPrice() + " | " + p.getPlanId());
                	                }
                	            }
                	            break;

                	        case 5:
                	            break;

                	        default:
                	            System.out.println("Invalid choice.");
                	    }

                	} while (packageChoice != 5);
                	break;


                // ---------------- SCHEDULE MENU ----------------
                case 5:
                    int scheduleChoice;
                    do {
                        System.out.println("\n=== Schedule Management ===");
                        System.out.println("1. Add Schedule");
                        System.out.println("2. Update Schedule");
                        System.out.println("3. View Schedule by ID");
                        System.out.println("4. View Schedules by Member ID");
                        System.out.println("5. View Schedules by Trainer ID");
                        System.out.println("6. View All Schedules");
                        System.out.println("7. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        scheduleChoice = sc.nextInt();
                        sc.nextLine();

                        switch (scheduleChoice) {
                            case 1: 
                            	System.out.println("\n=== Add New Schedule ===");
                                System.out.println("MemberID:TrainerId:SessionDate(YYYY-MM-DD):SessionTime(HH:MM:SS):Duration(in minutes):");
                                String s=sc.nextLine();
            
                            	scheduleService.addSchedule(s); break;
                            case 2: 
                            	int choice;
                            	String field = "";
                                String newValue = "";
                                int id;
                                loop:
                            	do {
                            	System.out.print("Enter Session ID to update: ");
                                id = sc.nextInt();
                                sc.nextLine(); // consume newline

                                System.out.println("\nSelect field to update:");
                                System.out.println("1. Update Date");
                                System.out.println("2. Update Time");
                                System.out.println("3. Update Duration");
                                System.out.println("4. Back");
                                System.out.print("Enter your choice: ");
                                choice = sc.nextInt();
                                sc.nextLine();

                                

                                switch (choice) {
                                    case 1:
                                        field = "session_date";
                                        System.out.println("Enter the new Date(YYYY-MM-DD): ");
                                        newValue = sc.nextLine();
                                        break loop;
                                    case 2:
                                        field = "session_time";
                                        System.out.print("Enter new Time(HH:MM:SS): ");
                                        newValue = sc.nextLine();
                                        break loop;
                                    case 3:
                                        field = "duration_minutes";
                                        System.out.print("Enter new duration: ");
                                        newValue = sc.nextLine();
                                        break loop;
                                    case 4:
                                    	break;
                                    default:
                                        System.out.println("Invalid choice!");
                                        return;
                                }
                 
                            	}while(choice!=4);
                            	if(choice!=4) {
                            	scheduleService.updateSchedule(id , field, newValue);
                            	}break;
                            case 3: 
                            	System.out.print("Enter Schedule ID: ");
                            	int scheduleId=sc.nextInt();
                            	Schedule schedule=scheduleService.viewScheduleById(scheduleId); 
                            	if (schedule != null) {
                                	System.out.println("\n--- Session Details ---");
                                    System.out.println("ID: " + schedule.getScheduleId());
                                    System.out.println("Member Id: " + schedule.getMemberId());
                                    System.out.println("Trainer Id: " + schedule.getTrainerId());
                                    System.out.println("Date: " + schedule.getSessionDate());
                                    System.out.println("Time: " + schedule.getSessionTime());
                                    System.out.println("Duration: " + schedule.getDurationMinutes());
                                    
                                } else {
                                    System.out.println("No schedule found with ID: " + scheduleId);
                                }break;
                                
                            case 4: 
                            	System.out.print("Enter Member ID: ");
                            	int memberId=sc.nextInt();
                            	Schedule schedule1=scheduleService.viewSchedulesByMemberId(memberId);
                            	if (schedule1 != null) {
                                	System.out.println("\n--- Session Details ---");
                                    System.out.println("ID: " + schedule1.getScheduleId());
                                    System.out.println("Member Id: " + schedule1.getMemberId());
                                    System.out.println("Trainer Id: " + schedule1.getTrainerId());
                                    System.out.println("Date: " + schedule1.getSessionDate());
                                    System.out.println("Time: " + schedule1.getSessionTime());
                                    System.out.println("Duration: " + schedule1.getDurationMinutes());
                                    
                                } else {
                                    System.out.println("No schedule found with ID: " + memberId);
                                }
                            	break;
                            case 5: 
                            	System.out.print("Enter Trainer ID: ");
                            	int trainerId=sc.nextInt();
                            	Schedule schedule2=scheduleService.viewSchedulesByTrainerId(trainerId); 
                            	if (schedule2 != null) {
                                	System.out.println("\n--- Session Details ---");
                                    System.out.println("ID: " + schedule2.getScheduleId());
                                    System.out.println("Member Id: " + schedule2.getMemberId());
                                    System.out.println("Trainer Id: " + schedule2.getTrainerId());
                                    System.out.println("Date: " + schedule2.getSessionDate());
                                    System.out.println("Time: " + schedule2.getSessionTime());
                                    System.out.println("Duration: " + schedule2.getDurationMinutes());
                                    
                                } else {
                                    System.out.println("No schedule found with ID: " + trainerId);
                                }
                            	break;
                            case 6: 
                            	List<Schedule> schedules=scheduleService.viewAllSchedules(); 
                            	if (schedules.isEmpty()) {
                                    System.out.println("No schedules available in the system.");
                                    return;
                                } 
                                
                                System.out.println("\n=== All Schedules ===");
                                System.out.println("SessionID   MemId    TrainerID      Date    Time    Duration");
                                for (Schedule m : schedules) {
                                    System.out.println(m.getScheduleId() + " | " + m.getMemberId() + " | " + m.getTrainerId() + " | " + m.getSessionDate() + " | " + m.getSessionTime() + " | " + m.getDurationMinutes());
                                }
                            	break;
                            case 7: break;
                            default: System.out.println("Invalid choice.");
                        }
                    } while (scheduleChoice != 7);
                    break;

                // ---------------- BILLING MENU ----------------
                case 6:
                    int billingChoice;
                    do {
                        System.out.println("\n=== Billing Management ===");
                        System.out.println("1. Generate Bill");
                        System.out.println("2. Update Bill");
                        System.out.println("3. View Bill by ID");
                        System.out.println("4. View Bills by Member ID");
                        System.out.println("5. View All Bills");
                        System.out.println("6. Back to Main Menu");
                        System.out.print("Enter your choice: ");
                        billingChoice = sc.nextInt();
                        sc.nextLine();

                        switch (billingChoice) {
                            case 1: 
                            	System.out.print("Enter Member ID: ");
                            	int memberId = sc.nextInt();

                            	System.out.print("Enter Package ID: ");
                            	int packageId = sc.nextInt();
                            	sc.nextLine();
                            	double amount = billingService.getPackagePrice(packageId);
                            	if (amount == -1) {
                            	    System.out.println("Invalid Package ID!");
                            	    return;
                            	}
                                //  Automatically generate date
                            	LocalDate date = LocalDate.now();
                            	System.out.println("Your package amount is: " + amount);

                                System.out.print("Enter Billing Status (Paid/Unpaid): ");
                            	String status = sc.nextLine();
                            	// Now save billing
                            	Billing billing = new Billing(memberId, packageId, Date.valueOf(date), amount, status);
                            	billingService.generateBill(billing);  break;
                            	
                            case 2: 
                            	System.out.print("Enter Bill ID to update: ");
                                int id = sc.nextInt();
                                sc.nextLine(); // consume newline
                                System.out.println("\nSelect field to update:");
                                System.out.println("1. Update Member Id");
                                System.out.println("2. Update Package Id");
                                System.out.println("3. Update Amount");
                                System.out.println("4. Update Billing Status");
                            
                            
                                System.out.print("Enter your choice: ");
                                int choice = sc.nextInt();
                                sc.nextLine();

                                String field = "";
                                String newValue = "";

                            switch (choice) {
                                case 1:
                                    field = "memberid";
                                    System.out.print("Enter the member id: ");
                                    newValue = sc.nextLine();
                                    break;
                                case 2:
                                	field= "packageid";
                                	System.out.print("Enter the package id: ");
                                	newValue= sc.nextLine();
                                	break;
                                case 3:
                                	field="amount";
                                	System.out.print("Enter new amount: ");
                                	newValue=sc.nextLine();
                                	break;
                                case 4:
                                    field = "billingStatus";
                                    System.out.print("Enter new billing status: ");
                                    newValue = sc.nextLine();
                                    break;
                                default:
                                    System.out.println("Invalid choice!");
                                    return;
                            }
                            billingService.updateBill(id, field, newValue);  break;
                            
                            case 3:
                            	System.out.print("Enter Bill ID: ");
                                int billId = sc.nextInt();
                                sc.nextLine();
                                Billing bill=billingService.viewBillById(billId);
                            	if (bill != null) {
                                    System.out.println("\n--- Billing Details ---");
                                    System.out.println("Bill ID: " + bill.getBillId());
                                    System.out.println("Member ID: " + bill.getMemberId());
                                    System.out.println("Package ID: " + bill.getPackageId());
                                    System.out.println("Billing Date: " + bill.getBillingDate());
                                    System.out.println("Amount: " + bill.getAmount());
                                    System.out.println("Status: " + bill.getBillingStatus());
                                } else {
                                    System.out.println("Bill not found!");
                                }
                                break;
                                
                            case 4: 
                            	 System.out.print("Enter Member ID: ");
                                 int memId = sc.nextInt();
                                 sc.nextLine();

                                 List<Billing> bills = billingService.viewBillsByMemberId(memId);
                            	 if(bills!=null) {
                            		 System.out.println("\n=== Bills for Member ID: " + memId + " ===");
                            	        for (Billing b : bills) {
                            	            System.out.println(b.getBillId() + " | " + b.getBillingDate() + " | " + b.getAmount() + " | " + b.getBillingStatus());
                            	        }
                            	 }
                            	 else {
                            		 System.out.println("No bills found for this member!");
                            	 }
                            		 break;
                            case 5: 
                            	 List<Billing> billList = billingService.viewAllBills();
                                 if (billList.isEmpty()) {
                                     System.out.println("No bills found!");
                                     return;
                                 }

                                 System.out.println("\n=== All Bills ===");
                                 for (Billing b : billList) {
                                     System.out.println(b.getBillId() + " | " + b.getMemberId() + " | " + b.getPackageId() + " | " +
                                             b.getBillingDate() + " | " + b.getAmount() + " | " + b.getBillingStatus());
                                 }
                                 break;
                            case 6: break;
                            default: System.out.println("Invalid choice.");
                        }
                    } while (billingChoice != 6);
                    break;

                case 7:
                    System.out.println("\n Exiting... Thank you for using Fitness Health Club Management System!");
                    break;

                default:
                    System.out.println("Invalid main menu choice.");
            }

     

            } while (mainChoice != 7);

        sc.close();
    }
}
