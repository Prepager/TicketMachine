package ticketmachine.tickets;

public class Ticket {
    /**
     * The ticket id.
     *
     * @var int
     */
    private final int id;

    /**
     * The price per ticket.
     *
     * @var double
     */
    private final double price;
    
    /**
     * The name of the ticket.
     *
     * @var String
     */
    private final String name;
    
    /**
     * Set the id, price and name on creation.
     *
     * @param price
     * @param name
     */
    public Ticket(int id, double price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    /**
     * Return the id of the ticket.
     *
     * @return int
     */
    public int getID() {
        return this.id;
    }
    
    /**
     * Return the price of the ticket.
     *
     * @return double
     */
    public double getPrice() {
        return this.price;
    }
    
    /**
     * Return the name of the ticket.
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }
}
