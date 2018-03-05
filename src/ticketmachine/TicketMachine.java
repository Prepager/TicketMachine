package ticketmachine;

import ticketmachine.logging.Logger;
import ticketmachine.logging.Action;

public class TicketMachine {
    /**
     * The per ticket price.
     *
     * @var double
     */
    private double ticketPrice = 10;
    
    /**
     * The total customer balance.
     *
     * @var double
     */
    private double balance = 0;
    
    /**
     * The amount of tickets sold.
     *
     * @var int
     */
    private int soldTickets = 0;
    
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
    * Return the ticket price.
    *
    * @return double The ticket price.
    */
    public double getTicketPrice() {
        return this.ticketPrice;
    }

    /**
    * Add money into customers balance.
    *
    * @param amount The amount to be added.
    * @return double The customers total balance.
    */
    public double addMoney(int amount) {
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
    */
    public void printTicket() {
        // Check for sufficient balance.
        if (this.balance < this.ticketPrice) {
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
        System.out.println("#        " + this.ticketPrice + " kr.       #");
        System.out.println("#                     #");
        System.out.println("##########B##T#########");
        System.out.println("# You have " + (this.balance - this.ticketPrice) + " DKK left       #");
        System.out.println("##########B##T#########");
        System.out.println();

        // Increase sold count.
        System.out.println("Increased ticket sale count.");
        this.soldTickets++;

        // Decrease balance by price.
        System.out.println("Removed ticket price from balance.");
        this.balance -= this.ticketPrice;
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
            return this.ticketPrice * this.soldTickets;
        }

        // Output rejected message and return 0.
        System.out.println("Rejected - You must login.");
        return 0;
    }

    /**
    * Return the amount of sold tickets.
    *
    * @return int The total amount of tickets sold or 0.
    */
    public int getSoldTickets() {
        // Check admin mode and return ticket sales count.
        if (this.adminMode) {
            return this.soldTickets;
        }

        // Output rejected message and return 0.
        System.out.println("Rejected - You must login.");
        return 0;
    }

    /**
    * Set the price of the ticket.
    *
    * @param price The price for a single ticket.
    */
    public void setTicketPrice(int price) {
        // Check admin mode and set ticket price.
        if (this.adminMode) {
            this.ticketPrice = price;
            return;
        }

        // Output rejected message.
        System.out.println("Rejected - You must login.");
    }

    /**
    * Reset the admin mode login.
    */
    public void reset() {
        // Check admin mode.
        if (this.adminMode) {
            // Reset sold ticket count.
            System.out.println("Reset sold ticket count.");
            this.soldTickets = 0;
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
    public void setSoldTickets(int amount) {
        // Check admin mode.
        if (this.adminMode) {
            // Set sold ticket count.
            System.out.println("Set sold ticket count to " + amount);
            this.soldTickets = amount;
            return;
        }

        // Output rejected message.
        System.out.println("Rejected - You must login.");
    }

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
}
