package com.jspider.product_management.jdbc.curd.dao;

import com.jspider.product_management.jdbc.curd.conection.ConnectionMyDb;
import com.jspider.product_management.jdbc.curd.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection = ConnectionMyDb.getConnectionMyDb();

    public User saveUserDao(User user){
        try {
            String INSERT_USER_QUERY = "insert into user(UID,UNAME,PASSWORD,UEMAIL) value(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY);
            preparedStatement.setInt(1,user.getUser_id());
            preparedStatement.setString(2,user.getUser_name());
            preparedStatement.setString(3,user.getUser_password());
            preparedStatement.setString(4,user.getUser_email());

            int a = preparedStatement.executeUpdate();
            return a!=0?user:null;
        }
        catch (Exception e) {
            System.err.println("Save Admin Dao Exception "+e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Product connection Closed : "+e.getMessage());
            }
        }
    }

    // ............................ 2. Delete method ......................................
    public int deleteUserByIdDao(int userId){
        try {
            String DELETE_USER_QUERY = "delete from user where UID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setInt(1,userId);
            return preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            System.err.println("Exception User Delete By Id : "+e.getMessage());
            return 0;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Product connection Closed : "+e.getMessage());
            }
        }
    }

    // --------------------3. Update -----------------------------------------------------------
    public User updateUserByIdDao(int userId, User user){
        try {
            String UPDATE_ADMIN_QUERY = "update user set UNAME=?,PASSWORD=?,UEMAIL=? where PID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_QUERY);
            preparedStatement.setInt(4,userId);
            preparedStatement.setString(1,user.getUser_name());
            preparedStatement.setString(2,user.getUser_password());
            preparedStatement.setString(3,user.getUser_email());

            int a = preparedStatement.executeUpdate();
            System.out.println("..Successfully update values..");
            return a != 0? user:null;
        }
        catch (Exception e) {
            System.err.println("Exception User Update By Id : "+e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Product connection Closed : "+e.getMessage());
            }
        }
    }

    // ..................................4. Display all the data ...........................................
    public List<User> getAllUserDisplayDao(){
        try {
            String DISPLAY_User_QUERY = "select * from user";
            PreparedStatement preparedStatement = connection.prepareStatement(DISPLAY_User_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> userList = new ArrayList<>();

            while (resultSet.next()){
                int u_id = resultSet.getInt("UID");
                String u_name = resultSet.getString("UNAME");
                String u_password = resultSet.getString("PASSWORD");
                String u_email = resultSet.getString("UEMAIL");

                User user = new User(u_id,u_name,u_password,u_email);
                userList.add(user);
            }
            return userList;
        }
        catch (Exception e) {
            System.err.println("Exception All User display : "+e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Product connection Closed : "+e.getMessage());
            }
        }
    }

    //............. 5. Display Single User data..........................................
    public User getUserByIdDao(int userId){
        try {
            String DISPLAY_SINGLE_USER_QUERY = "select * from user where UID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(DISPLAY_SINGLE_USER_QUERY);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int u_id = resultSet.getInt("UID");
                String u_name = resultSet.getString("UNAME");
                String u_password = resultSet.getString("PASSWORD");
                String u_email = resultSet.getString("UEMAIL");
                return new User(u_id,u_name,u_password,u_email);
            }
            System.out.println("No Admin with ID found: " + userId);
            return null;
        }
        catch (Exception e) {
            System.err.println("Exception User display By Id : "+e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Product connection Closed : "+e.getMessage());
            }
        }
    }
}
