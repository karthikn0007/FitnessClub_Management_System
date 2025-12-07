package service;

import java.util.List;
import dao.PackageDAO;
import model.Package;
import util.ApplicationUtil;

public class PackageService {

    private PackageDAO packageDAO = new PackageDAO();

    // Add Package from string input (name:price:planId)
    public void addPackage(String input) {
        Package pack = ApplicationUtil.parsePackageFromString(input);
        if (pack != null) {
            packageDAO.addPackage(pack);
        }
    }

    // Update Package field
    public void updatePackage(int id, String field, String newValue) {
        packageDAO.updatePackage(id, field, newValue);
    }

    // View package by ID (return object)
    public Package viewPackageById(int id) {
        return packageDAO.getPackageById(id);
    }

    // View all packages (return list)
    public List<Package> viewAllPackages() {
        return packageDAO.getAllPackages();
    }
}
