package ticketmachine;

public class Printer {
    /**
     * Print the ticket to the client.
     *
     * @param ticket
     * @param from
     * @param to
     * @param distance
     */
    public void printTicket(Ticket ticket, String from, String to, int distance) {
        // Store ticket data variables.
        String name = ticket.getName();

        // Output ticket to console.
        System.out.println("##########B##T#########");
        System.out.println(" BlueJ Transportation");
        System.out.println("");
        System.out.println(" " + name + " Ticket");
        System.out.println("");
        System.out.println(" From: " + from );
        System.out.println(" To: " + to );
        System.out.println("##########B##T#########");
        System.out.println();
    }
}
