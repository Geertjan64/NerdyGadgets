package Default.Entiteit;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdressenLijst {

    public static ArrayList<String> adressen = new ArrayList<String>();
    public static ArrayList<String> provincies = new ArrayList<String>();

    private String straat;
    private int huisnummer;
    private String stad;

    public void addAdres(String straat, int huisnummer, String stad) {
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.stad = stad;
        adressen.add(straat +" "+huisnummer);
    }

//    public int getHuisnummer() {
//        return huisnummer;
//    }
//
//    public String getStraat() {
//        return straat;
//    }
//
//    public String getStad() {
//        return stad;
//    }
//
//    public ArrayList<String> getProvincies() {
//        return provincies;
//    }

    public ArrayList<String> getAdressen() { return adressen; }

    public void clearAdressen() {
        adressen.clear();
    }

//    public void addProvincie(String provincie) { provincies.add(provincie); }

}
