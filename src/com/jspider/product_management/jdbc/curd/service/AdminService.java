package com.jspider.product_management.jdbc.curd.service;

import com.jspider.product_management.jdbc.curd.dao.AdminDao;
import com.jspider.product_management.jdbc.curd.entity.Admin;

import java.util.List;

public class AdminService {

    AdminDao dao = new AdminDao();

    // 1. Insert Admin Data.
    public Admin saveAdminService(Admin admin) {
        // Check if the Admin object is null and log an error if so
        if (admin == null) {
            System.err.println("Object cannot be null.");
            return null;
        }

        // Extract Admin properties for validation.
        int admin_id =admin.getAdmin_id();
        String admin_name = admin.getAdmin_name();
        String admin_password = admin.getAdmin_password();
        // Validate the data before saving to the database.
        if (admin_id >= 100 && admin_name.length() <= 45 && admin_password.length() >= 5){
            return dao.saveAdminDao(admin);
        }
        else {
            // If validation fails, log an error and return null.
            System.err.println("Invalid Admin details. Please check the inputs.");
            return null;
        }
    }

    //  2. Delete Admin data.
    public int deleteAdminByIdService(int adminId) {
        return dao.deleteAdminByIdDao(adminId); // return Integer Value
    }

    // 3. Update Admin data By Id.
    public Admin updateAdminByIdService(int adminId, Admin admin){
        // Check if the Admin object is null and log an error if so
        if (admin == null) {
            System.err.println("Object cannot be null.");
            return null;
        }
        // Extract Admin properties for validation.
        int admin_id = admin.getAdmin_id();
        String admin_name = admin.getAdmin_name();
        String admin_password = admin.getAdmin_password();

        return dao.updateAdminByIdDao(adminId,admin);
    }

    // 4. Display all the data
    public List<Admin> getAllAdminService(){
        return dao.getAllAdminDao();
    }

    // 5. Display Single Admin data.
    public Admin getAdminByIdService(int adminId){
        return dao.getAdminByIdDao(adminId);
    }


    //.============================== 🏳️🏳️ Authentication 🏳️🏳️============================================.
    // 1. Admin Login
    public boolean loginAdmin( int adminId, String adminPassword){

        try {
            // Validate inputs
            if (adminId <= 0) {
                throw new IllegalArgumentException("Admin ID must be a positive integer.");
            }
            if (adminPassword == null || adminPassword.trim().isEmpty()) {
                throw new IllegalArgumentException("Admin password cannot be null or empty.");
            }

            // Retrieve admin details from the service
            Admin admin = getAdminByIdService(adminId);

            // Handle case where no admin is found
            if (admin == null) {
                System.out.println("Admin not found.");
                return false;
            }

            // Extract properties for validation
            Integer a_id = admin.getAdmin_id();
            String a_password = admin.getAdmin_password();

            // Validate admin credentials
            return a_id.equals(adminId) && a_password.equals(adminPassword);
        } catch (IllegalArgumentException e) {
            System.err.println("Input validation error: " + e.getMessage());
            return false;
        } catch (NullPointerException e) {
            System.err.println("Null value encountered: " + e.getMessage());
            return false;
        } catch (Exception e) {
            // Generic exception handling
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    // 2. Register Admin
    public Admin registerAdmin(Admin admin){
        // Validate admin object
        if (admin == null) {
            System.err.println("Admin details cannot be null.");
            return null;
        }

        // Validate mandatory fields (example: name, email, password)
        if (admin.getAdmin_name() == null || admin.getAdmin_name().trim().isEmpty()) {
            System.err.println("Admin name cannot be null or empty.");
            return null;
        }

        if (admin.getAdmin_password() == null || admin.getAdmin_password().length() < 6) {
            System.err.println("Password must be at least 6 characters long.");
            return null;
        }
        return saveAdminService(admin);
    }
}
