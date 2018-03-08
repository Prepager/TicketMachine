package ticketmachine;

public class Printer {
    /**
     * Print the ticket to the client.
     *
     * @param client
     * @param ticket
     */
    public void printTicket(Client client, Ticket ticket) {
        // Store ticket data variables.
        double price = ticket.getPrice();
        double balance = client.getBalance();
        
        // Output ticket to console.
        System.out.println("##########B##T#########");
        System.out.println("# BlueJ Trafikselskab #");
        System.out.println("#                     #");
        System.out.println("#        Ticket       #");
        System.out.println("#       " + price + " DKK.     #");
        System.out.println("#                     #");
        System.out.println("##########B##T#########");
        System.out.println("You have " + balance + " DKK left");
        System.out.println("##########B##T#########");
        System.out.println();
    }
}
