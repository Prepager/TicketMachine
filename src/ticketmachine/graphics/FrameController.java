package ticketmachine.graphics;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import ticketmachine.Station;
import javafx.fxml.FXMLLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;
import ticketmachine.TicketMachine;
import ticketmachine.UseTicketMachine;
import javafx.application.Application;
import com.lynden.gmapsfx.javascript.object.LatLong;

public class FrameController extends Application {
    /**
     * The ticket machine for the frame.
     *
     * @var TicketMachine
     */
    private TicketMachine machine = null;

    /**
     * The current displayed stage.
     *
     * @var Stage
     */
    private Stage currentStage;

    /**
     * The id of the current scene.
     *
     * @var String
     */
    private String scene = "landing";

    /**
     * Store the landing frame object.
     *
     * @var Pane
     */
    private Pane landingFrame;

    /**
     * Store the landing frame controller.
     *
     * @var LandingController
     */
    private LandingController landingController;

    /**
     * Store the destination frame object.
     *
     * @var Pane
     */
    private Pane destinationFrame;

    /**
     * Store the destination frame controller.
     *
     * @var DestinationController
     */
    private DestinationController destinationController;

    /**
     * Store the payment frame object.
     *
     * @var Pane
     */
    private Pane paymentFrame;

    /**
     * Store the payment frame controller.
     *
     * @var PaymentController
     */
    private PaymentController paymentController;

    /**
     * Store the administrator frame object.
     *
     * @var Pane
     */
    private Pane adminFrame;

    /**
     * Store the administrator frame controller.
     *
     * @var AdminController
     */
    private AdminController adminController;

    /**
     * Store the amount of child passengers.
     *
     * @var integer
     */
    private int children = 0;

    /**
     * Store the amount of adult passengers.
     *
     * @var integer
     */
    private int adults = 0;

    /**
     * Store the amount of senior passengers.
     *
     * @var integer
     */
    private int seniors = 0;

    /**
     * Store the destination station.
     *
     * @var Station
     */
    private Station destination;

    /**
     * Store the distance to the destination.
     *
     * @var integer
     */
    private int distance;

    /**
     * Bind the machine to the frame.
     *
     * @param newMachine
     */
    public void setMachine(TicketMachine newMachine) {
        this.machine = newMachine;
    }

    /**
     * Return the binded machine.
     *
     * @return TicketMachine
     */
    public TicketMachine getMachine() {
        return this.machine;
    }

    /**
     * Set the amounts for children, adults and seniors.
     *
     * @param children
     * @param adults
     * @param seniors
     */
    public void setAmounts(int children, int adults, int seniors) {
        //
        this.children = children;
        this.adults = adults;
        this.seniors = seniors;
    }

    /**
     * Set the destination station and calculate distance.
     *
     * @param station
     */
    public void setDestination(Station station) {
        // Save destination on object.
        this.destination = station;

        // Define LatLong objects for to and from stations.
        LatLong toStation = new LatLong(station.getLatitude(), station.getLongitude());
        LatLong fromStation = new LatLong(this.machine.getLatitude(), this.machine.getLongitude());

        // Calculate the distance in km.
        this.distance = (int) Math.round(fromStation.distanceFrom(toStation) / 1000);
    }

    /**
     * Return the destination station.
     *
     * @return Station
     */
    public Station getDestination() {
        return this.destination;
    }

    /**
     * Return the total distance.
     *
     * @return integer
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * Return amount of child passengers.
     *
     * @return integer
     */
    public int getChildren() {
        return this.children;
    }

    /**
     * Return amount of adult passengers.
     *
     * @return integer
     */
    public int getAdults() {
        return this.adults;
    }

    /**
     * Return amount of senior passengers.
     *
     * @return integer
     */
    public int getSeniors() {
        return this.seniors;
    }

    /**
     * Launch and show the JavaFX frame.
     */
    public void show() {
        FrameController.launch();
    }

    /**
     * Load the frames from the FXML files.
     *
     * @throws java.io.IOException
     */
    public void loadFrames() throws IOException {
        // Load landing frame.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LandingFrame.fxml"));
        this.landingFrame = loader.load();
        this.landingController = loader.<LandingController>getController();
        this.landingController.setParent(this);

        // Load destination frame.
        loader = new FXMLLoader(getClass().getResource("DestinationFrame.fxml"));
        this.destinationFrame = loader.load();
        this.destinationController = loader.<DestinationController>getController();
        this.destinationController.setParent(this);

        // Load payment frame.
        loader = new FXMLLoader(getClass().getResource("PaymentFrame.fxml"));
        this.paymentFrame = loader.load();
        this.paymentController = loader.<PaymentController>getController();
        this.paymentController.setParent(this);

        // Load administrator frame.
        loader = new FXMLLoader(getClass().getResource("AdminFrame.fxml"));
        this.adminFrame = loader.load();
        this.adminController = loader.<AdminController>getController();
        this.adminController.setParent(this);
    }

    /**
     * Called by JavaFX, set initial settings.
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        // Set the machine from memory.
        this.setMachine(UseTicketMachine.machine);

        // Save the stage on the object.
        this.currentStage = stage;

        // Load frames and controllers on object.
        try {
            this.loadFrames();
        } catch (IOException ex) {
            Logger.getLogger(FrameController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create default scene with landing.
        System.out.println(this.landingFrame);
        stage.setScene(new Scene(this.landingFrame));

        // Set window title and size.
        stage.setTitle("BlueJ Transportation");
        stage.show();
    }

    /**
     * Find the correct next frame on continue.
     */
    public void nextFrame() {
        // Create holder for next pane.
        Pane nextPane;

        // Switch the current scene.
        switch (this.scene) {
            case "landing":
                this.scene = "destination";
                nextPane = this.destinationFrame;
                break;

            case "destination":
                this.scene = "payment";
                nextPane = this.paymentFrame;
                break;

            default:
                this.scene = "landing";
                nextPane = this.landingFrame;
                break;
        }

        // Set the next scene pane.
        this.currentStage.getScene().setRoot(nextPane);

        // Load data into payment form.
        if (this.scene.equals("payment")) {
            this.paymentController.load();
        }
    }

    /**
     * Purchase tickets on completion and return change.
     */
    public void complete() {
        // Purchase child tickets.
        for (int i = 0; i < this.children; i++) {
            this.machine.purchaseTicket("child", this.destination, this.distance);
        }

        // Purchase adult tickets.
        for (int i = 0; i < this.adults; i++) {
            this.machine.purchaseTicket("adult", this.destination, this.distance);
        }

        // Purchase senior tickets.
        for (int i = 0; i < this.seniors; i++) {
            this.machine.purchaseTicket("senior", this.destination, this.distance);
        }

        // Return remaning balance.
        this.machine.returnChange();

        // Continue to next frame.
        this.nextFrame();
    }

    /**
     * Show the administrator only panel.
     */
    public void loginPane() {
        this.scene = "admin";
        this.currentStage.getScene().setRoot(this.adminFrame);
        this.adminController.load();
    }
}
