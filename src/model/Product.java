package model;

import database.DatabaseManager;
import util.Category;

import java.util.Scanner;

import static constants.InformationConstants.*;

public class Product {
    private String name;
    private final Category category;
    private double price;
    private int id;
    private final static Scanner scanner = new Scanner(System.in);

    public Product(String name, Category category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product(String name, Category category, double price, int id) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.id = id;
    }

    // Function to create a product
    public static Product addProduct() {
        System.out.println(ADD_PRODUCT);

        System.out.println(VALUE + "name: ");
        String name = scanner.nextLine();

        Category category;
        while (true) {
            try {
                System.out.println(VALUE + "category:(Fashion, Pet, Sport, Games) ");
                category = Category.valueOf(scanner.nextLine());
                break;
            }
            catch (IllegalArgumentException e) {
                System.out.println(CATEGORY_INVALID + " " + TRY_AGAIN);
            }
        }

        double price;
        while (true) {
            try {
                System.out.println(VALUE + "price: ");
                price = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch (NumberFormatException e) {
                System.out.println(WRONG_INPUT + " " + TRY_AGAIN);
            }
        }

        return new Product(name, category, price);
    }

    // Function to update a product
    public void updateProduct() {
        System.out.println(UPDATE_PRODUCT);

        System.out.println(VALUE + "name: ");
        String name = scanner.nextLine();
        setName(name);

        double price;
        while (true) {
            try {
                System.out.println(VALUE + "price: ");
                price = Double.parseDouble(scanner.nextLine());
                break;
            }
            catch (NumberFormatException e) {
                System.out.println(WRONG_INPUT + " " + TRY_AGAIN);
            }
        }
        setPrice(price);

        DatabaseManager.getInstance().updateProduct(this);
    }

    // Setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // ToString function
    @Override
    public String toString() {
        return "Product{" +
                " name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}