package csv;

import model.Product;
import util.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCSV {
    private static ProductCSV instance;

    private ProductCSV() {}

    public static synchronized ProductCSV getInstance() {
        if (instance == null)
            instance = new ProductCSV();

        return instance;
    }

    // Read products from CSV
    public List<Product> readProductsFromCSV() {
        Audit.getInstance().writeAudit("readProductsFromCSV");
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/csv/files/products.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(", ");
                String name = columns[0];
                Category category = Category.valueOf(columns[1]);
                double price = Double.parseDouble(columns[2]);

                Product product = new Product(name, category, price);
                products.add(product);
            }
        } catch (IOException e) {
            System.out.println("Error reading products from CSV: " + e.getMessage());
        }

        return products;
    }

    // Write product in CSV
    public void writeProductToCSV(Product product) {
        Audit.getInstance().writeAudit("writeProductFromCSV");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/csv/files/products.csv", true))) {
            String line = product.getName() + ", " + product.getCategory() + ", " + product.getPrice();
            writer.write(line);
            writer.newLine();
        }
        catch (IOException e) {
            System.out.println("Error writing product to CSV: " + e.getMessage());
        }
    }
}
