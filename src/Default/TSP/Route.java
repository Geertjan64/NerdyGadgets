package Default.TSP;

import java.util.ArrayList;

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
        int i = 0;
        String returnval = "";
        for(Stad s : steden) {
            i++;
            returnval += "["+ i +"]: "+s.getNaam() +", "+s.getStraatnaam()+" "+ s.getHuisnummer() + ", "+s.getProvincie()+" ";
        }
        return returnval;
    }
}
