package com.jspider.product_management.jdbc.curd.dao;

import com.jspider.product_management.jdbc.curd.conection.ConnectionMyDb;
import com.jspider.product_management.jdbc.curd.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    Connection connection = ConnectionMyDb.getConnectionMyDb();

    // .................................... 1. insert method ...................................
    public Product saveProductDao(Product product){
        try {
            String INSERT_PRODUCT_QUERY = "insert into product(PID,PNAME,PCOLOR,PPRICE,PMFD,PEXPD) value(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_QUERY);
            preparedStatement.setInt(1,product.getProduct_id());
            preparedStatement.setString(2,product.getProduct_name());
            preparedStatement.setString(3,product.getProduct_color());
            preparedStatement.setDouble(4,product.getProduct_price());
            preparedStatement.setObject(5,product.getProduct_mfd());
            preparedStatement.setObject(6,product.getProduct_exp());

            int a = preparedStatement.executeUpdate();

            return a!= 0 ? product:null;
        } catch (Exception e) {
            System.err.println("Exception product insert : "+e.getMessage());
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

    // .............................. 2. Delete method ......................................
    public int deleteProductByIdDao(int productId) {
        try {
            String DELETE_Product_QUERY = "delete from product where PID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_Product_QUERY);
            preparedStatement.setInt(1,productId);
            return preparedStatement.executeUpdate();
        }
        catch (Exception e) {
            System.err.println("Exception product Delete : "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Product connection Closed : "+e.getMessage());
            }
        }
    }

    // ----------------------------3. Update -----------------------------------------------
    public Product updateProductByIdDao(int productId, Product product){
        try {
            String UPDATE_PRODUCT_QUERY = "update product set PNAME=?,PCOLOR=?,PPRICE=?,PMFD=?,PEXPD=? where PID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_QUERY);

            preparedStatement.setInt(6,productId);
            preparedStatement.setString(1,product.getProduct_name());
            preparedStatement.setString(2,product.getProduct_color());
            preparedStatement.setDouble(3,product.getProduct_price());
            preparedStatement.setObject(4,product.getProduct_mfd());
            preparedStatement.setObject(5,product.getProduct_exp());

            int a = preparedStatement.executeUpdate();
            return a != 0? product:null;

        }catch (Exception e) {
            System.err.println("Exception product insert : "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Product connection Closed : "+e.getMessage());

            }
        }
    }

    // ...............4. Display all the data .....................................................
    public List<Product> getAllProductDao(){
        try {
            String DISPLAY_PRODUCT_QUERY = "select* from product";
            PreparedStatement preparedStatement = connection.prepareStatement(DISPLAY_PRODUCT_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>();

            while (resultSet.next()){
                int p_id = resultSet.getInt("PID");
                String p_name = resultSet.getString("PNAME");
                String p_color = resultSet.getString("PCOLOR");
                double p_price = resultSet.getDouble("PPRICE");
                LocalDate p_mfd = resultSet.getDate("PMFD").toLocalDate();
                LocalDate p_expd = resultSet.getDate("PEXPD").toLocalDate();

                Product product = new Product(p_id,p_name,p_color,p_price,p_mfd,p_expd);
                productList.add(product);
            }
            return productList;
        }
        catch (Exception e) {
            System.err.println("Exception product Display All : "+e.getMessage());
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

    //............. 5. Display Single Admin data..........................................
    public Product getProductByIdDao(int productId){
        try {
            String DISPLAY_SINGLE_PRODUCT_QUERY = "select * from product where PID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(DISPLAY_SINGLE_PRODUCT_QUERY);
            preparedStatement.setInt(1,productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                int p_id = resultSet.getInt("PID");
                String p_name = resultSet.getString("PNAME");
                String p_color = resultSet.getString("PCOLOR");
                double p_price = resultSet.getDouble("PPRICE");
                LocalDate p_mfd = resultSet.getDate("PMFD").toLocalDate();
                LocalDate p_expd = resultSet.getDate("PEXPD").toLocalDate();

                return new Product(p_id,p_name,p_color,p_price,p_mfd,p_expd);
            }
            System.err.println("No Product found with ID: " + productId);
            return null;
        }
        catch (Exception e) {
            System.err.println("Exception product Display By Id : "+e.getMessage());
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
