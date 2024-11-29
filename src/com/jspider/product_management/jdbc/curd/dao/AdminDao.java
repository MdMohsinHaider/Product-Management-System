package com.jspider.product_management.jdbc.curd.dao;

import com.jspider.product_management.jdbc.curd.conection.ConnectionMyDb;
import com.jspider.product_management.jdbc.curd.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    Connection connection = ConnectionMyDb.getConnectionMyDb();

    // .................................... 1. insert method ...................................
    public Admin saveAdminDao(Admin admin) {
        try {
            // Step: 3 Crete preparedStatement and passing SQL Query;
            String INSERT_ADMIN_QUERY = "insert into admin(AID,ANAME,APASSWORD ) value(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_QUERY);
            // fill the admin data in an insert query.
            preparedStatement.setInt(1,admin.getAdmin_id());
            preparedStatement.setString(2,admin.getAdmin_name());
            preparedStatement.setString(3,admin.getAdmin_password());

            // Step: 4 Execute Sql Query;
            int a = preparedStatement.executeUpdate();
            return a != 0 ? admin:null;
        }
        catch (Exception e){
            System.err.println("Save Admin Dao Exception "+e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Save Admin Dao connection close Exception : "+e.getMessage());
            }
        }
    }

    // ............................ 2. Delete method ......................................
    public int deleteAdminByIdDao(int adminId) {
        try {
            // Step: 3 Crete preparedStatement and passing SQL Query;
            String DELETE_ADMIN_QUERY = "delete from admin where AID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_QUERY);
            preparedStatement.setInt(1,adminId);

            // Step: 4 Execute Sql Query;
            return preparedStatement.executeUpdate(); // return integer value
        }
        catch (Exception e){
            System.err.println("Save Admin Dao Exception "+e.getMessage());
            return 0;
        }
        finally {
            try {
                // Step: 5 Closes Connection;
                connection.close();
            } catch (SQLException e) {
                System.err.println("Save Admin Dao connection close Exception : "+e.getMessage());
            }
        }
    }

    // --------------------3. Update -----------------------------------------------------------
    public Admin updateAdminByIdDao(int adminId, Admin admin){
        try {
            String UPDATE_ADMIN_QUERY = "update admin set ANAME=?,APASSWORD=? where AID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_QUERY);
            preparedStatement.setInt(3,adminId);
            preparedStatement.setString(1,admin.getAdmin_name());
            preparedStatement.setString(2,admin.getAdmin_password());

            int a = preparedStatement.executeUpdate();
            System.out.println("..Successfully update values..");
            return a != 0?admin:null;
        }
        catch (Exception e){
            System.err.println("Update Dao : "+e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    // ..................................4. Display all the data ...........................................
    public List<Admin> getAllAdminDao(){
        try {
            String DISPLAY_ADMIN_QUERY = "select * from admin";
            PreparedStatement preparedStatement = connection.prepareStatement(DISPLAY_ADMIN_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery(); // Execute Query and store in resultSet Object.
            List<Admin> adminList = new ArrayList<>();

            while (resultSet.next()){
                int a_id = resultSet.getInt("AID");
                String a_name = resultSet.getString("ANAME");
                String a_password = resultSet.getString("APASSWORD");

                // data will store in admin variable or object and data type is Admin
                Admin admin = new Admin(a_id,a_name,a_password);
                adminList.add(admin);
            }
            return adminList;
        }catch (Exception e){
            System.out.println("Display All Admin Exception : "+e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    //............. 5. Display Single Admin data..........................................
    public Admin getAdminByIdDao(int adminId){
        try {
            String DISPLAY_SINGLE_ADMIN_QUERY = "select * from admin where AID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(DISPLAY_SINGLE_ADMIN_QUERY);
            preparedStatement.setInt(1,adminId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int a_id = resultSet.getInt("AID");
                String a_name = resultSet.getString("ANAME");
                String a_password = resultSet.getString("APASSWORD");
                return new Admin(a_id,a_name,a_password);
            }
            System.err.println("No Admin with ID found: " + adminId);
            return null;
        }
        catch (Exception e){
            System.out.println("Display All Admin Exception : "+e.getMessage());
            return null;
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
