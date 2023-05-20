package model;

import java.time.LocalDate;
import java.util.*;

import static constants.InformationConstants.*;

public class User extends Person {
    private final String email;
    private String password;
    private final List<Product> cart = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    public User(String name, String phoneNumber, Address address, String email, String password) {
        super(name, phoneNumber, address);
        this.email = email;
        this.password = password;
    }

    public void addToCart(Product product) {
        if (cart.contains(product)) {
            System.out.println(EXIST_IN_CART);
            return;
        }
        cart.add(product);
        System.out.println(ADD_CART);
    }

    public List<Product> getCart() {
        return cart;
    }

    public void printCart() {
        System.out.println(CART);

        if (cart.size() == 0)
            System.out.println(EMPTY_CART);

        for (int i=0; i < cart.size(); i++) {
            Product product = cart.get(i);
            System.out.println("Index " + (i+1) + ", Name " + product.getName() + ", Price" + product.getPrice());
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
        System.out.println(ADD_ORDER);
    }

    public void printOrders() {
        LocalDate currentDate = LocalDate.now();

        System.out.println(FUTURE_ORDERS);
        for (Order order: orders)
            if (order.getDeliveryDate().isAfter(currentDate))
                System.out.println(order);

        System.out.println(PAST_ORDERS);
        for (Order order: orders)
            if (order.getDeliveryDate().isBefore(currentDate))
                System.out.println(order);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                " name='" + this.getName() + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                ", address=" + this.getAddress() +
                '}';
    }
}