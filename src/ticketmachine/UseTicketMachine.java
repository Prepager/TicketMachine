package ticketmachine;

import ticketmachine.graphics.FrameController;

public class UseTicketMachine {
    /**
     * Store the ticket machine.
     *
     * @var TicketMachine
     */
    public static TicketMachine machine;

    /**
     * Store the main GUI frame.
     *
     * @var Frame
     */
    private static FrameController frame;

    /**
     * Call the non-static constructor.
     *
     * @param arg
     */
    public static void main(String[] arg) {
        // Create new ticket machine with name and lat, long.
        machine = new TicketMachine("DTU Lyngby", 55.785575, 12.521367);

        // Add child, adult and senior ticket types.
        machine.addTicket(new Ticket("child", 0.2, "Child"));
        machine.addTicket(new Ticket("adult", 0.5, "Adult"));
        machine.addTicket(new Ticket("senior", 0.3, "Senior"));

        // Add stations by latitude and longitude.
        machine.addStation(new Station("København H",       55.672761, 12.564924));
        machine.addStation(new Station("Dybbølsbro St.",    55.665385, 12.559695));
        machine.addStation(new Station("Sydhavn St.",       55.654794, 12.537186));
        machine.addStation(new Station("Sjælør St.",        55.651544, 12.527028));
        machine.addStation(new Station("Ny Ellebjerg St.",  55.652390, 12.516018));
        machine.addStation(new Station("Åmarken St.",       55.639905, 12.500007));
        machine.addStation(new Station("Friheden St.",      55.628928, 12.482507));
        machine.addStation(new Station("Avedøre St.",       55.625196, 55.625196));
        machine.addStation(new Station("Brønby Strand St.", 55.620996, 12.421747));
        machine.addStation(new Station("Vallensbæk St.",    55.623253, 12.388444));
        machine.addStation(new Station("Ishøj St.",         55.613335, 12.358032));
        machine.addStation(new Station("Hundige St.",       55.597449, 12.333387));
        machine.addStation(new Station("Greve St.",         55.581331, 12.295860));
        machine.addStation(new Station("Karlslunde St.",    55.566290, 12.268851));
        machine.addStation(new Station("Solrød Strand St.", 55.533319, 12.218082));
        machine.addStation(new Station("Jersie St.",        55.520686, 12.208474));
        machine.addStation(new Station("Ølby St.",          55.479847, 12.175584));
        machine.addStation(new Station("Køge St.",          55.458029, 12.186360));

        // Create and show the frame controller.
        frame = new FrameController();
        frame.show();
    }
}
