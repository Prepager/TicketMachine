package ticketmachine;

public class Ticket {
    /**
     * The ticket id.
     *
     * @var String
     */
    private final String id;

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
     * The amount sold of this ticket.
     *
     * @var integer
     */
    private int sold = 0;

    /**
     * The amount of KMs sold of this ticket.
     *
     * @var double
     */
    private double distance = 0;

    /**
     * Set the id, price and name on creation.
     *
     * @param id
     * @param price
     * @param name
     */
    public Ticket(String id, double price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    /**
     * Return the id of the ticket.
     *
     * @return String
     */
    public String getID() {
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

    /**
     * Return the amount of sold tickets.
     *
     * @return integer
     */
    public int getSold() {
        return this.sold;
    }

    /**
     * Set the amount of tickets sold.
     *
     * @param amount
     */
    public void setSold(int amount) {
        this.sold = amount;
    }

    /**
     * Increment the sold count and add distance.
     *
     * @param distance
     */
    public void wasSold(double distance) {
        this.sold++;
        this.distance += distance;
    }

    /**
     * Return the total amount sold.
     *
     * @return double
     */
    public double getTotal() {
        return this.price * this.distance;
    }

    /**
     * Change the toString format for use in drop-down.
     *
     * @return String
     */
    @Override
    public String toString()
    {
        return this.getName() + ", " + this.getPrice() + " DKK";
    }
}
