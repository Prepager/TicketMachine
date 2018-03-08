package ticketmachine;

import java.util.ArrayList;

import ticketmachine.logging.Logger;
import ticketmachine.logging.Action;
import ticketmachine.tickets.Ticket;

public class TicketMachine {
    /**
     * The total customer balance.
     *
     * @var double
     */
    private double balance = 0;
    
    /**
     * The current admin mode status.
     *
     * @var boolean
     */
    private boolean adminMode = false;

    /**
     * The admin password.
     *
     * @var String
     */
    private final String adminPassword = "1234";

    /**
     * The transaction logging object.
     *
     * @var Logger
     */
    private final Logger transactions = new Logger();
    
    /**
     * The list of ticket types.
     *
     * @var ArrayList
     */
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();

    /**
    * Add money into customers balance.
    *
    * @param amount The amount to be added.
    * @return double The customers total balance.
    */
    public double addMoney(double amount) {
        // Check if amount is negative
        if (amount < 0) {
            System.out.println("Amount must be a positive number.");
            return this.balance;
        }
        
        // Add amount to balance.
        this.transactions.addEntry("Added " + amount + " DKK.", String.valueOf(amount));
        this.balance += amount;
        
        // Return the total balance.
        return this.balance;
    }

    /**
    * Return the customers balance.
    *
    * @return double The customers total balance.
    */
    public double getBalance() {
        return this.balance;
    }

    /**
    * Print ticket if sufficient balance.
    *
    * @param ticket
    */
    public void printTicket(int ticket) {
        // Find the selected ticket.
        Ticket selectedTicket = this.getTicket(ticket);
        
        // Return out if not found.
        if (selectedTicket == null) {
            System.out.println("Unable to find ticket with id: " + ticket);
            return;
        }
        
        // Check for sufficient balance.
        if (this.balance < selectedTicket.getPrice()) {
            System.out.println("Insufficient balance. Input more money.");
            return;
        }
        
        // Log printing action.
        this.transactions.addEntry("Printed ticket.", null);

        // Output ticket to console.
        System.out.println("##########B##T#########");
        System.out.println("# BlueJ Trafikselskab #");
        System.out.println("#                     #");
        System.out.println("#        Ticket       #");
        System.out.println("#        " + selectedTicket.getPrice() + " kr.       #");
        System.out.println("#                     #");
        System.out.println("##########B##T#########");
        System.out.println("# You have " + (this.balance - selectedTicket.getPrice()) + " DKK left       #");
        System.out.println("##########B##T#########");
        System.out.println();

        // Increase sold count.
        System.out.println("Increased ticket sale count.");
        selectedTicket.wasSold();

        // Decrease balance by price.
        System.out.println("Removed ticket price from balance.");
        this.balance -= selectedTicket.getPrice();
    }

    /**
    * Return the change amount to the customer.
    *
    * @return double The amount to be paid back.
    */
    public double returnChange() {
        // Save balance and reset.
        double amount = this.balance;
        this.balance = 0;

        // Output notification.
        this.transactions.addEntry("Returned " + amount + " DKK.", String.valueOf(amount));

        // Return amount.
        return amount;
    }

    /**
    * Toggle admin mode if correct password.
    *
    * @param password The password to be checked.
    * @return boolean Whether or not attempt was successful.
    */
    public boolean adminLogin(String password) {
        // Check password.
        if (this.adminPassword.equals(password)) {
            // Enable admin mode.
            this.adminMode = true;

            // Output login notification.
            System.out.println("Admin mode activated!");
            System.out.println("You may now enter ticket price.");

            // Return out of function.
            return true;
        }

        // Output logout notification.
        System.out.println("Admin mode deactivated!");

        // Disable admin mode.
        this.adminMode = false;
        return false;
    }

    /**
    * Return the total amount of money earned.
    *
    * @return double The total earnings or 0.
    */
    public double getTotal() {
        // Check admin mode and return earnings.
        if (this.adminMode) {
            double total = 0;

            for(Ticket item: this.tickets) {
                total += item.getSold() * item.getPrice();
            }

            return total;
        }

        // Output rejected message and return 0.
        System.out.println("Rejected - You must login.");
        return 0;
    }

    /**
    * Return the amount of sold tickets.
    *
    * @param ticket
    * @return int The total amount of tickets sold or 0.
    */
    public int getSoldTickets(int ticket) {
        // Check admin mode and return ticket sales count.
        if (this.adminMode) {
            // Find the selected ticket.
            Ticket selectedTicket = this.getTicket(ticket);
            if (selectedTicket == null) {
                return 0;
            }
            
            // Get the sold count for the ticket.
            return selectedTicket.getSold();
        }

        // Output rejected message and return 0.
        System.out.println("Rejected - You must login.");
        return 0;
    }

    /**
    * Reset the admin mode login.
    */
    public void reset() {
        // Check admin mode.
        if (this.adminMode) {
            // Reset sold ticket count.
            System.out.println("Reset sold ticket count.");
            //this.soldTickets = 0;
            return;
        }

        // Output rejected message.
        System.out.println("Rejected - You must login.");
    }

    /**
    * Set the amount of tickets sold.
    *
    * @param amount The amount of tickets sold.
    */
    /*public void setSoldTickets(int amount) {
        // Check admin mode.
        if (this.adminMode) {
            // Set sold ticket count.
            System.out.println("Set sold ticket count to " + amount);
            return;
        }

        // Output rejected message.
        System.out.println("Rejected - You must login.");
    }*/

    /**
    * Return weather in admin mode or not. 
    *
    * @return boolean Whether or not in admin mode.
    */
    public boolean isAdmin() {
        return this.adminMode;
    }
    
    /**
     * Output transactions to console.
     */
    public void printLog() {
        // Check admin mode.
        if (this.adminMode) {
            // Output transactions to console.
            for (Action transaction: this.transactions.getList()) {
                System.out.println(transaction.formatted());
            }

            // Return out of function.
            return;
        }

        // Output rejected message.
        System.out.println("Rejected - You must login.");
    }

    /**
     * Add a new ticket type to the machine.
     *
     * @param ticket 
     */
    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
    
    /**
     * Return the list of possible tickets.
     *
     * @return ArrayList
     */
    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }

    /**
     * List out all the possible tickets.
     */
    public void listTickets() {
        // Loop through the tickets.
        for(Ticket ticket: this.tickets) {
            System.out.println(ticket.getID() + ": " + ticket.getName() + " " + ticket.getPrice() + " DKK.");
        }
    }
    
    /**
     * x
     *
     * @param id
     * @return Ticket
     */
    public Ticket getTicket(int id) {
        // Find the selected ticket.
        for(Ticket item: this.tickets) {
            if (item.getID() == id) {
                return item;
            }
        }
        
        //
        return null;
    }
}
