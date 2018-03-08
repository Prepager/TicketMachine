package ticketmachine;

import java.util.ArrayList;

import ticketmachine.logging.Logger;

public class TicketMachine {
    /**
     * Store the client.
     *
     * @var Client
     */
    private final Client client = new Client();

    /**
     * Store the printer.
     *
     * @var Printer
     */
    private final Printer printer = new Printer();
    
    /**
     * Store the transaction logger.
     *
     * @var Logger
     */
    private final Logger transactions = new Logger();

    /**
     * The machine admin password.
     *
     * @var String
     */
    private final String password = "1234";

    /**
     * The list of ticket types.
     *
     * @var ArrayList
     */
    private ArrayList<Ticket> tickets = new ArrayList<>();
    
    /**
     * Return the connected client.
     *
     * @return Client
     */
    public Client getClient() {
        return this.client;
    }
    
    /**
     * Return the connected logger.
     *
     * @return Logger
     */
    public Logger getLogger() {
        return this.transactions;
    }
    
    /**
     * Add a ticket to the list.
     *
     * @param ticket
     */
    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }
    
    /**
     * Get a ticket with the passed id.
     *
     * @param id
     * @return Ticket
     */
    public Ticket getTicket(int id) {
        // Create ticket holder variable.
        Ticket found = null;
        
        // Loop through tickets and find matching id.
        for(Ticket ticket: this.getTickets()) {
            if (id == ticket.getID()) found = ticket;
        }
        
        // Return found ticket or null.
        return found;
    }

    /**
     * Return the list of tickets.
     *
     * @return ArrayList
     */
    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }
    
    /**
     * Add amount to the clients balance.
     *
     * @param amount
     */
    public void inputMoney(double amount) {
        // Check if amount is below or equal 0.
        if (amount <= 0) {
            // Return out of method.
            this.transactions.addEntry("Input amount must be above 0.", null);
            return;
        }
        
        // Add the amount to the client balance.
        this.transactions.addEntry("Added " + amount + " to balance.", null);
        this.client.addBalance(amount);
    }
    
    /**
     * Return the clients remaining balance.
     */
    public void returnChange() {
        // Save the clients balance.
        double balance = this.client.getBalance();
        
        // Reset the clients balance.
        this.client.reset();
        
        // Return the money to the client.
        this.transactions.addEntry("Returned " + balance + " to client.", null);
    }
    
    /**
     * Purchase a ticket with the passed id.
     *
     * @param id
     */
    public void purchaseTicket(int id) {
        // Find the ticket matching the id.
        Ticket ticket = this.getTicket(id);
        
        // Return if the ticket was not found.
        if (ticket == null) {
            return;
        }
        
        // Return if the client cannot afford the price.
        if (! this.client.canAfford(ticket.getPrice())) {
            return;
        }
        
        // Remove the price from the clients balance.
        this.client.removeBalance(ticket.getPrice());
        
        // Increment the ticket sales count.
        ticket.wasSold();
        this.transactions.addEntry("Ticket (" + ticket.getName() + ") was sold.", null);
        
        // Print the ticket.
        printer.printTicket(this.client, ticket);
    }
    
    /**
     * Return the total amount sold for.
     *
     * @return double
     */
    public double getTotal() {
        // Loop through tickets and sum total.
        double total = 0;
        for(Ticket ticket: this.getTickets()) {
            total += ticket.getTotal();
        }
        
        // Return the found total.
        return total;
    }
    
    /**
     * Attempt to login into admin mode.
     *
     * @param attempt
     * @return boolean
     */
    public boolean login(String attempt) {
        return this.client.login(this.password, attempt);
    }
    
    /**
     * Logout of admin mode.
     */
    public void logout() {
        this.client.logout();
    }
    
    /**
     * Reset the machine, client and transaction log.
     */
    public void reset() {
        // Reset client details.
        this.client.reset();
        
        // Empty ticket types.
        this.tickets.clear();
        
        // Clear transaction log.
        // @wip
    }
}
