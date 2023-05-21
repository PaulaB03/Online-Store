package csv;

import model.Order;
import model.Product;
import util.Category;
import util.PayMethod;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderCSV {
    private static OrderCSV instance;

    private OrderCSV() {}

    public static synchronized OrderCSV getInstance() {
        if (instance == null)
            instance = new OrderCSV();

        return instance;
    }

    // Read orders from CSV
    public List<Order> readOrdersFromCSV() {
        Audit.getInstance().writeAudit("readOrdersFromCSV");
        List<Order> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/csv/files/orders.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] columns = line.split(", ");
                    LocalDate date = LocalDate.parse(columns[0]);
                    PayMethod payMethod = PayMethod.valueOf(columns[1]);
                    Map<Product, Integer> products = new HashMap<>();

                    for (int i = 2; i < columns.length; i += 4) {
                        String productName = columns[i];
                        Category productCategory = Category.valueOf(columns[i + 1]);
                        double productPrice = Double.parseDouble(columns[i + 2]);
                        Integer quantity = Integer.parseInt(columns[i + 3]);

                        products.put(new Product(productName, productCategory, productPrice), quantity);
                    }

                    Order order = new Order(products, payMethod, date);
                    orders.add(order);
                }
                catch (DateTimeParseException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error parsing line: " + line + ". Skipping this line.");
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Error parsing PayMethod or Category: " + e.getMessage() + ". Skipping this line.");
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error reading orders from CSV: " + e.getMessage());
        }

        return orders;
    }

    // Write order in CSV
    public void writeOrderToCSV(Order order) {
        Audit.getInstance().writeAudit("writeOrderFromCSV");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/csv/files/orders.csv", true))) {
            StringBuilder line = new StringBuilder(order.getOrderDate() + ", " + order.getPayMethod());

            for (Map.Entry<Product, Integer> entry: order.getProducts().entrySet()) {
                Product product = entry.getKey();
                line.append(", ").append(product.getName()).append(", ").append(product.getCategory()).append(", ").append(product.getPrice()).append(", ").append(entry.getValue());
            }

            writer.write(line.toString());
            writer.newLine();
        }
        catch (IOException e) {
            System.out.println("Error writing orders to CSV: " + e.getMessage());
        }
    }
}
