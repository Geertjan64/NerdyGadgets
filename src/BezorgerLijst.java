import java.util.ArrayList;

public class BezorgerLijst {

    private Bezorger bezorger;
    private ArrayList<Bezorger> bezorgers;
//    private String[] bezorgers;

    public BezorgerLijst() {
        bezorgers = new ArrayList<Bezorger>();
//      bezorgers = new String[] {"Piet", "Geert-Jan", "Pamir", "Lars", "Dielan", "Pascal", "Peter", "Fred", "Jan"};
    }

    public void addBezorger(Bezorger bezorger) {
        bezorgers.add(bezorger);
    }

    public ArrayList<Bezorger> getBezorgers() {
        return bezorgers;
    }

}
