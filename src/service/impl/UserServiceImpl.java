package service.impl;

import csv.OrderCSV;
import csv.UserCSV;
import database.DatabaseManager;
import exceptions.*;
import model.*;
import service.UserService;
import util.PayMethod;

import java.util.*;

import static constants.ExceptionConstants.INVALID_INT;
import static constants.InformationConstants.*;
import static constants.InformationConstants.FORCED_EXIT;
import static validation.UserValidation.*;

public class UserServiceImpl implements UserService {
    private final Scanner scanner = new Scanner(System.in);
    private final List<User> users;
    private final StoreServiceImpl stores = new StoreServiceImpl();
    private User loggedIn = null;

    public UserServiceImpl() {
//        users = UserCSV.getInstance().readUsersFromCSV();
        users = DatabaseManager.getInstance().getAllUsers();
        System.out.println("-- User menu --");

        boolean loop = true;
        int choice;
        while (loop) {
            System.out.println(USER_MENU);

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                choice = -1;
            }

            if (0 > choice || choice > 2)
                System.out.println(INVALID_INT);

            switch (choice) {
                case 0 -> {
                    loop = false;
                    printUsers();
                    System.out.println(EXIT_USER_MENU);
                }
                case 1 -> register();
                case 2 -> {
                    login();
                    if (loggedIn != null)
                        loginMenu();
                }
            }
        }
    }

    private void loginMenu() {
        // Check if the user is logged in
        if (loggedIn == null) {
            System.out.println(NO_LOGIN);
            return;
        }

        boolean loop = true;
        int choice;
        while (loop) {
            System.out.println(LOGIN_MENU);

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                choice = -1;
            }

            if (0 > choice || choice > 5)
                System.out.println(INVALID_INT);

            switch (choice) {
                case 0 -> {
                    loop = false;
                    logout();
                }
                case 1 -> updateInformation();
                case 2 -> browseStores();
                case 3 -> loggedIn.printCart();
                case 4 -> placeOrder();
                case 5 -> loggedIn.printOrders();
            }
        }
    }

    private void login() {
        if (loggedIn != null) {
            System.out.println(LOGGED_IN);
            return;
        }

        System.out.println(LOG_IN);
        System.out.println(VALUE + "email: ");
        String email = scanner.nextLine();

        if (getUserByEmail(email) != null) {
            System.out.println(VALUE + "password: ");
            String password = scanner.nextLine();

            if (getUserByEmail(email).getPassword().equals(password)) {
                loggedIn = getUserByEmail(email);
                System.out.println(LOGG_IN);
            }
            else
                System.out.println(WRONG_PASSWORD);
        }
        else {
            System.out.println(WRONG_EMAIL);
        }

    }

    private void logout() {
        loggedIn = null;
        System.out.println(LOG_OUT);
    }

    @Override
    public void register() {
        System.out.println(ADD_USER);
        String name, email, phoneNumber, password;

        System.out.println(VALUE + "name: ");
        name = scanner.nextLine();

        while (true) {
            System.out.println(VALUE + "email: ");
            email = scanner.nextLine();

            try {
                if (validateEmail(email)) {
                    if (existingEmail(email)) {
                        break;
                    } else {
                        System.out.println(EXISTING_EMAIL);
                    }
                }
            } catch (EmailException e) {
                System.out.println(e.getMessage() + " " + TRY_AGAIN);
            }
        }

        System.out.println(VALUE + "password: ");
        while (true) {
            password = scanner.nextLine();

            try {
                if (validatePassword(password)) {
                    break;
                }
            } catch (PasswordException e) {
                System.out.println(e.getMessage() + " " + TRY_AGAIN);
            }
        }

        System.out.println(VALUE + "phone number: ");
        while (true) {
            phoneNumber = scanner.nextLine();

            try {
                if (validatePhoneNumber(phoneNumber)) {
                    break;
                }
            } catch (PhoneNumberException e) {
                System.out.println(e.getMessage() + " " + TRY_AGAIN);
            }
        }

        Address address = Address.addAddress();

        User user = new User(name, phoneNumber, address, email, password);
        users.add(user);
//        UserCSV.getInstance().writeUserToCSV(user);
        DatabaseManager.getInstance().insertUser(user);
        System.out.println(REGISTERED);
    }

    // Function to place an order
    private void placeOrder() {
        // Check if the user is logged in
        if (loggedIn == null) {
            System.out.println(NO_LOGIN);
            return;
        }

        if (loggedIn.getCart().size() == 0) {
            System.out.println(EMPTY_CART);
            return;
        }

        Map<Product, Integer> products = new HashMap<>();
        int choice;

        // Add the products from the cart
        while (true) {
            loggedIn.printCart();
            System.out.println(SELECT_PRODUCT);

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                choice = -1;
            }

            if (choice == 0)
                break;

            if (choice-1 > loggedIn.getCart().size())
                System.out.println(INVALID_INT);
            else {
                Product product = loggedIn.getCart().remove(choice-1);
                System.out.println(SELECT_QUANTITY);

                // Add quantity of set products
                int quantity;
                while(true) {
                    try {
                        System.out.println(VALUE + "quantity: ");
                        quantity = scanner.nextInt();

                        if (quantity > 0)
                            break;
                        else
                            System.out.println(INVALID_INT);
                    }
                    catch (InputMismatchException e) {
                        System.out.println(WRONG_INPUT + " " + TRY_AGAIN);
                        scanner.nextLine();
                    }
                }

                products.put(product, quantity);
            }
        }

        // Select payment method
        PayMethod pay;
        System.out.println(INPUT_PAYMENT_METHOD);
        while (true) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                choice = -1;
            }

            if (choice == 1) {
                pay = PayMethod.Cash;
                break;
            }
            else if (choice == 2) {
                pay = PayMethod.Card;
                break;
            }
            else
                System.out.println(INVALID_INT);
        }

        Order order = new Order(products, pay);
