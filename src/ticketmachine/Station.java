package ticketmachine;

public class Station {
    /**
     * The station name.
     *
     * @var String
     */
    private final String name;

    /**
     * The stations latitude.
     *
     * @var double
     */
    private double latitude = 0;

    /**
     * The stations longitude.
     *
     * @var double
     */
    private double longitude = 0;

    /**
     * Set the name, latitude and longitude on creation.
     *
     * @param name
     * @param latitude
     * @param longitude
     */
    public Station(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Return the name of the station.
     *
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the latitude of the station.
     *
     * @return double
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * Return the longitude of the station.
     *
     * @return double
     */
    public double getLongitude() {
        return this.longitude;
    }
}
