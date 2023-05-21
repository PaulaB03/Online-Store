package csv;

import model.Address;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddressCSV {
    private static AddressCSV instance;

    private AddressCSV() {}

    public static synchronized AddressCSV getInstance() {
        if (instance == null)
            instance = new AddressCSV();

        return instance;
    }

    // Read addresses from CSV
    public List<Address> readAddressFromCSV() {
        Audit.getInstance().writeAudit("readAddressFromCSV");
        List<Address> addresses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/csv/files/address.csv"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(", ");
                String country = columns[0];
                String city = columns[1];
                String street = columns[2];
                String number = columns[3];
                if (!columns[4].equals("null")) {
                    String entrance = columns[4];
                    String floor = columns[5];
                    addresses.add(new Address(country, city, street, number, entrance, floor));
                }
                else
                    addresses.add(new Address(country, city, street, number));
            }
        }
        catch (IOException e) {
            System.out.println("Error reading addresses from CSV: "  + e.getMessage());
        }

        return addresses;
    }

    // Write address in CSV
    public void writeAddressToCSV(Address address) {
        Audit.getInstance().writeAudit("writeAddressFromCSV");

        try (BufferedWriter write = new BufferedWriter(new FileWriter("src/csv/files/address.csv", true))) {
            String line = address.getCounty() + ", " + address.getCity() + ", " + address.getStreet() + ", " + address.getNumber() + ", " + address.getEntrance() + ", " + address.getFloor();
            write.write(line);
            write.newLine();
        }
        catch (IOException e) {
            System.out.println("Error writing address to CSV: " + e.getMessage());
        }
    }
}
