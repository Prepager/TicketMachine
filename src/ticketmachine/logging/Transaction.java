package ticketmachine.logging;

import java.time.LocalDateTime;

public class Transaction {
    /**
     * The date for the transaction creation.
     *
     * @var LocalDateTime
     */
    protected LocalDateTime date;

    /**
     * The message of the transaction performed.
     *
     * @var String
     */
    protected String message;
    
    /**
     * The parameter for the transaction.
     *
     * @var double
     */
    protected double parameter;

    /**
     * Set the date, message and parameter on creation.
     *
     * @param message
     * @param param
     */
    Transaction(String message, double param) {
        // Set date of action to current time.
        this.date = LocalDateTime.now();

        // Save message and parameters on action.
        this.message = message;
        this.parameter = param;
    }
    
    /**
     * Return the data as a formatted string.
     *
     * @return String
     */
    public String formatted() {
        // Add together the date and message.
        return this.date + ": " + this.message;
    }
}
