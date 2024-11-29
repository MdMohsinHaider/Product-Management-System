import com.jspider.product_management.jdbc.curd.entity.Admin;
import com.jspider.product_management.jdbc.curd.entity.Product;
import com.jspider.product_management.jdbc.curd.entity.User;
import com.jspider.product_management.jdbc.curd.service.AdminService;
import com.jspider.product_management.jdbc.curd.service.ProductService;
import com.jspider.product_management.jdbc.curd.service.UserService;

// 1. Login.
    // 1.1 Admin Login.
        //  1.1.1 Add Product.
        //  1.1.2 delete Product.
        //  1.1.3 Display Product.
        //  1.1.4 Update Product.
    // 1.2 User Login.
        //  1.2.1 Search Product by Name.
        //  1.2.2 Displays All Products.
// 2. Register.
    // 2.1 Register Admin.
    // 2.1 Register User.


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// 1. Login Functionality
    // 1.1 Admin Login
        // 1.1.1 Add Product
        //          Allows an admin to add a new product to the inventory. Validates product details before saving.
        //  1.1.2 Delete Product
        //          Enables an admin to remove a product from the inventory based on its unique identifier.
        //  1.1.3 Displays Product
        //          Fetches and displays details of all products or a specific product as requested by the admin.
        //  1.1.4 Update Product
        //          Allows an admin to modify existing product details such as name, price, or stock.

    // 1.2 User Login
        //  1.2.1 Search Product by Name
        //        Provides users the ability to search for a product by its name and view its details.
        //  1.2.2 Display All Products
        //        Displays the entire product catalog available in the inventory for user browsing.

// 2. Registration Functionality
    // 2.1 Register Admin
    //        Facilitates the registration of a new admin with necessary validations for unique credentials.
    // 2.2 Register User
    //        Handles the creation of user accounts by collecting and verifying basic user information.



public class ProductManagementController {

    // Main Method ...........................
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! Select an option:");
        System.out.println("1. Login");
        System.out.println("2. Register");

        int choice = scanner.nextInt();

