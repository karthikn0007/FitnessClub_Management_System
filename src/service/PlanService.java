package service;

import java.util.List;
import dao.PlanDAO;
import model.Plan;
import util.ApplicationUtil;

public class PlanService {

    private PlanDAO planDAO = new PlanDAO();

    // Add Plan
    public void addPlan(String input) {
        Plan plan = ApplicationUtil.parsePlanFromString(input);
        if (plan != null) {
            planDAO.addPlan(plan);
        }
    }

    // Update Plan
    public void updatePlan(int id, String field, String newValue) {
        planDAO.updatePlan(id, field, newValue);
    }

    // View Plan By ID (returns Plan object)
    public Plan viewPlanById(int id) {
        return planDAO.getPlanById(id);
    }

    // View All Plans (returns list of plans)
    public List<Plan> viewAllPlans() {
        return planDAO.getAllPlans();
    }
}
