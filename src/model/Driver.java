package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static constants.ExceptionConstants.NO_DRIVER_DELIVERIES;

public class Driver extends Person {
    private final List<Order> deliveries = new ArrayList<>();

    public Driver(String name, String phoneNumber, Address address) {
        super(name, phoneNumber, address);
    }

    // Function to add a delivery
    public void addDelivery(Order delivery) {
        deliveries.add(delivery);
    }

    // Function to print today's deliveries
    public void printTodayDeliveries() {
        if (deliveries.size() == 0) {
            System.out.println(NO_DRIVER_DELIVERIES);
            return;
        }

        for (Order delivery: deliveries)
            if (delivery.getDeliveryDate().equals(LocalDate.now()))
                System.out.println(delivery);
    }

    // Getter
    public List<Order> getDeliveries() {
        return deliveries;
    }

    @Override
    public String toString() {
        return "Driver{" +
                ", name='" + this.getName() + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                ", address=" + this.getAddress() +
                "deliveries=" + deliveries +
                '}';
    }
}