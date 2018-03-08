package ticketmachine;

import org.junit.Test;
import static org.junit.Assert.*;

public class PrinterTest {
    /**
     * Test of printTicket method, of class Printer.
     */
    @Test
    public void testPrintTicket() {
        System.out.println("printTicket");

        Client client = new Client();
        Ticket ticket = new Ticket(1, 10, "test");
        
        Printer instance = new Printer();
        instance.printTicket(client, ticket);
    }
}
