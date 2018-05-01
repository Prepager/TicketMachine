package ticketmachine;

import org.junit.Test;

public class PrinterTest {
    /**
     * Test of printTicket method, of class Printer.
     */
    @Test
    public void testPrintTicket() {
        System.out.println("printTicket");

        Client client = new Client();
        Ticket ticket = new Ticket("child", 10, "child");

        Printer instance = new Printer();
        instance.printTicket(ticket, "From St.", "To St.", 10);
    }
}
