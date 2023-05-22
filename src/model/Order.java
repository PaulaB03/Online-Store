package model;

import util.PayMethod;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private final Map<Product, Integer> products;
    private final double price;
    private final PayMethod payMethod;
    private final LocalDate orderDate;
    private LocalDate deliveryDate;
    private static List<Order> orders = new ArrayList<>();

    public Order(Map<Product, Integer> products, PayMethod payMethod) {
        this.products = products;
        this.price = setPrice();
        this.payMethod = payMethod;
        this.orderDate = LocalDate.now();
        setDeliveryDate();
        orders.add(this);
    }

    public Order(Map<Product,Integer> products, PayMethod payMethod, LocalDate orderDate) {
        this.products = products;
        this.price = setPrice();
        this.payMethod = payMethod;
        this.orderDate = orderDate;
        setDeliveryDate();
        orders.add(this);
    }

    // Set order price
    private double setPrice() {
        double sum = 0;
        for (Map.Entry<Product, Integer> entry: products.entrySet())
            sum += entry.getKey().getPrice() * entry.getValue();

        return sum;
    }

    // Set delivery date (after 3 working days)
    private void setDeliveryDate() {
        deliveryDate = orderDate;

        for (int i=0; i < 3; i++) {
            deliveryDate = deliveryDate.plusDays(1);

            while (deliveryDate.getDayOfWeek() == DayOfWeek.SATURDAY || deliveryDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                deliveryDate = deliveryDate.plusDays(1);
            }
        }
    }

    // Getters
    public Map<Product, Integer> getProducts() {
        return products;
    }



    public double getPrice() {
        return price;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", price=" + price +
                ", payMethod=" + payMethod +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}