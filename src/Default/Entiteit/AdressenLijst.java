package Default.Entiteit;

import java.util.ArrayList;

public class AdressenLijst {

    /** Adressenlijst met openstaande routes van een provincie **/
    public static ArrayList<String> adressen = new ArrayList<String>();

    private String straat;
    private int huisnummer;
    private String stad;

    public int getAddress_ID() {
        return Address_ID;
    }

    private int Address_ID;

    /** Voegt adressen toe aan adressenlijst **/
    public void addAdres(int Address_ID, String straat, int huisnummer, String stad) {
        this.Address_ID = Address_ID;
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
