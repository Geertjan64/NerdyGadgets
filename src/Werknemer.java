public abstract class Werknemer {

    private int werknemerID;
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;
    private String email;

    public Werknemer(int werknemerID, String voornaam, String achternaam, String tussenvoegsel, String email) {
        this.werknemerID = werknemerID;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.tussenvoegsel = tussenvoegsel;
        this.email = email;
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

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getEmail() {
        return email;
    }
}


