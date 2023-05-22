package database;

import model.Address;
import model.Order;
import model.Product;
import model.User;
import util.Category;
import util.PayMethod;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private static DatabaseManager instance;

    private DatabaseManager() {}

    public static synchronized DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();

        return instance;
    }

    public List<Product> getAllProducts() {
        String selectSQL = "SELECT * FROM products";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Product> products = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                double price = resultSet.getDouble("price");

                products.add(new Product(name, Category.valueOf(category), price, id));
            }
        }
        catch (SQLException e) {
            System.out.println("Error reading products from database: " + e.getMessage());
        }

        return products;
    }

    public void insertProduct(Product product) {
        String insertSQL = "INSERT INTO products (name, category, price) VALUES (?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCategory().toString());
            preparedStatement.setDouble(3, product.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                product.setId(generatedId);
            }
        } catch (SQLException e) {
            System.out.println("Error inserting product into database: " + e.getMessage());
        }
    }

    public void updateProduct(Product product) {
        String updateSQL = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error updating product in database: " + e.getMessage());
        }
    }

    public void removeProduct(int productId) {
        String deleteSQL = "DELETE FROM products WHERE id = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, productId);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error removing product from database: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String selectSQL = "SELECT * FROM users";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String number = resultSet.getString("number");
                String entrance = resultSet.getString("entrance");
                String floor = resultSet.getString("floor");

                users.add(new User(name, phone, new Address(country, city, street, number, entrance, floor), email, password));
            }
        }
        catch (SQLException e) {
            System.out.println("Error reading addresses from database: " + e.getMessage());
        }

        return users;
    }

    public void insertUser(User user) {
        String insertSQL = "INSERT INTO users (name, phone, email, password, country, city, street, number, entrance, floor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddress().getCounty());
            preparedStatement.setString(6, user.getAddress().getCity());
            preparedStatement.setString(7, user.getAddress().getStreet());
            preparedStatement.setString(8, user.getAddress().getNumber());
            preparedStatement.setString(9, user.getAddress().getEntrance());
            preparedStatement.setString(10, user.getAddress().getFloor());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error inserting user into database: " + e.getMessage());
        }
    }

    public void updateUserName(String email, String newName) {
        String updateSQL = "UPDATE users SET name = ? WHERE email = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error updating user name in the database: " + e.getMessage());
        }
    }

    public void updateUserPhone(String email, String newPhone) {
        String updateSQL = "UPDATE users SET phone = ? WHERE email = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, newPhone);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error updating user phone number in the database: " + e.getMessage());
        }
    }

    public void updateUserPassword(String email, String newPassword) {
        String updateSQL = "UPDATE users SET password = ? WHERE email = ?";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error updating user password in the database: " + e.getMessage());
        }
    }

    public List<Address> getAllAddresses() {
        String selectSQL = "SELECT * FROM address";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Address> addresses = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) {
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                String street = resultSet.getString("street");
                String number = resultSet.getString("number");
                String entrance = resultSet.getString("entrance");
                String floor = resultSet.getString("floor");

                addresses.add(new Address(country, city, street, number, entrance, floor));
            }
        }
        catch (SQLException e) {
            System.out.println("Error reading addresses from database: " + e.getMessage());
        }

        return addresses;
    }

    public List<Order> getAllOrders() {
        String selectSQL = "SELECT * FROM orders";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Order> orders = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) {
                LocalDate date = resultSet.getDate("order_date").toLocalDate();
                String payment = resultSet.getString("payment");
                String products = resultSet.getString("products");

                orders.add(new Order(createOrderList(products), PayMethod.valueOf(payment), date));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error reading orders from database: " + e.getMessage());
        }

        return orders;
    }

    public void insertOrder(Order order) {
        String insertSQL = "INSERT INTO orders (order_date, payment, products) VALUES (?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(order.getOrderDate()));
            preparedStatement.setString(2, order.getPayMethod().toString());
            preparedStatement.setString(3, createProductString(order.getProducts()));

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error inserting order into database: " + e.getMessage());
        }
    }

    private Map<Product, Integer> createOrderList(String list) {
        String[] columns = list.split(", ");
        Map<Product, Integer> products = new HashMap<>();

        for (int i=0; i < columns.length; i+= 4) {
            String productName = columns[i];
            Category productCategory = Category.valueOf(columns[i+1]);
            double productPrice = Double.parseDouble(columns[i + 2]);
            Integer quantity = Integer.parseInt(columns[i+3]);

            products.put(new Product(productName, productCategory, productPrice), quantity);
        }

        return products;
    }

    private String createProductString(Map<Product, Integer> products) {
        StringBuilder line = new StringBuilder();

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            line.append(", ").append(product.getName()).append(", ").append(product.getCategory()).append(", ").append(product.getPrice()).append(", ").append(entry.getValue());
        }

        // Remove the leading comma and space
        if (line.length() > 0) {
            line.delete(0, 2);
        }

        return line.toString();
    }
}
