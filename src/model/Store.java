package model;


import csv.ProductCSV;
import exceptions.ProductException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static constants.ExceptionConstants.*;
import static constants.InformationConstants.*;

public class Store {
    private String storeName;
    private Address deposit;
    private final List<Product> products = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);

    public Store(String storeName, Address deposit) {
        this.storeName = storeName;
        this.deposit = deposit;
    }

    public void menu() {
        while (true) {
            System.out.println(STORE_MENU);

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            catch (InputMismatchException e) {
                System.out.println(INVALID_INT);
                scanner.nextLine();
                choice = -1;
            }

            if (choice == 0)
                return;

            switch (choice) {
                case 1 -> printProducts();
                case 2 -> addProduct();
                case 3 -> {
                    System.out.println(REMOVE_PRODUCT);

                    try {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                    }
                    catch (InputMismatchException e) {
                        System.out.println(INVALID_INT);
                        scanner.nextLine();
                        choice = -1;
                    }

                    try {
                        removeProduct(choice-1);
                    }
                    catch (ProductException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.println(UPDATING_PRODUCT);

                    try {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                    }
                    catch (InputMismatchException e) {
                        System.out.println(INVALID_INT);
                        scanner.nextLine();
                        choice = -1;
                    }

                    try {
                        getProductFromIndex(choice-1).updateProduct();
                    }
                    catch (ProductException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public void printProducts() {
        for (int i=0; i < products.size(); i++)
            System.out.println("Index " + (i+1) + " " + products.get(i));
    }

    public Product getProductFromIndex(int index) {
        if (index < 0 || index >= products.size())
            throw new ProductException(NO_PRODUCT);

        return products.get(index);
    }

    public void removeProduct(int index) {
        if (index < 0 || index >= products.size())
            throw new ProductException(NO_PRODUCT);

        products.remove(index);
        System.out.println(REMOVED_PRODUCT);
    }

    public void addProduct() {
        Product product = Product.addProduct();
        products.add(product);
        ProductCSV.getInstance().writeProductToCSV(product);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Address getDeposit() {
        return deposit;
    }

    public void setDeposit(Address deposit) {
        this.deposit = deposit;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                ", deposit='" + deposit + '\'' +
                ", products=" + products +
                '}';
    }
}