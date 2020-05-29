package Default.TSP;

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

    public int getAfstandKortsteRoute(Route kortsteRoute) {
        return kortsteRoute.berekenTotaleAfstand();
    }

}
