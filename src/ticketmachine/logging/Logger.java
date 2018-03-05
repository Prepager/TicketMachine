package ticketmachine.logging;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Logger {
    /**
     * The list of saved actions.
     *
     * @var ArrayList
     */
    final private ArrayList<Action> list = new ArrayList<Action>();
    
    /**
     * Add a new entry to the list
     *
     * @param message
     */
    public void addEntry(String message) {
        // Create a new action with date and message.
        Action action = new Action();
        action.date = LocalDateTime.now();
        action.message = message;
        
        // Add the new action to the list.
        this.list.add(action);
        
        // Output the action to the console.
        System.out.println(action.formatted());
    }
    
    /**
     * Return the current action list.
     *
     * @return ArrayList
     */
    public ArrayList<Action> getList() {
        return this.list;
    }
}
