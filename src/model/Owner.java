package model;

import service.impl.StoreServiceImpl;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static constants.ExceptionConstants.INVALID_INT;
import static constants.InformationConstants.*;

public class Owner extends Person {
    private final List<Store> stores = new ArrayList<>();
    private final StoreServiceImpl storeMenu = new StoreServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    public Owner(String name, String phoneNumber, Address address) {
        super(name, phoneNumber, address);
    }

    public void menu() {
        System.out.println(OWNER);

        while (true) {
            System.out.println(OWNER_MENU);

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
                case 1 -> printStores();
                case 2 -> addStore();
                case 3 -> {
                    System.out.println(MANAGE_STORE);

                    try {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                    }
                    catch (InputMismatchException e) {
                        System.out.println(INVALID_INT);
                        scanner.nextLine();
                        choice = -1;
                    }

                    Store store = getStoreFromIndex(choice-1);

                    if (store != null)
                        store.menu();
                }
            }
        }
    }

    // Function to create a Store
    public void addStore() {
        System.out.println(ADD_STORE);

        System.out.println(VALUE + "name: ");
        String name = scanner.nextLine();
        Address address = Address.addAddress();

        addStore(new Store(name, address));
    }

    // Function to add a Store
    public void addStore(Store store) {
        stores.add(store);
        storeMenu.addStore(store);
    }

    // Function to return store from index
    public Store getStoreFromIndex(int index) {
        if (index < 0 || index >= stores.size()) {
            System.out.println(INVALID_INT);
            return null;
        }

        return stores.get(index);
    }

    public void printStores() {
        for (int i=0; i <stores.size(); i++)
            System.out.println("Index " + (i+1) + " " + stores.get(i));
    }

    // ToString function
    @Override
    public String toString() {
        return "Owner{" +
                ", name='" + this.getName() + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                ", address=" + this.getAddress() +
                "stores=" + stores +
                '}';
    }
}