package TSP;

public class Stad {
    private static final int radius = 6378;
    private static final double gradenNaarRadius = Math.PI/180D;
    private double longitude;
    private double latitude;
    private String naam;

    public Stad(String naam, double latitude, double longitude) {
        this.naam = naam;
        this.latitude = latitude * gradenNaarRadius;
        this.longitude = longitude * gradenNaarRadius;
    }

    public String getNaam() {
        return naam;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double afstandMeten(Stad stad) {
        double verschilLongitude = (stad.getLongitude() - this.getLongitude());
        double verschilLatitude = (stad.getLatitude() - this.getLatitude());
        double berekening = Math.pow(Math.sin(verschilLatitude / 2D), 2D) +
                            Math.cos(this.getLatitude()) * Math.cos(stad.getLatitude()) * Math.pow(Math.sin(verschilLongitude / 2D), 2D);
        return radius * 2D * Math.atan2(Math.sqrt(berekening), Math.sqrt(1D - berekening));
    }
    public String toString() {
        return this.naam;
    }
}
