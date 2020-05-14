package TSP;

import java.util.ArrayList;
import java.util.Arrays;

public class Bezorger {
    private ArrayList<Stad> initialSteden = new ArrayList<Stad>(Arrays.asList(
            new Stad("Nijkerk", 52.22, 5.48611),
            new Stad("Amsterdam", 52.370216, 4.895168),
            new Stad("Rotterdam", 51.905445, 4.466637),
            new Stad("Den Haag", 52.078663, 4.288788)
    ));

    public void printKortsteRoute(Route kortsteRoute) {
        System.out.println("------");
        System.out.println("Kortste route gevonden zo ver: " + kortsteRoute);
        System.out.println("w/ totale afstand: " + kortsteRoute.berekenTotaleAfstand());
        System.out.println("------");
    }

    public static void main(String[] args) {
        Bezorger bezorger = new Bezorger();
        ArrayList<Stad> steden = new ArrayList<Stad>();
        steden.addAll(bezorger.initialSteden);
        bezorger.printKortsteRoute(new AlgoritmeNB().vindKortsteRoute(steden));
    }


}
