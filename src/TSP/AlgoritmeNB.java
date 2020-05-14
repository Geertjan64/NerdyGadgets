package TSP;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class AlgoritmeNB {

    public String kr = "";

    public Route vindKortsteRoute(ArrayList<Stad> steden) {
        ArrayList<Stad> kortsteRouteSteden = new ArrayList<>(steden.size());
        System.out.println("----");
        System.out.println("Eerste route => " + Arrays.toString(steden.toArray()));
        System.out.println("w/ Totale afstand: " + new Route(steden).berekenTotaleAfstand());
        System.out.println("----");

        Stad stad = steden.get(0);
        updateRoutes(kortsteRouteSteden, steden, stad);

        while (steden.size() >= 1) {
            stad = getVolgendeStad(steden, stad);
            updateRoutes(kortsteRouteSteden, steden, stad);
        }
        kr = kortsteRouteSteden.toString();
        System.out.println(kr);
        return new Route(kortsteRouteSteden);
    }

    public void updateRoutes(ArrayList<Stad> kortsteStedenRoutes, ArrayList<Stad> steden, Stad stad) {
        kortsteStedenRoutes.add(stad);
        steden.remove(stad);

        System.out.println("Steden in kortste route => " + Arrays.toString(kortsteStedenRoutes.toArray()));
        System.out.println("Overgebleven steden => " + Arrays.toString(steden.toArray()) + "\n");
    }

    public Stad getVolgendeStad(ArrayList<Stad> steden, Stad stad) {
        return steden.stream().min((stad1, stad2) -> {
            int flag = 0;
            if (stad1.afstandMeten(stad) < stad2.afstandMeten(stad)) flag = -1;
            else if (stad1.afstandMeten(stad) > stad2.afstandMeten(stad)) flag = 1;
            return flag;
        }).get();
    }
}
