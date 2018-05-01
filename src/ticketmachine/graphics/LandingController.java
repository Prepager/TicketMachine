package ticketmachine.graphics;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LandingController implements Initializable {
    /**
     * Store the parent class.
     *
     * @var FrameController
     */
    private FrameController parent;

    /**
     * Store the login password field.
     *
     * @var TextField
     */
    @FXML
    private TextField login;

    /**
     * Store the child input field.
     *
     * @var TextField
     */
    @FXML
    private TextField childInput;

    /**
     * Store the adult input field.
     *
     * @var TextField
     */
    @FXML
    private TextField adultInput;

    /**
     * Store the senior input field.
     *
     * @var TextField
     */
    @FXML
    private TextField seniorInput;

    /**
     * Store the frame continue button.
     *
     * @var Button
     */
    @FXML
    private Button frameContinue;

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
     * Decrement the child amount.
     */
    @FXML
    private void childDecrement() {
        // Get value from input and handle action.
        int value = Integer.parseInt(childInput.getText());
        if (value > 0) value--;

        // Set new String value on input.
        childInput.setText(Integer.toString(value));
        this.amountChanged();
    }

    /**
     * Increment the child amount.
     */
    @FXML
    private void childIncrement() {
        // Set new String value on input.
        childInput.setText(Integer.toString(Integer.parseInt(childInput.getText()) + 1));
        this.amountChanged();
    }

    /**
     * Decrement the adult amount.
     */
    @FXML
    private void adultDecrement() {
        // Get value from input and handle action.
        int value = Integer.parseInt(adultInput.getText());
        if (value > 0) value--;

        // Set new String value on input.
        adultInput.setText(Integer.toString(value));
        this.amountChanged();
    }

    /**
     * Increment the adult amount.
     */
    @FXML
    private void adultIncrement() {
        // Set new String value on input.
        adultInput.setText(Integer.toString(Integer.parseInt(adultInput.getText()) + 1));
        this.amountChanged();
    }

    /**
     * Decrement the senior amount.
     */
    @FXML
    private void seniorDecrement() {
        // Get value from input and handle action.
        int value = Integer.parseInt(seniorInput.getText());
        if (value > 0) value--;

        // Set new String value on input.
        seniorInput.setText(Integer.toString(value));
        this.amountChanged();
    }

    /**
     * Increment the senior amount.
     */
    @FXML
    private void seniorIncrement() {
        // Set new String value on input.
        seniorInput.setText(Integer.toString(Integer.parseInt(seniorInput.getText()) + 1));
        this.amountChanged();
    }

    /**
     * Listen to child input change and remove chars.
     */
    @FXML
    private void childInputChanged() {
        if (! childInput.getText().matches("\\d*")) {
            childInput.setText(childInput.getText().replaceAll("[^\\d]", ""));
            childInput.positionCaret(100);
        }
    }

    /**
     * Listen to adult input change and remove chars.
     */
    @FXML
    private void adultInputChanged() {
        if (! adultInput.getText().matches("\\d*")) {
            adultInput.setText(adultInput.getText().replaceAll("[^\\d]", ""));
            adultInput.positionCaret(100);
        }
    }

    /**
     * Listen to senior input change and remove chars.
     */
    @FXML
    private void seniorInputChanged() {
        if (! seniorInput.getText().matches("\\d*")) {
            seniorInput.setText(seniorInput.getText().replaceAll("[^\\d]", ""));
            seniorInput.positionCaret(100);
        }
    }

    /**
     * Listen to amount inputs and change continue state.
     */
    @FXML
    private void amountChanged() {
        // Get total amount of people.
        int total = Integer.parseInt(this.childInput.getText());
        total += Integer.parseInt(this.adultInput.getText());
        total += Integer.parseInt(this.seniorInput.getText());

        // Change continue button disabled state if 0.
        this.frameContinue.setDisable((total <= 0));
    }

    /**
     * On continue set passenger amounts and continue.
     */
    @FXML
    private void doContinue() {
        // Continue to next frame.
        this.parent.nextFrame();

        // Extract amount and set on parent.
        int children = Integer.parseInt(this.childInput.getText());
        int adults = Integer.parseInt(this.adultInput.getText());
        int seniors = Integer.parseInt(this.seniorInput.getText());
        this.parent.setAmounts(children, adults, seniors);
    }

    /**
     * Attempt to login and show administrator panel.
     */
    @FXML
    private void attemptLogin() {
        // Attempt to login using login text field.
        boolean attempt = this.parent.getMachine().login(this.login.getText());

        // Check if attempt was successfull.
        if (attempt) {
            this.parent.loginPane();
        }
    }
}
