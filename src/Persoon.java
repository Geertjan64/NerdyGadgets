public abstract class Persoon {

    private int werknemerID;
    private String voornaam;
    private String achternaam;

    public Persoon(int werknemerID, String voornaam, String achternaam) {
        this.werknemerID = werknemerID;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
    }

    public int getWerknemerID() {
        return werknemerID;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }
}


