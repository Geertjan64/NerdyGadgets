package Default;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdressenLijst {

    public static ArrayList<String> adressen = new ArrayList<String>();
    public static ArrayList<String> provincies = new ArrayList<String>();

    public void addAdres(String adres) {
        adressen.add(adres);
    }

    public void addProvincie(String provincie) {
        provincies.add(provincie);
    }

    public ArrayList<String> getAdressen() {
        return adressen;
    }

    public void clearAdressen() {
        adressen.clear();
    }

    public ArrayList<String> getProvincies() {
        return provincies;
    }
}
