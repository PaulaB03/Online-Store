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
        System.out.println("Today's deliveries:");
        List<Order> todayDeliveries = new ArrayList<>();

        for (Order delivery: deliveries)
            if (delivery.getDeliveryDate().equals(LocalDate.now()))
                todayDeliveries.add(delivery);

        if (todayDeliveries.size() == 0) {
            System.out.println(NO_DRIVER_DELIVERIES);
        }
        else {
            for (Order delivery: todayDeliveries)
                System.out.println(delivery);
        }
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