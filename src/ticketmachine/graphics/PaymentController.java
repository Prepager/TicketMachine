package ticketmachine.graphics;

import java.net.URL;
import javafx.fxml.FXML;
import ticketmachine.Station;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import ticketmachine.TicketMachine;
import javafx.scene.control.TextField;

public class PaymentController implements Initializable {
    /**
     * Store the parent class.
     *
     * @var FrameController
     */
    private FrameController parent;

    /**
     * Store the total price.
     *
     * @var double
     */
    private double totalPrice;

    /**
     * Store the frame continue button.
     *
     * @var Button
     */
    @FXML
    private Button frameContinue;

    /**
     * Store the from station label.
     *
     * @var Label
     */
    @FXML
    private Label from;

    /**
     * Store the to station label.
     *
     * @var Label
     */
    @FXML
    private Label to;

    /**
     * Store the total distance label.
     *
     * @var Label
     */
    @FXML
    private Label distance;

    /**
     * Store the amount of child passengers label.
     *
     * @var Label
     */
    @FXML
    private Label childrenAmount;

    /**
     * Store the amount of adult passengers label.
     *
     * @var Label
     */
    @FXML
    private Label adultsAmount;

    /**
     * Store the amount of senior passengers label.
     *
     * @var Label
     */
    @FXML
    private Label seniorsAmount;

    /**
     * Store the total child price label.
     *
     * @var Label
     */
    @FXML
    private Label childrenPrice;

    /**
     * Store the total adult price label.
     *
     * @var Label
     */
    @FXML
    private Label adultsPrice;

    /**
     * Store the total senior price label.
     *
     * @var Label
     */
    @FXML
    private Label seniorsPrice;

    /**
     * Store the total price label.
     *
     * @var Label
     */
    @FXML
    private Label total;

    /**
     * Store the current balance label.
     *
     * @var Label
     */
    @FXML
    private Label balance;

    /**
     * Store the remaining price label.
     *
     * @var Label
     */
    @FXML
    private Label remaining;

    /**
     * Store the manual money input field.
     *
     * @var TextField
     */
    @FXML
    private TextField money;

    /**
     * Initialize the frame.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Method left blank intentionally.
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
     * Load the data into the various elements.
     */
    public void load() {
        // Get needed related objects.
        Station station = this.parent.getDestination();
        TicketMachine machine = this.parent.getMachine();

        // Get total distance and passenger amounts.
        int totalDistance = this.parent.getDistance();
        int children = this.parent.getChildren();
        int adults = this.parent.getAdults();
        int seniors = this.parent.getSeniors();

        // Calculate total prices for passengers.
        double childrenTotalPrice = Math.round((machine.getTicket("child").getPrice() * children) * totalDistance);
        double adultsTotalPrice = Math.round((machine.getTicket("adult").getPrice()* adults) * totalDistance);
        double seniorsTotalPrice = Math.round((machine.getTicket("senior").getPrice() * seniors) * totalDistance);
        this.totalPrice = Math.round(childrenTotalPrice + adultsTotalPrice + seniorsTotalPrice);

        // Set the to and from station names.
        this.from.setText(machine.getName());
        this.to.setText(station.getName());

        // Set the total distance between stations.
        this.distance.setText(totalDistance + " km");

        // Set the passenger multipliers.
        this.childrenAmount.setText("x" + children);
        this.adultsAmount.setText("x" + adults);
        this.seniorsAmount.setText("x" + seniors);

        // Set the passenger total prices.
        this.childrenPrice.setText(childrenTotalPrice + " DKK");
        this.adultsPrice.setText(adultsTotalPrice + " DKK");
        this.seniorsPrice.setText(seniorsTotalPrice + " DKK");
        this.total.setText(totalPrice + " DKK");

        // Reload the current balance.
        this.reloadBalance();
    }

    /**
     * Reload current balance and remaining amount
     */
    public void reloadBalance() {
        // Get balance and remaining.
        double clientBalance =  Math.round(this.parent.getMachine().getClient().getBalance());
        double clientRemaining =  Math.round(this.totalPrice - clientBalance);

        // Set variables on labels.
        this.balance.setText(clientBalance + " DKK");
        this.remaining.setText(clientRemaining + " DKK");

        // Disable button if currently remaining.
        this.frameContinue.setDisable((clientRemaining > 0));
    }

    /**
     * Notify the parent of purchase complete.
     */
    @FXML
    private void doPurchase() {
        this.parent.complete();
    }

    /**
     * Cancel purchase and return back to landing.
     */
    @FXML
    private void doCancel() {
        this.parent.nextFrame();
    }

    /**
     * Manually add amount to client balance and reload.
     */
    @FXML
    private void inputMoney() {
        this.parent.getMachine().inputMoney(Integer.parseInt(this.money.getText()));
        this.reloadBalance();
    }
}
