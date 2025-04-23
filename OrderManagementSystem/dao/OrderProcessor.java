package dao;

import entity.Product;
import entity.User;
import exception.UserNotFoundException;
import exception.OrderNotFoundException;
import exception.InvalidUserRoleException;
import java.util.ArrayList;
import java.util.List;

public class OrderProcessor implements IOrderManagementRepository {
    private List<User> users = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Product> orders = new ArrayList<>();
    
    @Override
    public void createOrder(User user, List<Product> products) throws UserNotFoundException {
        if (!users.contains(user)) {
            throw new UserNotFoundException("User not found");
        }
        orders.addAll(products);
        System.out.println("Order created successfully.");
    }

    @Override
    public void cancelOrder(int userId, int orderId) throws UserNotFoundException, OrderNotFoundException {
        User user = users.stream().filter(u -> u.getUserId() == userId).findFirst().orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        Product order = orders.stream().filter(p -> p.getProductId() == orderId).findFirst().orElse(null);
        if (order == null) {
            throw new OrderNotFoundException("Order not found");
        }
        orders.remove(order);
        System.out.println("Order canceled successfully.");
    }

    @Override
    public void createProduct(User user, Product product) throws InvalidUserRoleException {
        if (!user.getRole().equals("Admin")) {
            throw new InvalidUserRoleException("Only Admin can create products");
        }
        products.add(product);
        System.out.println("Product created successfully.");
    }

    @Override
    public void createUser(User user) {
        users.add(user);
        System.out.println("User created successfully.");
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public List<Product> getOrderByUser(User user) {
        return orders;
    }

    public User getUserById(int userId) throws UserNotFoundException {
        return users.stream().filter(u -> u.getUserId() == userId)
            .findFirst().orElseThrow(() -> new UserNotFoundException("User not found"));
    }
    
    public Product getProductById(int productId) {
        return products.stream().filter(p -> p.getProductId() == productId).findFirst().orElse(null);
    }

    

}
