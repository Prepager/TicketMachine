package ticketmachine.logging;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Logger {
    /**
     * The log file location.
     */
    private final String file = "log.txt";

    /**
     * The list of saved transaction.
     *
     * @var ArrayList
     */
    private final ArrayList<Transaction> list = new ArrayList<>();
    
    /**
     * Add a new entry to the list
     *
     * @param message
     * @param param
     */
    public void addEntry(String message, double param) {
        // Create a new action with date and message.
        Transaction transaction = new Transaction(message, param);
        
        // Add the new transaction to the list.
        this.list.add(transaction);
        
        // Output the transaction to the console.
        System.out.println(transaction.formatted());
        
        // Write formatted transaction to file.
        try {
            // Add linebreak to end of line.
            String msg = transaction.formatted() + "\n";

            // Find file path and opening method.
            Path path = Paths.get(this.file);
            OpenOption method = (Files.exists(path) 
                ? StandardOpenOption.APPEND
                : StandardOpenOption.CREATE
            );

            // Write bytes to file.
            Files.write(path, msg.getBytes(), method);
        } catch (IOException e) {
            System.out.println("Couldn't save log to file. ERROR: " + e.getMessage());
        }
    }
    
    /**
     * Return the current transaction list.
     *
     * @return ArrayList
     */
    public ArrayList<Transaction> getList() {
        return this.list;
    }
    
    /**
     * Clear the list of transactions.
     */
    public void reset() {
        this.list.clear();
    }
}