        if (choice == 1 || choice == 2){
            switch (choice) {
                case 1: // Login
                    handleLogin(scanner);
                    break;
                case 2: // Register
                    handleRegister(scanner);
                    break;
                default:
                    System.out.println("Invalid choice! Please select 1 or 2.");
            }
        } else {
        System.err.println("Wrong Input Choice");
        System.exit(0);
    }
    }

    // 1. Method to handle Login .................................
    public static void handleLogin(Scanner scanner) {
        System.out.println(" 🏳️🏳️ Login as:---------------------------------------------- 🏳️🏳️");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");

        int loginChoice = scanner.nextInt();
        AdminService adminService = new AdminService();
        UserService userService = new UserService();

        if (loginChoice == 1 || loginChoice ==2) {
            switch (loginChoice) {
                case 1: // Admin Login
                    System.out.print("Enter Admin Id : ");
                    int a_id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Admin Password : ");
                    String a_password = scanner.next();
                    if (adminService.loginAdmin(a_id, a_password))
                        handleAdminActions(scanner);
                    else System.out.println("Invalid Admin ");
                    break;
                case 2: // User Login
                    System.out.print("Enter User Id : ");
                    int u_id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter user Password : ");
                    String u_password = scanner.nextLine();

                    if (userService.loginUser(u_id, u_password)) {
                        handleUserActions(scanner);
                    } else System.err.println("check user input details");
                    break;
                default:
                    System.err.println("Invalid login choice!");
            }
        }else {
            System.err.println("Wrong Input Choice");
            System.exit(0);
        }
    }

    // 2. Method to handle Admin actions .......................................
    public static void handleAdminActions(Scanner scanner) {

        char ch;

        do {

            System.out.println("🏳️🏳️ Admin Options: 🏳️🏳️\n-------------------\n");
            System.out.println("1. Add Product");
            System.out.println("2. Delete Product");
            System.out.println("3. Update Product");
            System.out.println("4. Display Products");
            System.out.println("5. Exit ❌❌❌");

            ProductService productService = new ProductService();

            int adminChoice = scanner.nextInt();

            if (adminChoice <=5 && adminChoice >=0) {
                switch (adminChoice) {
                    case 1:
                        System.out.println("🏳️🏳️ Add Product.🏳️🏳️");
                        System.out.print("Enter Product Id : ");
                        int p_id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Product Name : ");
                        String p_name = scanner.nextLine();
                        System.out.print("Enter Product Color : ");
                        String p_color = scanner.nextLine();
                        System.out.print("Enter Product Price : ");
                        double p_price = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Product Manufacture Date : ");
                        String p_mfd = scanner.next();
                        System.out.print("Enter Product Expired Date : ");
                        String p_exp = scanner.next();

                        Product product = new Product(p_id, p_name, p_color, p_price, LocalDate.parse(p_mfd), LocalDate.parse(p_exp));
                        Product product1 = productService.saveProductService(product);

                        String msg = product1 != null ? "Successfully data Store" : "data not store";
                        System.out.println(msg);

                        break;
                    case 2:
                        System.out.println("🏳️🏳️ Delete Product 🏳️🏳️");
                        System.out.println("Enter Product Id");
                        int p_id1 = scanner.nextInt();
                        int a = productService.deleteProductByIdService(p_id1);

                        String d_msg = a != 0 ? "Delete Successfully" : "Invalid Product Id or check the Id";
                        System.out.println(d_msg);

                        break;
                    case 3:
                        System.out.println("🏳️🏳️ Update Product functionality.🏳️🏳️");
                        System.out.println("🏳️🏳️ Add Product.🏳️🏳️");
                        System.out.print("Enter Product Id : ");
                        int p_id2 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Product Name : ");
                        String p_name2 = scanner.nextLine();
                        System.out.print("Enter Product Color : ");
                        String p_color2 = scanner.nextLine();
                        System.out.print("Enter Product Price : ");
                        double p_price2 = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Product Manufacture Date : ");
                        String p_mfd2 = scanner.next();
                        System.out.print("Enter Product Expired Date");
                        String p_exp2 = scanner.next();

                        Product product2 = new Product();
                        product2.setProduct_name(p_name2);
                        product2.setProduct_color(p_color2);
                        product2.setProduct_price(p_price2);
                        product2.setProduct_mfd(LocalDate.parse(p_mfd2));
                        product2.setProduct_exp(LocalDate.parse(p_exp2));

                        Product product3 = productService.updateProductByIdService(p_id2, product2);
                        String msg_update = product3 != null ? "Successfully update " : " not update";
                        System.out.println(msg_update);

                        break;
                    case 4:
                        System.out.println("🏳️🏳️Display Product🏳️🏳️");
                        List<Product> productList = productService.getAllProductService();
                        for (Product product4 : productList) {
                            System.out.println(product4);
                        }
                        break;
                    case 5:
                        System.out.println(" Exiting the program...");
                        System.exit(0); // Terminate the program
                        break;
                    default:
                        System.out.println("Invalid admin action!");
                }
            }else {
                System.err.println("Wrong Input Choice");
                System.exit(0);
            }

            // Do While Continuity
            System.out.print("\nAre You continue Y or y : and Exit other \t");
            ch = scanner.next().charAt(0);
            if (!(ch == 'Y' || ch =='y')) {
                System.out.println("Program Exit Successful");
                System.exit(0);
            }
        }while (ch == 'Y' || ch == 'y');
        System.out.println(" Program End ...Thank You..! ");
    }

    // 3.  Method to handle User actions ...................................
    public static void handleUserActions(Scanner scanner) {
        char ch;
        do {

            System.out.println("🏳️🏳️ Successfully Longed In 🏳️🏳️");

            System.out.println("🏳️🏳️ User Options:🏳️🏳️\n-------------------\n");
            System.out.println("1. Search Product by Name");
            System.out.println("2. Display All Products");
            System.out.println("3. Display All Product by Low to High Price");
            System.out.println("4. Display All Product by High to Low Price");
            System.out.println("5. Exit ❌❌❌");

            ProductService productService = new ProductService();

            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println(" 🏳️ ️️️🏳️ Search Product by Name 🏳️🏳️ ");
                    System.out.print("Enter Product name : ");
                    scanner.nextLine();
                    String product_name = scanner.nextLine();
                    List<Product> productListByName =productService.filterAllProductByNameService(product_name);
                    if (productListByName.isEmpty()){
                        System.out.println("Item Not Found");
                    }else {
                        for (Product product : productListByName){
                            System.out.println(product);
                        }
                    }
                    break;
                case 2:
                    System.out.println("🏳️🏳️ Display All Products 🏳️🏳️");
                    List<Product> productList = productService.getAllProductService();
                    for (Product product : productList){
                        System.out.println(product);
                    }
                    break;
                case 3:
                    System.out.println(" 🏳️🏳️Product Low to High Price🏳️🏳️");
                    List<Product> productList1 = productService.filterAllProductsByPriceAscending();
                    for (Product product: productList1){
                        System.out.println(product);
                    }
                    break;
                case 4:
                    System.out.println("Product High to low Price");
                    List<Product> productList2 = productService.filterAllProductsByPriceDescending();
                    for (Product product: productList2){
                        System.out.println(product);
                    }
                    break;
                case 5:
                    System.out.println("Exiting the program... thank you....!! ");
                    System.exit(0); // Terminate the program
                    break;

                default:
                    System.out.println("Invalid user action!");
            }


            // Do While Continuity
            System.out.print("\nAre You continue Y or y : \t");
            ch = scanner.next().charAt(0);
            if (!(ch == 'Y' || ch =='y')){
                System.out.println("Program Exit Successful");
                System.exit(0);
            }

        }while (ch == 'Y' || ch =='y');
        System.out.println(" Program End ...Thank You..! ");
    }

    // 4.  Method to handle Registration ...............................................
    public static void handleRegister(Scanner scanner) {
        System.out.println("🏳️🏳️ Register as:-------------------------------------------------- 🏳️🏳️");
        System.out.println("1. Admin");
        System.out.println("2. User");

        AdminService adminService = new AdminService();
        UserService userService = new UserService();

        int registerChoice = scanner.nextInt();

        if (registerChoice ==1 || registerChoice == 2) {
            switch (registerChoice) {
                case 1:
                    System.out.println("🏳️🏳️ Register Admin 🏳️🏳️");
                    System.out.print("Enter Admin Id : ");
                    int a_id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Admin Name : ");
                    String a_name = scanner.nextLine();
                    System.out.print("Enter Admin Password : ");
                    String a_password = scanner.next();
                    Admin admin = new Admin(a_id, a_name, a_password);
                    Admin admin1 = adminService.registerAdmin(admin);
                    System.out.println("Adding Admin Details Successfully in databases : " + admin1);
                    break;
                case 2:
                    System.out.println("🏳️🏳️ Register User 🏳️🏳️");
                    System.out.print("Enter USer Id : ");
                    int u_id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter User Name : ");
                    String u_name = scanner.nextLine();
                    System.out.print("Enter User Email : ");
                    String u_email = scanner.nextLine();
                    System.out.print("Create User Password : ");
                    String u_password = scanner.nextLine();

                    User user = new User(u_id, u_name, u_password, u_email);
                    User user1 = userService.registerUser(user);
                    System.out.println(user1);
                    break;
                default:
                    System.out.println("Invalid registration choice!");
            }
        }else {
            System.err.println("Wrong Input Choice");
            System.exit(0);
        }
    }
}