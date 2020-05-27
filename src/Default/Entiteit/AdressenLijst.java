package Default.Entiteit;

import java.util.ArrayList;

public class AdressenLijst {

    /** Adressenlijst met openstaande routes van een provincie **/
    public static ArrayList<String> adressen = new ArrayList<String>();

    private String straat;
    private int huisnummer;
    private String stad;

    /** Voegt adressen toe aan adressenlijst **/
    public void addAdres(String straat, int huisnummer, String stad) {
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.stad = stad;
        adressen.add(straat +" "+huisnummer);
    }

    public ArrayList<String> getAdressen() { return adressen; }

    public void clearAdressen() {
        adressen.clear();
    }


}
