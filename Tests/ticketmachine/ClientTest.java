package ticketmachine;

import org.junit.Test;
import static org.junit.Assert.*;

public class ClientTest {
    /**
     * Test of getBalance method, of class Client.
     */
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");

        Client instance = new Client();
        instance.addBalance(10);

        assertEquals(instance.getBalance(), 10, 0.0);
    }

    /**
     * Test of setBalance method, of class Client.
     */
    @Test
    public void testSetBalance() {
        System.out.println("setBalance");

        Client instance = new Client();
        instance.setBalance(10);

        assertEquals(instance.getBalance(), 10, 0.0);
    }

    /**
     * Test of addBalance method, of class Client.
     */
    @Test
    public void testAddBalance() {
        System.out.println("addBalance");

        Client instance = new Client();
        instance.setBalance(10);
        instance.addBalance(10);

        assertEquals(instance.getBalance(), 20, 0.0);
    }

    /**
     * Test of removeBalance method, of class Client.
     */
    @Test
    public void testRemoveBalance() {
        System.out.println("removeBalance");

        Client instance = new Client();
        instance.setBalance(20);
        instance.removeBalance(10);

        assertEquals(instance.getBalance(), 10, 0.0);
    }

    /**
     * Test of canAfford method, of class Client.
     */
    @Test
    public void testCanAfford() {
        System.out.println("canAfford");

        Client instance = new Client();
        instance.setBalance(10);

        assertEquals(instance.canAfford(20), false);
        assertEquals(instance.canAfford(10), true);
    }

    /**
     * Test of isAdmin method, of class Client.
     */
    @Test
    public void testIsAdmin() {
        System.out.println("isAdmin");

        Client instance = new Client();

        assertEquals(instance.isAdmin(), false);
        instance.setAdmin(true);
        assertEquals(instance.isAdmin(), true);
    }

    /**
     * Test of setAdmin method, of class Client.
     */
    @Test
    public void testSetAdmin() {
        System.out.println("setAdmin");

        Client instance = new Client();

        instance.setAdmin(true);
        assertEquals(instance.isAdmin(), true);
        instance.setAdmin(false);
        assertEquals(instance.isAdmin(), false);
    }

    /**
     * Test of login method, of class Client.
     */
    @Test
    public void testLogin() {
        System.out.println("login");

        Client instance = new Client();

        assertEquals(instance.login("abc", "abc"), true);
        assertEquals(instance.login("123", "abc"), false);
    }

    /**
     * Test of logout method, of class Client.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");

        Client instance = new Client();
        instance.setAdmin(true);

        instance.logout();
        assertEquals(instance.isAdmin(), false);
    }

    /**
     * Test of reset method, of class Client.
     */
    @Test
    public void testReset() {
        System.out.println("reset");

        Client instance = new Client();
        instance.setBalance(100);
        instance.setAdmin(true);

        instance.reset();
        assertEquals(instance.getBalance(), 0, 0.0);
        assertEquals(instance.isAdmin(), false);
    }
}