//        OrderCSV.getInstance().writeOrderToCSV(order);
        DatabaseManager.getInstance().insertOrder(order);
        loggedIn.addOrder(order);
    }

    // Function to browse the stores
    private void browseStores() {
        // Check if the user is logged in
        if (loggedIn == null) {
            System.out.println(NO_LOGIN);
            return;
        }

        while (true) {
            stores.printStores();
            System.out.println(BROWSE_STORES);
            Store store = null;

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

            try {
                store = stores.getStoreFromIndex(choice-1);
            }
            catch (StoreException e) {
                System.out.println(e.getMessage());
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println(INVALID_INT);
            }

            if (store != null) {
                System.out.println("-- " + store.getStoreName() + " --");
                store.printProducts();
                while (true) {
                    System.out.println(BROWSE_PRODUCTS);

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
                        break;

                    try {
                        Product product = store.getProductFromIndex(choice-1);
                        loggedIn.addToCart(product);
                    }
                    catch (ProductException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    // Function to update information about user
    private void updateInformation() {
        // Check if the user is logged in
        if (loggedIn == null) {
            System.out.println(NO_LOGIN);
            return;
        }

        // Select the information they want to change
        int choice, i;
        System.out.println(USER_INFORMATION_CHANGE + "\n" + EXIT);
        do {
            System.out.println(CHOICE);
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                choice = -1;
            }

            if (0 > choice || choice > 4)
                System.out.println(INVALID_INT);
        } while (0 > choice || choice > 3);

        if (!validateChoice())
            return ;

        // Update set information
        switch (choice) {
            case 1 -> {
                System.out.println(VALUE + "new name: ");
                String name = scanner.nextLine();
                loggedIn.setName(name);
                DatabaseManager.getInstance().updateUserName(loggedIn.getEmail(), name);
            }
            case 2 -> {
                System.out.println(VALUE + "new phone number: ");
                for (i = 0; i < 3; ++i) {
                    try {
                        String phoneNumber = scanner.nextLine();
                        if (validatePhoneNumber(phoneNumber)) {
                            loggedIn.setPhoneNumber(phoneNumber);
                            DatabaseManager.getInstance().updateUserPhone(loggedIn.getEmail(), phoneNumber);
                            break;
                        }
                    } catch (PhoneNumberException e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (i == 3) {
                    System.out.println(FORCED_EXIT);
                }
            }
            case 3 -> {
                System.out.println(VALUE + "new password: ");
                for (i = 0; i < 3; ++i) {
                    try {
                        String password = scanner.nextLine();
                        if (validatePassword(password)) {
                            loggedIn.setPassword(password);
                            DatabaseManager.getInstance().updateUserPassword(loggedIn.getEmail(), password);
                            break;
                        }
                    } catch (PasswordException e) {
                        System.out.println(e.getMessage());
                    }
                }
                if (i == 3) {
                    System.out.println(FORCED_EXIT);
                }
            }
        }
    }

    // Function that requires password for confirmation
    private boolean validateChoice() {
        String password = "0";

        // Give user 3 attempts
        for (int i = 0; i < 3; ++i) {
            System.out.println(PASSWORD_ENTER);
            password = scanner.nextLine();

            if (password.equals("0")) {
                System.out.println(CHOICE_EXIT);
            }
            if (!loggedIn.getPassword().equals(password) && !password.equals("0"))
                System.out.println(INCORRECT_PASSWORD);
            else
                break;
        }

        if (!loggedIn.getPassword().equals(password)) {
            System.out.println(FORCED_EXIT);
            return false;
        }
        else
            return true;
    }

    // Check if user exists in the system
    private boolean existingEmail(String email) {
        for (User user: users)
            if (user.getEmail().equals(email))
                return false;

        return true;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void printUsers() {
        for (User user: users)
            System.out.println(user);
    }
}