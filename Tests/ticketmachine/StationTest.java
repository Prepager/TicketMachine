package ticketmachine;

import junit.framework.TestCase;

public class StationTest extends TestCase {
    /**
     * Test of getName method, of class Station.
     */
    public void testGetName() {
        System.out.println("getName");

        Station instance = new Station("København H", 55.672761, 12.564924);

        assertEquals(instance.getName(), "København H");
    }

    /**
     * Test of getLatitude method, of class Station.
     */
    public void testGetLatitude() {
        System.out.println("getLatitude");

        Station instance = new Station("København H", 55.672761, 12.564924);

        assertEquals(instance.getLatitude(), 55.672761, 0);
    }

    /**
     * Test of getLongitude method, of class Station.
     */
    public void testGetLongitude() {
        System.out.println("getLongitude");

        Station instance = new Station("København H", 55.672761, 12.564924);

        assertEquals(instance.getLongitude(), 12.564924, 0);
    }
}
