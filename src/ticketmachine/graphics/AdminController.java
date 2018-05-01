package ticketmachine.graphics;

import java.net.URL;
import javafx.fxml.FXML;
import ticketmachine.Ticket;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ticketmachine.TicketMachine;
import javafx.scene.control.TextArea;
import ticketmachine.logging.Transaction;

public class AdminController implements Initializable {
    /**
     * Store the parent class.
     *
     * @var FrameController
     */
    private FrameController parent;

    /**
     * Store the total earnings label.
     *
     * @var Label
     */
    @FXML
    private Label total;

    /**
     * Store the child earnings label.
     *
     * @var Label
     */
    @FXML
    private Label childText;

    /**
     * Store the adult earnings label.
     *
     * @var Label
     */
    @FXML
    private Label adultText;

    /**
     * Store the senior earnings label.
     *
     * @var Label
     */
    @FXML
    private Label seniorText;

    /**
     * Store the log text field.
     *
     * @var TextArea
     */
    @FXML
    private TextArea log;

    /**
     * Initializes the controller class.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Method left blank intentionally.
    }

    /**
     * Load the data into the various elements.
     */
    public void load() {
        // Get needed related objects.
        TicketMachine machine = this.parent.getMachine();

        // Update total earnings text.
        this.total.setText(machine.getTotal() + " DKK");

        // Update child earnings text.
        Ticket child = machine.getTicket("child");
        this.childText.setText("Sold: " + child.getSold() + ", Earnings: " + child.getTotal());

        // Update adult earnings text.
        Ticket adult = machine.getTicket("adult");
        this.adultText.setText("Sold: " + adult.getSold() + ", Earnings: " + adult.getTotal());

        // Update senior earnings text.
        Ticket senior = machine.getTicket("senior");
        this.seniorText.setText("Sold: " + senior.getSold() + ", Earnings: " + senior.getTotal());

        // Append log entries to text area.
        machine.getLogger().getList().forEach((Transaction transaction) -> {
            this.log.appendText(transaction.formatted() + "\n");
        });
    }

    /**
     * Save the frame controller parent for future use.
     *
     * @param controller
     */
    public void setParent(FrameController controller) {
        this.parent = controller;
    }

    /**
     * Logout the user and return to landing frame.
     */
    @FXML
    private void doLogout() {
        // Logout the user.
        this.parent.getMachine().logout();

        // Return to next frame.
        this.parent.nextFrame();
    }
}
