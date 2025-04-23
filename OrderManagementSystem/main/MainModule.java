package main;

import dao.OrderProcessor;
import entity.*;
import exception.*;
import java.util.*;

public class MainModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderProcessor processor = new OrderProcessor();

        while (true) {
            System.out.println("\n====== Order Management System ======");
            System.out.println("1. Create User");
            System.out.println("2. Create Product");
            System.out.println("3. Create Order");
            System.out.println("4. Cancel Order");
            System.out.println("5. Get All Products");
            System.out.println("6. Get Orders by User");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter User ID: ");
                        int userId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter Password: ");
                        String password = scanner.nextLine();
                        System.out.print("Enter Role (Admin/User): ");
                        String role = scanner.nextLine();

                        User newUser = new User(userId, username, password, role);
                        processor.createUser(newUser);
                        break;

                    case 2:
                        System.out.print("Enter Admin User ID: ");
                        int adminId = scanner.nextInt();
                        scanner.nextLine();
                        User admin = processor.getUserById(adminId);

                        System.out.print("Enter Product ID: ");
                        int productId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Product Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter Price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter Quantity in Stock: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Type (Electronics/Clothing): ");
                        String type = scanner.nextLine();

                        Product product;
                        if (type.equalsIgnoreCase("Electronics")) {
                            System.out.print("Enter Brand: ");
                            String brand = scanner.nextLine();
                            System.out.print("Enter Warranty Period (months): ");
                            int warranty = scanner.nextInt();
                            scanner.nextLine();
                            product = new Electronics(productId, name, description, price, quantity, type, brand, warranty);
                        } else {
                            System.out.print("Enter Size: ");
                            String size = scanner.nextLine();
                            System.out.print("Enter Color: ");
                            String color = scanner.nextLine();
                            product = new Clothing(productId, name, description, price, quantity, type, size, color);
                        }

                        processor.createProduct(admin, product);
                        break;

                    case 3:
                        System.out.print("Enter User ID: ");
                        int orderUserId = scanner.nextInt();
                        scanner.nextLine();
                        User orderUser = processor.getUserById(orderUserId);

                        System.out.print("Enter number of products to order: ");
                        int count = scanner.nextInt();
                        scanner.nextLine();

                        List<Product> orderList = new ArrayList<>();
                        for (int i = 0; i < count; i++) {
                            System.out.print("Enter Product ID to order: ");
                            int pid = scanner.nextInt();
                            scanner.nextLine();
                            Product p = processor.getProductById(pid);
                            if (p != null) {
                                orderList.add(p);
                            } else {
                                System.out.println("Product ID " + pid + " not found.");
                            }
                        }

                        processor.createOrder(orderUser, orderList);
                        break;

                    case 4:
                        System.out.print("Enter User ID: ");
                        int cancelUserId = scanner.nextInt();
                        System.out.print("Enter Order Product ID to cancel: ");
                        int cancelProductId = scanner.nextInt();
                        processor.cancelOrder(cancelUserId, cancelProductId);
                        break;

                    case 5:
                        List<Product> allProducts = processor.getAllProducts();
                        if (allProducts.isEmpty()) {
                            System.out.println("No products available.");
                        } else {
                            for (Product prod : allProducts) {
                                System.out.println(prod);
                            }
                        }
                        break;

                    case 6:
                        System.out.print("Enter User ID: ");
                        int checkUserId = scanner.nextInt();
                        User checkUser = processor.getUserById(checkUserId);
                        List<Product> userOrders = processor.getOrderByUser(checkUser);
                        if (userOrders.isEmpty()) {
                            System.out.println("No orders found for user.");
                        } else {
                            for (Product ord : userOrders) {
                                System.out.println(ord);
                            }
                        }
                        break;

                    case 0:
                        System.out.println("Exiting program. Thank you!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (UserNotFoundException | OrderNotFoundException | InvalidUserRoleException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}
