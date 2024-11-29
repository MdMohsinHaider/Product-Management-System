package com.jspider.product_management.jdbc.curd.service;

import com.jspider.product_management.jdbc.curd.dao.ProductDao;
import com.jspider.product_management.jdbc.curd.entity.Product;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    ProductDao productDao = new ProductDao();

    // insert product
    public Product saveProductService(Product product){

        // Validate Product properties
        if (product == null) {
            System.err.println("Product cannot be null.");
            return null;
        }

        // Extract Product properties for validation
        int p_id = product.getProduct_id();
        String p_name = product.getProduct_name();
        String p_color = product.getProduct_color();
        double p_price = product.getProduct_price();
        LocalDate p_mfd = product.getProduct_mfd();
        LocalDate p_expd = product.getProduct_exp();

        if (p_id <=0){
            System.err.println("Product Id cannot be Negative Integer.");
            return null;
        }

        if (p_name == null || p_name.trim().isEmpty()) {
            System.err.println("Product name cannot be null or empty.");
            return null;
        }

        if (p_color == null || p_color.trim().isEmpty()) {
            System.err.println("Product color cannot be null or empty.");
            return null;
        }

        if (p_price <= 0) {
            System.err.println("Product price must be greater than 0.");
            return null;
        }

        if (p_mfd == null || p_expd == null) {
            System.err.println("Manufacture and expiry dates cannot be null.");
            return null;
        }

        if (p_mfd.isAfter(p_expd)) {
            System.err.println("Manufacture date cannot be after expiry date.");
            return null;
        }

        return productDao.saveProductDao(product);
    }

    // Delete Product
    public int deleteProductByIdService(int productId) {
        // Validate the productId
        if (productId <= 0) {
            System.err.println("Product ID must be a positive integer.");
            return 0;
        }
        return productDao.deleteProductByIdDao(productId);
    }

    // Product update
    public Product updateProductByIdService(int productId, Product product){
        // Validate productId
        if (productId <= 0) {
            System.err.println("Product ID must be a positive integer.");
            return null;
        }
        // Validate product object
        if (product == null) {
            System.err.println("Product details cannot be null.");
            return null;
        }
        return productDao.updateProductByIdDao(productId,product);
    }

    // Display All Product
    public List<Product> getAllProductService(){
        // Retrieve all products
        List<Product> products = productDao.getAllProductDao();

        // Handle the case where no products are found
        if (products == null || products.isEmpty()) {
            System.err.println("No products available.");
            return null;
        }

        return products;
    }

    // Display Product by id.
    public Product getProductByIdService(int productId){
        // Validate productId
        if (productId <= 0) {
            System.err.println("Product ID must be a positive integer.");
            return null;
        }

        // Fetch the product from the Product DAO.
        Product product = productDao.getProductByIdDao(productId);

        // Handle the case where the product is not found
        if (product == null) {
            System.err.println("No product found with the given ID: " + productId);
            return null;
        }
        return product;
    }



//   🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾🧾

    // filter product by name.
    public List<Product> filterAllProductByNameService(String product_name){
        List<Product> product_list_by_name = productDao.getAllProductDao();
        return product_list_by_name.stream()  // Convert the list to a stream
                .filter(a -> a.getProduct_name().equals(product_name)) //
                .collect(Collectors.toList()); // Collect the filtered laptops back into a list
    }


    // Filter products by price in descending order
    public List<Product> filterAllProductsByPriceDescending() {
        List<Product> productList = productDao.getAllProductDao();
        return productList.stream()  // Convert the list to a stream
                .sorted(Comparator.comparingDouble(Product::getProduct_price).reversed()) // Sort by price descending
                .collect(Collectors.toList()); // Collect the sorted products back into a list
    }

    // Filter products by price in ascending order
    public List<Product> filterAllProductsByPriceAscending() {
        List<Product> productList = productDao.getAllProductDao();
        return productList.stream()  // Convert the list to a stream
                .sorted(Comparator.comparingDouble(Product::getProduct_price)) // Sort by price ascending
                .collect(Collectors.toList()); // Collect the sorted products back into a list
    }


}
