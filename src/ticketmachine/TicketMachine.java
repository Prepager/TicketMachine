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
    private final Logger log = new Logger();

    /**
     * The name of the machine location.
     *
     * @var String
     */
    private final String name;

    /**
     * The machine administrator password.
     *
     * @var String
     */
    private final String password = "1234";

    /**
     * The list of ticket types.
     *
     * @var ArrayList
     */
    private final ArrayList<Ticket> tickets = new ArrayList<>();

    /**
     * The list of stations.
     *
     * @var ArrayList
     */
    private final ArrayList<Station> stations = new ArrayList<>();

    /**
     * The current machine latitude.
     *
     * @var double
     */
    private double latitude = 0;

    /**
     * The current machine longitude.
     *
     * @var double
     */
    private double longitude = 0;

    /**
     * Set the machine name, latitude and longitude.
     *
     * @param name
     * @param latitude
     * @param longitude
     */
    public TicketMachine(String name, double latitude, double longitude) {
        this.name = name;

        this.latitude = latitude;
        this.longitude = longitude;
    }

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
        return this.log;
    }

    /**
     * Return the name of the machine.
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the latitude of the machine.
     *
     * @return double
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Return the longitude of the machine.
     *
     * @return double
     */
    public double getLongitude() {
        return this.longitude;
    }

    /**
     * Add a station to the list.
     *
     * @param station
     */
    public void addStation(Station station) {
        this.stations.add(station);
    }

    /**
     * Return the list of stations.
     *
     * @return ArrayList
     */
    public ArrayList<Station> getStations() {
        return this.stations;
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
    public Ticket getTicket(String id) {
        // Create ticket holder variable.
        Ticket found = null;

        // Loop through tickets and find matching id.
        for(Ticket ticket: this.getTickets()) {
            if (ticket.getID().equals(id)) found = ticket;
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
            this.log.addEntry("Input amount must be above 0.", amount);
            return;
        }

        // Add the amount to the client balance.
        this.log.addEntry("Added " + amount + " to balance.", amount);
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
        this.log.addEntry("Returned " + balance + " to client.", balance);
    }

    /**
     * Purchase a ticket to station with passed segment and distance.
     *
     * @param id
     * @param station
     * @param distance
     * @return boolean
     */
    public boolean purchaseTicket(String id, Station station, int distance) {
        // Find the ticket matching the id.
        Ticket ticket = this.getTicket(id);

        // Return if the ticket was not found.
        if (ticket == null) {
            return false;
        }

        // Return if the client cannot afford the price.
        if (! this.client.canAfford(ticket.getPrice() * distance)) {
            return false;
        }

        // Remove the price from the clients balance.
        this.client.removeBalance(ticket.getPrice() * distance);

        // Increment the ticket sales count and distance.
        ticket.wasSold(distance);
        this.log.addEntry("Ticket (" + ticket.getName() + ") was sold.", ticket.getPrice() * distance);

        // Print the ticket and return.
        printer.printTicket(ticket, this.name, station.getName(), distance);
        return true;
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
     * Attempt to login into administrator mode.
     *
     * @param attempt
     * @return boolean
     */
    public boolean login(String attempt) {
        return this.client.login(this.password, attempt);
    }

    /**
     * Logout of administrator mode.
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
        this.log.reset();
    }
}
