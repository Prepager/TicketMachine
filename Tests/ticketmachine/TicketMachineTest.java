package ticketmachine;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TicketMachineTest {
    /**
     * Hold current test instace.
     */
    TicketMachine instance;

    /**
     * Recreate instance on setup and before.
     */
    @Before
    public void setUp() {
        instance = new TicketMachine("DTU Lyngby", 55.785575, 12.521367);
    }

    /**
     * Test of getClient method, of class TicketMachine.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");

        assertEquals(this.instance.getClient().getBalance(), 0, 0.0);
    }

    /**
     * Test of getLogger method, of class TicketMachine.
     */
    @Test
    public void testGetLogger() {
        System.out.println("getLogger");

        assertTrue(this.instance.getLogger().getList().isEmpty());
    }

    /**
     * Test of getName method, of class TicketMachine.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");

        assertEquals(this.instance.getName(), "DTU Lyngby");
    }

    /**
     * Test of getLatitude method, of class TicketMachine.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");

        assertEquals(this.instance.getLatitude(), 55.785575, 0);
    }

    /**
     * Test of getLongitude method, of class TicketMachine.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getName");

        assertEquals(this.instance.getLongitude(), 12.521367, 0);
    }

    /**
     * Test of addStation method, of class TicketMachine.
     */
    @Test
    public void testAddStation() {
        System.out.println("addStation");

        Station station = new Station("København H", 55.672761, 12.564924);

        this.instance.addStation(station);
        assertEquals(this.instance.getStations().get(0), station);
    }

    /**
     * Test of getStations method, of class TicketMachine.
     */
    @Test
    public void testGetStations() {
        System.out.println("getStations");

        Station station1 = new Station("København H", 55.672761, 12.564924);
        Station station2 = new Station("Dybbølsbro St.", 55.665385, 12.559695);
        this.instance.addStation(station1);
        this.instance.addStation(station2);

        ArrayList<Station> stations = new ArrayList<>();
        stations.add(station1);
        stations.add(station2);

        assertEquals(instance.getStations(), stations);
    }

    /**
     * Test of addTicket method, of class TicketMachine.
     */
    @Test
    public void testAddTicket() {
        System.out.println("addTicket");

        Ticket ticket = new Ticket("child", 10, "Child");

        this.instance.addTicket(ticket);
        assertEquals(this.instance.getTicket("child"), ticket);
    }

    /**
     * Test of getTicket method, of class TicketMachine.
     */
    @Test
    public void testGetTicket() {
        System.out.println("getTicket");

        Ticket ticket = new Ticket("child", 10, "Child");

        this.instance.addTicket(ticket);
        assertEquals(this.instance.getTicket("child"), ticket);
    }

    /**
     * Test of getTickets method, of class TicketMachine.
     */
    @Test
    public void testGetTickets() {
        System.out.println("getTickets");

        Ticket ticket1 = new Ticket("child", 10, "Child");
        Ticket ticket2 = new Ticket("adult", 20, "Adult");
        this.instance.addTicket(ticket1);
        this.instance.addTicket(ticket2);

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

        this.instance.inputMoney(10);

        assertEquals(this.instance.getClient().getBalance(), 10, 0.0);
    }

    /**
     * Test of returnChange method, of class TicketMachine.
     */
    @Test
    public void testReturnChange() {
        System.out.println("returnChange");

        this.instance.getClient().setBalance(10);

        this.instance.returnChange();
        assertEquals(this.instance.getClient().getBalance(), 0, 0.0);
    }

    /**
     * Test of purchaseTicket method, of class TicketMachine.
     */
    @Test
    public void testPurchaseTicket() {
        System.out.println("purchaseTicket");

        this.instance.addTicket(new Ticket("child", 50, "Child"));

        Station station = new Station("København H", 55.672761, 12.564924);

        this.instance.getClient().addBalance(50);
        assertFalse(this.instance.purchaseTicket("child", station, 2));
        this.instance.getClient().addBalance(50);
        assertTrue(this.instance.purchaseTicket("child", station, 2));
        assertEquals(this.instance.getClient().getBalance(), 0, 0.0);
    }

    /**
     * Test of getTotal method, of class TicketMachine.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");

        this.instance.addTicket(new Ticket("child", 10, "Child"));
        this.instance.addTicket(new Ticket("adult", 20, "Adult"));

        this.instance.getTicket("child").wasSold(2);
        this.instance.getTicket("adult").wasSold(5);

        assertEquals(this.instance.getTotal(), 120, 0.0);
    }

    /**
     * Test of login method, of class TicketMachine.
     */
    @Test
    public void testLogin() {
        System.out.println("login");

        assertFalse(this.instance.login("wrong-password"));
        assertFalse(this.instance.getClient().isAdmin());
        assertTrue(this.instance.login("1234"));
        assertTrue(this.instance.getClient().isAdmin());
    }

    /**
     * Test of logout method, of class TicketMachine.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");

        this.instance.getClient().setAdmin(true);

        this.instance.logout();
        assertFalse(this.instance.getClient().isAdmin());
    }

    /**
     * Test of reset method, of class TicketMachine.
     */
    @Test
    public void testReset() {
        System.out.println("reset");

        this.instance.getClient().setAdmin(true);
        this.instance.addTicket(new Ticket("child", 10, "Child"));

        this.instance.reset();
        assertFalse(this.instance.getClient().isAdmin());
        assertTrue(this.instance.getTickets().isEmpty());
    }
}
