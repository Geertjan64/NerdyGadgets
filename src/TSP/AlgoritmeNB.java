package TSP;
import Default.HereApp;
import Default.SQLFuncties;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class AlgoritmeNB {

    private SQLFuncties f;
    private HereApp s;
    public String provincie = "";
    public String route = "";


    public Route vindKortsteRoute(ArrayList<Stad> steden) throws SQLException, IOException, JSONException {
        int i = 0;
        ArrayList<Stad> kortsteRouteSteden = new ArrayList<>(steden.size());
        System.out.println("------");
        System.out.println("Eerste route => " + Arrays.toString(steden.toArray()));
        System.out.println("w/ Totale afstand: " + new Route(steden).berekenTotaleAfstand());
        System.out.println("------");

        Stad stad = steden.get(0);
        updateRoutes(kortsteRouteSteden, steden, stad);

        while (steden.size() >= 1) {
            stad = getVolgendeStad(steden, stad);
            updateRoutes(kortsteRouteSteden, steden, stad);
        }
        for(Stad s : kortsteRouteSteden) {
            i++;
            provincie = s.getProvincie();
            route += "["+ i +"]: "+s.getNaam() +", "+s.getStraatnaam()+" "+ s.getHuisnummer() + ", "+s.getProvincie()+" ";
        }
        f = new SQLFuncties();

        System.out.println(HereApp.bezorgerID);
        f.insertKortsteRoute(route,provincie, HereApp.bezorgerID);
        return new Route(kortsteRouteSteden);
    }

    public String getProvincie() {
        return provincie;
    }

    public String getRoute() {
        return route;
    }

    public void updateRoutes(ArrayList<Stad> kortsteStedenRoutes, ArrayList<Stad> steden, Stad stad) {
        kortsteStedenRoutes.add(stad);
        steden.remove(stad);
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
