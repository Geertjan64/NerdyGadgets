package Default.TSP;

public class Stad {
    private static final int radius = 6378;
    private static final double gradenNaarRadius = Math.PI/180D;
    private double longitude;
    private double latitude;
    private String naam;
    private String straatnaam;
    private int huisnummer;
    private String provincie;

    public Stad(String naam, double latitude, double longitude, String straatnaam, int huisnummer, String provincie ) {
        this.naam = naam;
        this.latitude = latitude * gradenNaarRadius;
        this.longitude = longitude * gradenNaarRadius;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.provincie = provincie;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getProvincie() { return provincie; }

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
