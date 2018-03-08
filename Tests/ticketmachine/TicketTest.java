package ticketmachine;

import org.junit.Test;
import static org.junit.Assert.*;

public class TicketTest {
    /**
     * Test of getID method, of class Ticket.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");

        Ticket instance = new Ticket(10, 50, "test");

        assertEquals(instance.getID(), 10);
    }

    /**
     * Test of getPrice method, of class Ticket.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");

        Ticket instance = new Ticket(10, 50, "test");

        assertEquals(instance.getPrice(), 50, 0.0);
    }

    /**
     * Test of getName method, of class Ticket.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");

        Ticket instance = new Ticket(10, 50, "test");

        assertEquals(instance.getName(), "test");
    }

    /**
     * Test of getSold method, of class Ticket.
     */
    @Test
    public void testGetSold() {
        System.out.println("getSold");

        Ticket instance = new Ticket(10, 50, "test");

        assertEquals(instance.getSold(), 0);
        instance.setSold(5);
        assertEquals(instance.getSold(), 5);
    }

    /**
     * Test of setSold method, of class Ticket.
     */
    @Test
    public void testSetSold() {
        System.out.println("setSold");

        Ticket instance = new Ticket(10, 50, "test");

        assertEquals(instance.getSold(), 0);
        instance.setSold(5);
        assertEquals(instance.getSold(), 5);
    }

    /**
     * Test of wasSold method, of class Ticket.
     */
    @Test
    public void testWasSold() {
        System.out.println("wasSold");

        Ticket instance = new Ticket(10, 50, "test");

        assertEquals(instance.getSold(), 0);
        instance.wasSold();
        assertEquals(instance.getSold(), 1);
    }

    /**
     * Test of getTotal method, of class Ticket.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");

        Ticket instance = new Ticket(10, 50, "test");

        assertEquals(instance.getTotal(), 0, 0.0);
        instance.setSold(2);
        assertEquals(instance.getTotal(), 100, 0.0);
    }

    /**
     * Test of toString method, of class Ticket.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        Ticket instance = new Ticket(10, 50, "test");
        instance.toString();
    }
}
