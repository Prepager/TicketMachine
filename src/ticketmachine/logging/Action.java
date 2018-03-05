package ticketmachine.logging;

import java.time.LocalDateTime;

public class Action {
    /**
     * The date for the action creation.
     *
     * @var LocalDateTime
     */
    public LocalDateTime date;

    /**
     * The message of the action performed.
     *
     * @var String
     */
    public String message;

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
