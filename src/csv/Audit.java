package csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class Audit {
    private static Audit instance;
    private Audit() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/csv/files/audit.csv"))) {
            writer.write("");
        }
        catch (IOException e) {
            System.out.println("Error writing audit: " + e.getMessage());
        }
    }

    public static synchronized Audit getInstance() {
        if (instance == null)
            instance = new Audit();

        return instance;
    }

    // Write audit to CSV
    public void writeAudit(String action) {
        LocalDateTime now = LocalDateTime.now();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/csv/files/audit.csv", true))) {
            String line = action + ", " + now;
            writer.write(line);
            writer.newLine();
        }
        catch (IOException e) {
            System.out.println("Error writing audit: " + e.getMessage());
        }
    }
}
