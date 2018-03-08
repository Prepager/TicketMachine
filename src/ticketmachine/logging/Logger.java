package ticketmachine.logging;

import java.util.ArrayList;

public class Logger {
    /**
     * The list of saved transaction.
     *
     * @var ArrayList
     */
    final private ArrayList<Transaction> list = new ArrayList<>();
    
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
