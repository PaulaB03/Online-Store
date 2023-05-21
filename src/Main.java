import csv.AddressCSV;
import csv.Audit;
import csv.OrderCSV;
import csv.ProductCSV;
import model.*;
import service.impl.UserServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Address> addresses = AddressCSV.getInstance().readAddressFromCSV();

        Owner owner = new Owner("Alex", "0788899989", addresses.get(0));
        Store store = new Store("Game Heaven", addresses.get(1));
        Store store1 = new Store("Chic Boutique", addresses.get(2));
        Store store2 = new Store("Pet Shop", addresses.get(3));

        List<Product> products = ProductCSV.getInstance().readProductsFromCSV();
        for (int i=0; i < products.size()/3 ; i++) {
            store.addProduct(products.get(i));
            store1.addProduct(products.get(i+3));
            store2.addProduct(products.get(i+6));
        }

        // Add some stores
        owner.addStore(store);
        owner.addStore(store1);
        owner.addStore(store2);

        owner.menu();
        new UserServiceImpl();

        // Print driver deliveries
        List<Order> orders = OrderCSV.getInstance().readOrdersFromCSV();
        Driver driver = new Driver("Alin", "0784587457", addresses.get(4));
        for (Order order: orders) {
            driver.addDelivery(order);
        }

        driver.printTodayDeliveries();
    }
}