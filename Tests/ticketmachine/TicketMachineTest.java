package ticketmachine;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketmachine.logging.Logger;

/**
 *
 * @author andreas
 */
public class TicketMachineTest {
    /**
     * Test of getClient method, of class TicketMachine.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");

        TicketMachine instance = new TicketMachine();

        assertEquals(instance.getClient().getBalance(), 0, 0.0);
    }

    /**
     * Test of getLogger method, of class TicketMachine.
     */
    @Test
    public void testGetLogger() {
        System.out.println("getLogger");
        
        TicketMachine instance = new TicketMachine();
        
        assertTrue(instance.getLogger().getList().isEmpty());
    }

    /**
     * Test of addTicket method, of class TicketMachine.
     */
    @Test
    public void testAddTicket() {
        System.out.println("addTicket");
        
        Ticket ticket = new Ticket(1, 10, "test");
        TicketMachine instance = new TicketMachine();
        
        instance.addTicket(ticket);
        assertEquals(instance.getTicket(1), ticket);
    }

    /**
     * Test of getTicket method, of class TicketMachine.
     */
    @Test
    public void testGetTicket() {
        System.out.println("getTicket");
        
        Ticket ticket = new Ticket(1, 10, "test");
        TicketMachine instance = new TicketMachine();
        
        instance.addTicket(ticket);
        assertEquals(instance.getTicket(1), ticket);
    }

    /**
     * Test of getTickets method, of class TicketMachine.
     */
    @Test
    public void testGetTickets() {
        System.out.println("getTickets");
        
        Ticket ticket1 = new Ticket(1, 10, "test");
        Ticket ticket2 = new Ticket(2, 20, "test2");
        TicketMachine instance = new TicketMachine();
        instance.addTicket(ticket1);
        instance.addTicket(ticket2);
        
        ArrayList<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        
        assertEquals(instance.getTickets(), tickets);
        
    }

    /**
     * Test of inputMoney method, of class TicketMachine.
     */
    @Test
    public void testInputMoney() {
        System.out.println("inputMoney");

        TicketMachine instance = new TicketMachine();
        instance.inputMoney(10);
        
        assertEquals(instance.getClient().getBalance(), 10, 0.0);
    }

    /**
     * Test of returnChange method, of class TicketMachine.
     */
    @Test
    public void testReturnChange() {
        System.out.println("returnChange");
        
        TicketMachine instance = new TicketMachine();
        instance.getClient().setBalance(10);

        instance.returnChange();
        assertEquals(instance.getClient().getBalance(), 0, 0.0);
    }

    /**
     * Test of purchaseTicket method, of class TicketMachine.
     */
    @Test
    public void testPurchaseTicket() {
        System.out.println("purchaseTicket");

        TicketMachine instance = new TicketMachine();
        instance.addTicket(new Ticket(1, 50, "test"));
        
        instance.getClient().addBalance(25);
        assertFalse(instance.purchaseTicket(1));
        instance.getClient().addBalance(25);
        assertTrue(instance.purchaseTicket(1));
        assertEquals(instance.getClient().getBalance(), 0, 0.0);
    }

    /**
     * Test of getTotal method, of class TicketMachine.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        
        TicketMachine instance = new TicketMachine();
        instance.addTicket(new Ticket(1, 10, "test1"));
        instance.addTicket(new Ticket(2, 20, "test2"));

        instance.getTicket(1).setSold(2);
        instance.getTicket(2).setSold(1);
        
        assertEquals(instance.getTotal(), 40, 0.0);
    }

    /**
     * Test of login method, of class TicketMachine.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        
        TicketMachine instance = new TicketMachine();
        
        assertFalse(instance.login("wrong-password"));
        assertFalse(instance.getClient().isAdmin());
        assertTrue(instance.login("1234"));
        assertTrue(instance.getClient().isAdmin());
    }

    /**
     * Test of logout method, of class TicketMachine.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        
        TicketMachine instance = new TicketMachine();
        instance.getClient().setAdmin(true);
        
        instance.logout();
        assertFalse(instance.getClient().isAdmin());
    }

    /**
     * Test of reset method, of class TicketMachine.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        
        TicketMachine instance = new TicketMachine();
        instance.getClient().setAdmin(true);
        instance.addTicket(new Ticket(1, 10, "test"));
        
        instance.reset();
        assertFalse(instance.getClient().isAdmin());
        assertTrue(instance.getTickets().isEmpty());
    }
}
