package TSP;

import java.util.ArrayList;
import java.util.Arrays;

public class BezorgerSteden {
    public ArrayList<Stad> initialSteden = new ArrayList<>(Arrays.asList(
    ));

    public void addStad(Stad stad) {
        initialSteden.add(stad);
    }

    public String getInitialSteden() {
        String returnValue = "";
        for (Stad f : initialSteden) {
            returnValue += f.getNaam() + " " + f.getStraatnaam() + " " + f.getHuisnummer() + " " + f.getProvincie() + "\n";
        };
        return returnValue;
    }

    public void printKortsteRoute(Route kortsteRoute) {
        System.out.println("------");
        System.out.println("Kortste route gevonden zo ver: " + kortsteRoute);
        System.out.println("w/ totale afstand: " + kortsteRoute.berekenTotaleAfstand());
        System.out.println("------");
    }

}
