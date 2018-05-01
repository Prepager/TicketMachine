package ticketmachine.graphics;

import java.net.URL;
import javafx.fxml.FXML;
import ticketmachine.Station;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import netscape.javascript.JSObject;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.MapComponentInitializedListener;

public class DestinationController implements Initializable, MapComponentInitializedListener {
    /**
     * Store the parent class.
     *
     * @var FrameController
     */
    private FrameController parent;

    /**
     * Store the raw Google Map View.
     *
     * @var GoogleMapView
     */
    @FXML
    private GoogleMapView mapView;

    /**
     * Store the created Google Map.
     *
     * @var GoogleMap
     */
    private GoogleMap map;

    /**
     * Initialize the frame controller.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add inialized event listener.
        mapView.addMapInializedListener(this);
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
     * Continue to the next frame.
     */
    @FXML
    private void doContinue() {
        this.parent.nextFrame();
    }

    /**
     * Setup and load the Google Maps settings.
     */
    @Override
    public void mapInitialized() {
        // Get machine location as latitude and longitude
        double latitude = this.parent.getMachine().getLatitude();
        double longitude = this.parent.getMachine().getLongitude();
        LatLong position = new LatLong(latitude, longitude);

        // Create new map options object.
        MapOptions mapOptions = new MapOptions();

        // Define map options.
        mapOptions.center(position)
            .mapType(MapTypeIdEnum.ROADMAP)
            .overviewMapControl(false)
            .panControl(false)
            .rotateControl(false)
            .scaleControl(false)
            .streetViewControl(false)
            .zoomControl(false)
            .zoom(9);

        // Create map with above settings.
        map = mapView.createMap(mapOptions);

        // Set current location settings.
        MarkerOptions selfMarker = new MarkerOptions();
        selfMarker.title("Current Location");
        selfMarker.icon("https://i.imgur.com/ipkYKEP.png");
        selfMarker.position(position);

        // Add current location to map.
        Marker selfMapMarker = new Marker(selfMarker);
        map.addMarker(selfMapMarker);

        // Loop through all stations on machine.
        this.parent.getMachine().getStations().forEach((Station station) -> {
            // Create marker options with name and position.
            MarkerOptions marker = new MarkerOptions();
            marker.title(station.getName());
            marker.icon("https://i.imgur.com/UgzOBZp.png");
            marker.position(new LatLong(station.getLatitude(), station.getLongitude()));

            // Create map marker and add to map.
            Marker mapMarker = new Marker(marker);
            map.addMarker(mapMarker);

            // Add marker click event.
            map.addUIEventHandler(mapMarker, UIEventType.click, (JSObject obj) -> {
                this.parent.setDestination(station);
                this.doContinue();
            });
        });
    }
}
