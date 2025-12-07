package service;

import java.util.List;

import dao.TrainerDAO;
import model.Trainer;
import util.ApplicationUtil;

public class TrainerService {

    private TrainerDAO trainerDAO = new TrainerDAO();

    // Add Trainer
    public void addTrainer(String input) {
        Trainer trainer = ApplicationUtil.parseTrainerFromString(input);
        if (trainer != null) {
            trainerDAO.addTrainer(trainer);
        }
    }

    // Update Trainer
    public void updateTrainer(int id, String field, String newValue) {
        trainerDAO.updateTrainer(id, field, newValue);
    }

    // Deactivate Trainer
    public void deactivateTrainer(int id) {
        trainerDAO.deactivateTrainer(id);
    }

    // View Trainer By ID (returns object)
    public Trainer viewTrainerById(int id) {
        return trainerDAO.getTrainerById(id);
    }

    // View All Trainers (returns list)
    public List<Trainer> viewAllTrainers() {
        return trainerDAO.getAllTrainers();
    }
}
