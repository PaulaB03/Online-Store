package csv;


import model.Address;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserCSV {
    private static UserCSV instance;
    private static final String CSV_FILE_PATH = "src/csv/file/users.csv";

    private UserCSV() {}

    public static synchronized UserCSV getInstance() {
        if (instance == null)
            instance = new UserCSV();

        return instance;
    }

    // Read users from CSV
    public List<User> readUsersFromCSV() {
        Audit.getInstance().writeAudit("readUsersFromCSV");
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/csv/files/users.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(", ");

                if (columns.length < 10) {
                    System.out.println("Invalid entry: " + line);
                    continue;  // Skip this line and move to the next one
                }

                String name = columns[0];
                String phone = columns[1];
                String country = columns[2];
                String city = columns[3];
                String street = columns[4];
                String number = columns[5];
                String entrance = columns[6].equals("null") ? null : columns[6];
                String floor = columns[7].equals("null") ? null : columns[7];
                String email = columns[8];
                String password = columns[9];

                users.add(new User(name, phone, new Address(country, city, street, number, entrance, floor), email, password));
            }
        }
        catch (IOException e) {
            System.out.println("Error reading addresses from CSV: "  + e.getMessage());
        }

        return users;
    }

    // Write user in CSV
    public void writeUserToCSV(User user) {
        Audit.getInstance().writeAudit("writeUserFromCSV");
        try (BufferedWriter write = new BufferedWriter(new FileWriter("src/csv/files/users.csv", true))) {
            String line = user.getName() + ", " + user.getPhoneNumber() + ", " + user.getAddress().getCounty() + ", " + user.getAddress().getCity() + ", " + user.getAddress().getStreet() + ", " +
                          user.getAddress().getNumber() + ", " + user.getAddress().getEntrance() + ", " + user.getAddress().getFloor() + ", " + user.getEmail() + ", " + user.getPassword();
            write.write(line);
            write.newLine();
        }
        catch (IOException e) {
            System.out.println("Error writing user to CSV: " + e.getMessage());
        }
    }
}
