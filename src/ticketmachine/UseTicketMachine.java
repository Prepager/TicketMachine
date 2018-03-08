package ticketmachine;

import ticketmachine.gui.ContentFrame;
import ticketmachine.tickets.Ticket;

public class UseTicketMachine {
    /**
     * x
     *
     * @var TicketMachine
     */
    TicketMachine machine;

    /**
     * x
     *
     * @var Frame
     */
    ContentFrame frame;

    /**
     * Call the non-static constructor.
     *
     * @param arg
     */
    public static void main(String[] arg) {
        new UseTicketMachine();
    }
    
    /**
     * Create machine and open initial frame.
     */
    UseTicketMachine() {
        // Create new ticket machine.
        this.machine = new TicketMachine();
        this.machine.addTicket(new Ticket(3, 25, "x"));
        this.machine.addTicket(new Ticket(1, 50, "y"));
        this.machine.addTicket(new Ticket(2, 100, "z"));
        
        // Create the frame and make visible.
        this.frame = new ContentFrame();
        this.frame.setVisible(true);
        
        // Bind the machine to the frame and refresh.
        this.frame.setMachine(machine);
        this.frame.refresh();
    }
}
