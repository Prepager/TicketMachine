package ticketmachine.logging;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Action {
    /**
     * The date for the action creation.
     *
     * @var LocalDateTime
     */
    protected LocalDateTime date;

    /**
     * The message of the action performed.
     *
     * @var String
     */
    protected String message;
    
    /**
     * The parameter for the action.
     *
     * @var String
     */
    protected String parameter;

    /**
     * x
     */
    Action(String message, String param) {
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
