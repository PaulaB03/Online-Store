import csv.AddressCSV;
import csv.Audit;
import csv.OrderCSV;
import csv.ProductCSV;
import database.DatabaseManager;
import model.*;
import service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
//        List<Address> addresses = AddressCSV.getInstance().readAddressFromCSV();
        List<Address> addresses = DatabaseManager.getInstance().getAllAddresses();

        Owner owner = new Owner("Alex", "0788899989", addresses.get(0));
        Store store = new Store("Country Mart", addresses.get(1));

//        List<Product> products = ProductCSV.getInstance().readProductsFromCSV();
        List<Product> products = DatabaseManager.getInstance().getAllProducts();
        for (int i=0; i < products.size() ; i++)
            store.addProduct(products.get(i));


        // Add some stores
        owner.addStore(store);

        owner.menu();
        new UserServiceImpl();
//
        // Print driver deliveries
//        List<Order> orders = OrderCSV.getInstance().readOrdersFromCSV();
        List<Order> orders = DatabaseManager.getInstance().getAllOrders();
        Driver driver = new Driver("Alin", "0784587457", addresses.get(4));
        for (Order order: orders)
            driver.addDelivery(order);

        driver.printTodayDeliveries();
    }
}