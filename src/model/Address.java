package model;

import java.util.Scanner;

import static constants.InformationConstants.*;

public class Address {
    private String county;
    private String city;
    private String street;
    private String number;
    private String entrance;
    private String floor = "P";

    // Constructor for apartments
    public Address(String county, String city, String street, String number, String entrance, String floor) {
        this.county = county;
        this.city = city;
        this.street = street;
        this.number = number;
        this.entrance = entrance;
        this.floor = floor;
    }

    // Constructor for houses
    public Address(String county, String city, String street, String number) {
        this.county = county;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    // Function to create an Address
    public static Address addAddress() {
        Scanner scanner = new Scanner(System.in);
        char choice;

        System.out.println(VALUE + "county: ");
        String county = scanner.nextLine();
        System.out.println(VALUE + "city: ");
        String city = scanner.nextLine();
        System.out.println(VALUE + "street: ");
        String street = scanner.nextLine();
        System.out.println(VALUE + "number: ");
        String number = scanner.nextLine();

        System.out.println("Does it have an entrance? (y/n) ");
        while (true){
            choice = scanner.next().toUpperCase().charAt(0);

            if ('Y' != choice && choice != 'N')
                System.out.println(CHOICE_INVALID);
            else
                break;
        }

        if (choice == 'Y') {
            System.out.println(VALUE + "entrance: ");
            scanner.nextLine();
            String entrance = scanner.nextLine();

            System.out.println(VALUE + "floor: ");
            String floor = scanner.nextLine();

            return new Address(county, city, street, number, entrance, floor);
        }

        return new Address(county, city, street,number);
    }

    // Setters and Getters
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    // ToString function
    @Override
    public String toString() {
        return "Address: " +
                "county " + county +
                ", city " + city +
                ", street " + street +
                ", number " + number +
                ((entrance == null) ? "" : (", entrance " + entrance + ", floor " + floor));
    }
}