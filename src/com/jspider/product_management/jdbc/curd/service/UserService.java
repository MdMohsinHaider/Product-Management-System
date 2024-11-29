package com.jspider.product_management.jdbc.curd.service;

import com.jspider.product_management.jdbc.curd.dao.UserDao;
import com.jspider.product_management.jdbc.curd.entity.User;

import java.util.List;

public class UserService {

    UserDao userDao = new UserDao();

    // User Insert in Db
    public User saveUserService(User user){

        if (user == null){
            return null;
        }

        int u_id = user.getUser_id();
        String u_name = user.getUser_name();
        String u_password = user.getUser_password();
        String u_email = user.getUser_email();

        return userDao.saveUserDao(user);
    }

    // ............................ 2. Delete method ......................................
    public int deleteUserByIdService(int userId){
        return userDao.deleteUserByIdDao(userId);
    }

    // --------------------3. Update -----------------------------------------------------------
    public User updateUserByIdService(int userId, User user){
        return userDao.updateUserByIdDao(userId,user);
    }

    // ..................................4. Display all the data ...........................................
    public List<User> getAllUserDisplayService(){
        return userDao.getAllUserDisplayDao();
    }

    //............. 5. Display Single User data..........................................
    public User getUserByIdService(int userId){
        return userDao.getUserByIdDao(userId);
    }

    //.============================== 🏳️🏳️ Authentication 🏳️🏳️============================================.

    // 1. login User ------------------------------------------------------------

    public boolean loginUser(int user_id, String user_password){
        try {
            // Fetch the user details from the database
            User user = getUserByIdService(user_id);

            // Handle case where no user is found
            if (user == null) {
                System.out.println("User not found.");
                return false;
            }

            // Extract properties for validation
            Integer userId = user.getUser_id();
            String userPassword = user.getUser_password();

            // Perform login validation
            return userId.equals(user_id) && userPassword.equals(user_password);
        } catch (NullPointerException e) {
            System.err.println("Null value encountered during login process: " + e.getMessage());
            return false;
        } catch (Exception e) {
            // Generic exception handling for unforeseen issues
            System.err.println("An error occurred during login: " + e.getMessage());
            return false;
        }
    }


    // 2. Register Admin -------------------------------------------------------

    public User registerUser(User user){
        // Validate admin object
        if (user == null) {
            System.err.println("Admin details cannot be null.");
            return null;
        }

        // Validate mandatory fields (example: name, email, password)
        if (user.getUser_name() == null || user.getUser_name().trim().isEmpty()) {
            System.err.println("Admin name cannot be null or empty.");
            return null;
        }
        if (user.getUser_password() == null || user.getUser_password().length() < 6) {
            System.err.println("Password must be at least 6 characters long.");
            return null;
        }
        return saveUserService(user) ;
    }
}
