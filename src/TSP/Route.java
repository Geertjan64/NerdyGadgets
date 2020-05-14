package TSP;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Route {
    private ArrayList<Stad> steden = new ArrayList<Stad>();

    public Route(ArrayList<Stad> steden) {
        this.steden.addAll(steden);
    }

    public ArrayList<Stad> getSteden() {
        return steden;
    }

    public int berekenTotaleAfstand() {
        int stedenLengte = this.getSteden().size();
        return (int) (this.getSteden().stream().mapToDouble(x -> {
            int stadIndex = this.getSteden().indexOf(x);
            double returnWaarde = 0;
            if (stadIndex < stedenLengte - 1) returnWaarde = x.afstandMeten(this.getSteden().get(stadIndex +1));
            return returnWaarde;
        }).sum() + this.getSteden().get(stedenLengte - 1).afstandMeten(this.getSteden().get(0)));
    }
    public String toString() {
        return Arrays.toString(steden.toArray()); }
}
